package com.sirika.openplacesearch.api.administrativedivision.internal

import com.google.common.io.{InputSupplier}
import grizzled.slf4j.Logging
import com.sirika.openplacesearch.api.administrativedivision._
import com.sirika.commons.scala.lineparser.{LineByLineInputStreamParser}
import com.sirika.openplacesearch.api.referencedata.ReferenceData
import java.io.{Reader}
import com.google.inject.Inject

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
@com.google.inject.Singleton()
protected[administrativedivision] final class InMemoryAdministrativeDivisionRepository @Inject() (private[this] val countryRepository: CountryRepository,
                                                          private[this] val alternateNamesLookup: AlternateNamesLookup)
  extends AdministrativeDivisionRepository with Logging {

  // make sure the objects are not lazy-loaded
  def doNothing(a: Any) = {}
  doNothing(FirstOrderAdministrativeDivisions.allFirstOrderAdministrativeDivisions)
  doNothing(SecondOrderAdministrativeDivisions.allSecondOrderAdministrativeDivisions)

  private[this] object FirstOrderAdministrativeDivisions {
    val allFirstOrderAdministrativeDivisions = parseAdm1(ReferenceData.FirstOrderAdministrativeDivisions)
    val allFirstOrderAdministrativeDivisionsPerCountry: Map[Country,List[AdministrativeDivision]] = allFirstOrderAdministrativeDivisions.groupBy (_.country)
    val adm1LookupTable: Map[(Country,String),AdministrativeDivision] = Map(allFirstOrderAdministrativeDivisions.map{a : AdministrativeDivision => ((a.country,a.code), a)} : _*)

    private def parseAdm1[R <: Reader](readerSupplier: InputSupplier[R]) : List[AdministrativeDivision] = {

      new LineByLineInputStreamParser(readerSupplier = readerSupplier, fieldExtractor = FieldExtractors.extractFieldsFromAdministrativeDivisionLine).map { (fields, line, lineNumber) =>
        fields match {
          case Array(compositeCode, name, asciiName, geonamesId)
          =>
            val Array(countryAlpha2Code,adminCode) = compositeCode.split('.')
            val parentAdministrativeEntity = countryRepository.getByIsoAlpha2Code(countryAlpha2Code)

            Right(AdministrativeDivision(
              code=adminCode,
              featureNameProvider=
                SimpleFeatureNameProvider(
                  defaultName = if(name.nonEmpty) name else asciiName,
                  parentAdministrativeEntity=Some(parentAdministrativeEntity),
                  names=alternateNamesLookup.getAlternateNamesFor(geonamesId.toLong)),
              parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(Some(parentAdministrativeEntity))))
        }
      }
    }
  }

  private[this] object SecondOrderAdministrativeDivisions {
    val allSecondOrderAdministrativeDivisions = parseAdm2(ReferenceData.SecondOrderAdministrativeDivisions)
    val allSecondOrderAdministrativeDivisionPerCountryAndAdm1: Map[(Country,AdministrativeDivision),List[AdministrativeDivision]] =
      allSecondOrderAdministrativeDivisions.groupBy {a=>(a.country,a.parentAdministrativeEntity.get.asInstanceOf[AdministrativeDivision])}
    val adm2LookupTable: Map[(Country,AdministrativeDivision, String),AdministrativeDivision] =
      Map(allSecondOrderAdministrativeDivisions.map{a : AdministrativeDivision =>
        ((a.country,a.parentAdministrativeEntity.get.asInstanceOf[AdministrativeDivision], a.code), a)} : _*)

    private def parseAdm2[R <: Reader](readerSupplier: InputSupplier[R]) : List[AdministrativeDivision] = {
      var adm1hacks: Map[(Country, String), AdministrativeDivision] = Map()

      val result = new LineByLineInputStreamParser(readerSupplier = readerSupplier, fieldExtractor = FieldExtractors.extractFieldsFromAdministrativeDivisionLine).map { (fields, line, lineNumber) =>
        fields match {
          case Array(compositeCode, name, asciiName, geonamesId)
          =>
            val Array(countryAlpha2Code,adm1Code,adm2Code) = compositeCode.split('.')
            val country = countryRepository.getByIsoAlpha2Code(countryAlpha2Code)
            val adm1 = getFirstOrderAdministrativeDivisionOption(country, adm1Code)

            def adm1ToUse: Option[AdministrativeDivision] = {
              adm1 match {
                case None =>
                  warn("ADM1 with code %s from country %s does not exist. ADM2(%s,%s) will have a parent ADM1 with name: %s".format(adm1Code, countryAlpha2Code, adm2Code, name, adm1Code + "[HACK]"))
                  adm1hacks.get((country, adm1Code)) match {
                    case None =>
                      val a = AdministrativeDivision(code=adm1Code,featureNameProvider=
                        SimpleFeatureNameProvider(
                          defaultName = adm1Code + "[HACK]",
                          parentAdministrativeEntity=Some(country)),
                        parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(Some(country)))

                      val t = ((a.country,a.code), a)
                      adm1hacks = adm1hacks + t
                      adm1hacks.get((country, adm1Code))
                    case s => s
                  }
                case s => s
              }

            }

            Right(
              AdministrativeDivision(
                code=adm2Code,
                featureNameProvider=
                  SimpleFeatureNameProvider(
                    defaultName = if(name.nonEmpty) name else asciiName,
                    parentAdministrativeEntity=adm1ToUse,
                    names=alternateNamesLookup.getAlternateNamesFor(geonamesId.toLong)),
                parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(adm1ToUse)))
        }
      }

      sumUpErrors(adm1hacks)
      result
    }
  }

  def sumUpErrors(adm1hacks: Map[(Country, String), AdministrativeDivision]) {
    val errorsByCountry = adm1hacks.values.groupBy(_.country)
    errorsByCountry.keys.foreach { c =>
      val missingAdm1s = errorsByCountry.get(c).get.map(_.code).mkString(",")
      warn("(Summary) Country[%s] misses the following ADM1: %s".format(c.isoCountryCode.alpha3Code, missingAdm1s))

    }
  }

  def findAllSecondOrderAdministrativeDivisions(country: Country, firstOrderAdministrativeDivision: AdministrativeDivision) = {
    SecondOrderAdministrativeDivisions.allSecondOrderAdministrativeDivisionPerCountryAndAdm1.get((country, firstOrderAdministrativeDivision)).get
  }

  def getSecondOrderAdministrativeDivision(country: Country, firstOrderAdministrativeDivision: AdministrativeDivision, code: String) = {
    SecondOrderAdministrativeDivisions.adm2LookupTable.get((country, firstOrderAdministrativeDivision, code)).get
  }

  def findAllFirstOrderAdministrativeDivisions(country: Country) = {
    FirstOrderAdministrativeDivisions.allFirstOrderAdministrativeDivisionsPerCountry.get(country).get
  }

  def getFirstOrderAdministrativeDivision(country: Country, code: String): AdministrativeDivision = {
    FirstOrderAdministrativeDivisions.adm1LookupTable.get((country,code)).get
  }

  private def getFirstOrderAdministrativeDivisionOption(country: Country, code: String): Option[AdministrativeDivision] = {
    FirstOrderAdministrativeDivisions.adm1LookupTable.get((country,code))
  }

}