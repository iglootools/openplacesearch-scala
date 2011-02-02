package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.continent.Continents
import com.sirika.openplacesearch.api.geonames._
import com.sirika.openplacesearch.api.feature._
import org.joda.time.DateTimeZone
import com.ibm.icu.util.{ULocale, Currency}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object Places {
  object France {
    def gazeran: Place = {
      val gisFeature = GisFeature(
        featureGeography=FeatureGeography(
          location=JtsPoint(1.76667,48.63333),
          population=Some(1219L),
          gTopo30ElevationInMeters=Some(161L),
          elevationInMeters=None,
          timeZone=Some(DateTimeZone.forID("Europe/Paris"))),
        geonamesFeature=GeonamesFeature(geonamesId=3016456L, geonamesFeatureCategory = GeonamesFeatureCategory(featureClass="P", featureCode="PPL")),
        featureNames=FeatureNames(defaultName="Gazeran"),
        parentAdministrativeEntity=Some(AdministrativeDivisions.France.gazeranAdm4))

      Place(
        parentAdministrativeEntityProvider=gisFeature,
        featureGeographyProvider=gisFeature,
        featureNameProvider=gisFeature,
        stableIdProvider=gisFeature)
    }
  }

}