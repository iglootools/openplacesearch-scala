package com.sirika.openplacesearch.api.administrativedivision.internal

import com.sirika.openplacesearch.api.administrativedivision.{Country, IsoCountryCode, FipsCountryCode, CountryAdministrativeInformation,CountryGeographicInformation, CountryRepository, SimpleFeatureNameProvider}
import com.google.common.io.{Resources, LineProcessor, CharStreams, InputSupplier}
import java.io.InputStreamReader
import com.sirika.commons.scala.Urls
import com.google.common.base.Charsets
import grizzled.slf4j.Logging
import com.ibm.icu.util.{ULocale, Currency}
import com.sirika.openplacesearch.api.continent.internal.InMemoryContinentRepository

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

class InMemoryCountryRepository extends CountryRepository with Logging {
  def findAll(): List[Country] = null
  def getByFipsCode(code: String): Country = null
  def getByIsoAlpha3Code(code: String): Country = null
  def getByIsoAlpha2Code(code: String): Country = null

  importCountriesFromClassPath

  private def importCountriesFromClassPath() : List[Country ] = {
    val inputStreamSupplier = Resources.newInputStreamSupplier(Urls.classpath("com/sirika/openplacesearch/api/administrativedivision/countries"))
    val inputReaderSupplier = CharStreams.newReaderSupplier(inputStreamSupplier, Charsets.UTF_8)
    parseCountries(inputReaderSupplier)
  }

  private def parseCountries(readerSupplier: InputSupplier[InputStreamReader]) : List[Country] = {
    // ISO,ISO3,ISO-Numeric,fips,Country,Capital,Area(in sq km),Population,Continent,tld,CurrencyCode,CurrencyName,Phone,Postal Code Format,Postal Code Regex,Languages,geonameid,neighbours,EquivalentFipsCode
    CharStreams.readLines(readerSupplier, new LineProcessor[List[Country]]() {
      private[this] val continentRepository = new InMemoryContinentRepository()
      private[this] var countries : List[Country] = Nil
      private[this] var countryGeonamesId: Map[Country,Long]= Map()
      private[this] var lineNumber = 1

      def getResult() : List[Country] = countries
      def processLine(line : String) : Boolean = {
        debug("Processing line[%d]: %s".format(lineNumber, line))

        line match {
          case s:String if s.startsWith("#") => debug("Ignoring line [%d]: comment".format(lineNumber))
          case s:String if s.isEmpty => debug("Ignoring line[%d]: empty".format(lineNumber))
          case l:String =>
            debug("count (line, l): " + line.count(_ == '\t') + "," + l.count(_ == '\t'))
            debug("number of elements: " + l.split('\t').size)
            sanitizeLineSplit(l.split('\t')) match {
              case List(isoAlpha2CountryCode,isoAlpha3CountryCode,isoNumericCountryCode,fipsCountryCode,countryName,
              capitalName,areaInSquareMeters,population,continentCode,topLevelDomain,currencyCode,currencyName,
              phonePrefix,postalCodeMask,postalCodeRegex,preferredLocales,geonamesId,neighbours, equivalentFipsCode)
              =>
                val c=Country(
                  isoCountryCode=
                    IsoCountryCode(
                      alpha3Code=isoAlpha3CountryCode,
                      alpha2Code=isoAlpha2CountryCode,
                      numeric=isoNumericCountryCode.toInt),
                  continent=continentRepository.getByGeonamesCode(continentCode),
                  featureNameProvider= SimpleFeatureNameProvider(defaultName = countryName, parentAdministrativeEntity=None),
                  currency=someIfNonEmpty(currencyCode, {c => Currency.getInstance(c)}),
                  fipsCountryCode=
                    FipsCountryCode(
                      fipsCode=someIfNonEmpty(fipsCountryCode),
                      equivalentFipsCode=someIfNonEmpty(equivalentFipsCode)),
                  countryAdministrativeInformation=
                    CountryAdministrativeInformation(
                      preferredLocales=toLocales(preferredLocales),
                      topLevelDomain=someIfNonEmpty(topLevelDomain),
                      phonePrefix=someIfNonEmpty(phonePrefix),
                      postalCodeRegex=someIfNonEmpty(postalCodeRegex),
                      postalCodeMask=someIfNonEmpty(postalCodeMask)),
                  countryGeographicInformation=
                    CountryGeographicInformation(
                      population = someIfNonEmpty(population, p=> p.toLong),
                      areaInSquareKilometers = someIfNonEmpty(areaInSquareMeters, a => a.toDouble)))

                debug("Created Country: " + c)
                countries ::= c
              case _ => throw new IllegalArgumentException("Error processing line[%d]: %s".format(lineNumber, line))
            }
        }

        lineNumber+=1
        true
      }
    })
  }

  private def sanitizeLineSplit(line: Array[String]):List[String] = {
    val ExpectedNumberOfFields = 19
    def actualNumberOfFields = line.size
    def numberOfMissingFields = ExpectedNumberOfFields-actualNumberOfFields

    line.toList ++ List.fill(numberOfMissingFields)("")
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

  private def toLocales(preferredLocalesAsString: String): List[ULocale] = {
    preferredLocalesAsString.split(",").map { localeAsString =>
      new ULocale(localeAsString.replaceAll("-", "_"))
    }.toList
  }
}