package com.sirika.openplacesearch.api.administrativedivision.internal

import com.google.common.io.{InputSupplier}
import java.io.InputStreamReader
import grizzled.slf4j.Logging
import com.sirika.openplacesearch.api.administrativedivision._
import com.sirika.commons.scala.{InputStreamReaderTransformer, Urls, ParsingWarning}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

class InMemoryAdministrativeDivisionRepository extends AdministrativeDivisionRepository with Logging {
  private[this] val countryRepository = new InMemoryCountryRepository()
  private[this] object FirstOrderAdministrativeDivisions {
    val allFirstOrderAdministrativeDivisions = parseAdm1(Urls.toInputReaderSupplier("com/sirika/openplacesearch/api/administrativedivision/admin1CodesASCII"))
    val allFirstOrderAdministrativeDivisionsPerCountry: Map[Country,List[AdministrativeDivision]] = allFirstOrderAdministrativeDivisions.groupBy (_.country)
    val adm1LookupTable: Map[(Country,String),AdministrativeDivision] = Map(allFirstOrderAdministrativeDivisions.map{a : AdministrativeDivision => ((a.country,a.code), a)} : _*)

    private def parseAdm1(readerSupplier: InputSupplier[InputStreamReader]) : List[AdministrativeDivision] = {
      new InputStreamReaderTransformer(readerSupplier).map { (line, lineNumber) =>
        line.split('\t') match {
          case Array(compositeCode, name, asciiName, geonamesId)
          =>
            val Array(countryAlpha2Code,adminCode) = compositeCode.split('.')
            val parentAdministrativeEntity = countryRepository.getByIsoAlpha2Code(countryAlpha2Code)

            Right(AdministrativeDivision(
              code=adminCode,
              featureNameProvider=
                SimpleFeatureNameProvider(
                  defaultName = if(name.nonEmpty) name else asciiName,
                  parentAdministrativeEntity=Some(parentAdministrativeEntity)),
              parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(Some(parentAdministrativeEntity))))
          case _ => throw new IllegalArgumentException("Syntax error in the input file. We are expecting the following fields: compositeCode, name, asciiName, geonamesId")
        }
      }
    }
  }

  private[this] object SecondOrderAdministrativeDivisions {
    val allSecondOrderAdministrativeDivisions = parseAdm2(Urls.toInputReaderSupplier("com/sirika/openplacesearch/api/administrativedivision/admin2Codes"))
    val allSecondOrderAdministrativeDivisionPerCountryAndAdm1: Map[(Country,AdministrativeDivision),List[AdministrativeDivision]] =
      allSecondOrderAdministrativeDivisions.groupBy {a=>(a.country,a.parentAdministrativeEntity.get.asInstanceOf[AdministrativeDivision])}
    val adm2LookupTable: Map[(Country,AdministrativeDivision, String),AdministrativeDivision] =
      Map(allSecondOrderAdministrativeDivisions.map{a : AdministrativeDivision =>
        ((a.country,a.parentAdministrativeEntity.get.asInstanceOf[AdministrativeDivision], a.code), a)} : _*)

    private def parseAdm2(readerSupplier: InputSupplier[InputStreamReader]) : List[AdministrativeDivision] = {
      new InputStreamReaderTransformer(readerSupplier).map { (line, lineNumber) =>
        line.split('\t') match {
          case Array(compositeCode, name, asciiName, geonamesId)
          =>
            val Array(countryAlpha2Code,adm1Code,adm2Code) = compositeCode.split('.')
            val country = countryRepository.getByIsoAlpha2Code(countryAlpha2Code)
            val adm1 = getFirstOrderAdministrativeDivisionOption(country, adm1Code)


            adm1 match {
              case None =>
                Left(ParsingWarning("ADM1 with code %s from country %s does not exist. Cannot import ADM2 properly(%s,%s)".format(adm1Code, countryAlpha2Code, adm2Code, name)))
              case _ =>
                Right(AdministrativeDivision(
                  code=adm2Code,
                  featureNameProvider=
                    SimpleFeatureNameProvider(
                      defaultName = if(name.nonEmpty) name else asciiName,
                      parentAdministrativeEntity=adm1),
                  parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(adm1)))
            }


          case _ => throw new IllegalArgumentException("Syntax error in the input file. We are expecting the following fields: compositeCode, name, asciiName, geonamesId")
        }
      }
    }
  }

  //private val fipsLookupTable : Map[String, Country] = Map(countries.filter{_.fipsCountryCode.fipsCode.isDefined}.map{c : Country => (c.fipsCountryCode.fipsCode.get, c)} : _*)
  //private val alpha2LookupTable : Map[String, Country] = Map(countries.map{c : Country => (c.isoCountryCode.alpha2Code, c)} : _*)
  //private val alpha3LookupTable : Map[String, Country] = Map(countries.map{c : Country => (c.isoCountryCode.alpha3Code, c)} : _*)

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