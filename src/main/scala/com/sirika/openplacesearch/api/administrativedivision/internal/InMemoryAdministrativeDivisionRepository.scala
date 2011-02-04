package com.sirika.openplacesearch.api.administrativedivision.internal

import com.google.common.io.{Resources, LineProcessor, CharStreams, InputSupplier}
import java.io.InputStreamReader
import com.google.common.base.Charsets
import grizzled.slf4j.Logging
import com.sirika.openplacesearch.api.administrativedivision._
import com.sirika.commons.scala.{InputStreamReaderTransformer, Urls}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

class InMemoryAdministrativeDivisionRepository extends AdministrativeDivisionRepository with Logging {
  private[this] val countryRepository = new InMemoryCountryRepository()

  private lazy val adm1 = importAdm1FromClassPath()

  importAdm1FromClassPath()

  //private val fipsLookupTable : Map[String, Country] = Map(countries.filter{_.fipsCountryCode.fipsCode.isDefined}.map{c : Country => (c.fipsCountryCode.fipsCode.get, c)} : _*)
  //private val alpha2LookupTable : Map[String, Country] = Map(countries.map{c : Country => (c.isoCountryCode.alpha2Code, c)} : _*)
  //private val alpha3LookupTable : Map[String, Country] = Map(countries.map{c : Country => (c.isoCountryCode.alpha3Code, c)} : _*)

  def getAllSecondOrderAdministrativeDivisions(country: Country, firstOrderAdministrativeDivision: AdministrativeDivision): List[AdministrativeDivision] = null
  def getSecondOrderAdministrativeDivision(country: Country, firstOrderAdministrativeDivision: AdministrativeDivision, code: String): AdministrativeDivision = null
  def getAllFirstOrderAdministrativeDivisions(country: Country): List[AdministrativeDivision] = null
  def getFirstOrderAdministrativeDivision(country: Country, code: String): AdministrativeDivision = null

  private def importAdm1FromClassPath() : List[AdministrativeDivision ] = {
    parseAdm1(Urls.toInputReaderSupplier("com/sirika/openplacesearch/api/administrativedivision/admin1CodesASCII"))
  }

  private def parseAdm1(readerSupplier: InputSupplier[InputStreamReader]) : List[AdministrativeDivision] = {
    new InputStreamReaderTransformer(readerSupplier).map { (line, lineNumber) =>
      line.split('\t') match {
        case Array(compositeCode, name, asciiName, geonamesId)
        =>
          val Array(countryAlpha2Code,adminCode) = compositeCode.split('.')
          val parentAdministrativeEntity = countryRepository.getByIsoAlpha2Code(countryAlpha2Code)

          Some(AdministrativeDivision(
            code=adminCode,
            featureNameProvider=
              SimpleFeatureNameProvider(
                defaultName = if(name.nonEmpty) name else asciiName,
                parentAdministrativeEntity=Some(parentAdministrativeEntity)),
            parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(Some(parentAdministrativeEntity))))
        case _ => throw new IllegalArgumentException("Error processing line: %s".format(line))
      }
    }

  }

  /**
   * trims the value, and returns an Option :
   * <ul>
   *  <li>None if the string is empty (after trimming)</li>
   *  <li>Some() on what the result callback returns (by default, the string itself)</li>
   * </ul>
   */
  private def someIfNonEmpty[T](value: String, result: (String)=> T = {s:String => s}):Option[T] = value.trim match {
    case s if s.isEmpty => None
    case s:String => Some(result(s))
  }

}