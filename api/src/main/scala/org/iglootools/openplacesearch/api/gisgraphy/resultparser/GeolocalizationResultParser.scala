package org.iglootools.openplacesearch.api.gisgraphy.resultparser

import java.io.{InputStream}
import xml.{Node, XML}
import org.iglootools.openplacesearch.api.administrativedivision.{AdministrativeDivisionRepository, CountryRepository, Place}
import org.iglootools.openplacesearch.api.language.LanguageRepository
import org.joda.time.DateTimeZone
import org.iglootools.openplacesearch.api.feature._
import org.iglootools.commons.scala.jts.JtsPoint
import org.iglootools.openplacesearch.api.StableIDMapper
import org.iglootools.openplacesearch.api.geonames._

/**
 * <p>
 * Does not close the inputStream !!
 * </p>
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
protected[gisgraphy] final class GeolocalizationResultParser(implicit  private[this] val countryRepository: CountryRepository,
                                        implicit private[this] val administrativeDivisionRepository: AdministrativeDivisionRepository,
                                        implicit private[this] val languageRepository: LanguageRepository,
                                        implicit private[this] val stableIDMapper: StableIDMapper) extends ResultParser {

  def toPlaces(inputStream: InputStream): List[Place] = {
    (XML.load(inputStream) \ "result").map { result => toPlace(result)}.toList
  }

  private def toPlace(root: Node) = {
    val countryCode = value(root, "countryCode")
    val adm1Code = value(root, "adm1Code")
    val adm2Code = option(root, "adm2Code")
    val adm3Code = option(root, "adm3Code")
    val adm4Code = option(root, "adm4Code")
    val featureClass = value(root, "featureClass")
    val featureCode = value(root, "featureCode")
    val featureId = value(root, "featureId").toLong
    val latitude = value(root, "lat").toDouble
    val longitude = value(root, "lng").toDouble
    val name = value(root, "name")
    val gtopo30 = option(root,"gtopo30").map(_.toLong)
    val elevation = option(root,"elevation").map(_.toLong) // no elevation in output (gisgraphy bug)
    val population = option(root, "population").map(_.toLong)
    val timezone = option(root, "timezone").map {s => DateTimeZone.forID(s)}

    val country = countryRepository.getByIsoAlpha2Code(countryCode)
    val adm1 = administrativeDivisionRepository.getFirstOrderAdministrativeDivision(country, adm1Code)
    val adm2 = adm2Code.map { c => administrativeDivisionRepository.getSecondOrderAdministrativeDivision(country, adm1, c)}
    // FIXME: adm3+4 ?

    GeonamesPlace(
      GisFeature(
        featureGeography=FeatureGeography(
          location=JtsPoint(longitude,latitude),
          population=population,
          gTopo30ElevationInMeters=gtopo30,
          elevationInMeters=elevation,
          timeZone=timezone),
        geonamesFeature=GeonamesFeature(geonamesId=featureId, geonamesFeatureCategory = GeonamesFeatureCategory(featureClass=featureClass, featureCode=featureCode)),
        featureNames=FeatureNames(defaultName=name), // alternate names not supported by gisgraphy geoloc result.
        parentAdministrativeEntity=adm2.orElse(Some(adm1))))
  }

  private def value(root: Node, element: String):String = {
    option(root, element).get
  }

  private def option(root: Node, element: String):Option[String] = {
    (root \ element).headOption.map(_.text)
  }
}