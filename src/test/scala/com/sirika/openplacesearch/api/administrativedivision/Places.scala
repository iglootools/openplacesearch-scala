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
    object IleDeFrance {
      object Yvelines {
        def gazeran: Place = placeBasedOn(
          GisFeature(
            featureGeography=FeatureGeography(
              location=JtsPoint(1.76667,48.63333),
              population=Some(1219L),
              gTopo30ElevationInMeters=Some(161L),
              elevationInMeters=None,
              timeZone=Some(DateTimeZone.forID("Europe/Paris"))),
            geonamesFeature=GeonamesFeature(geonamesId=3016456L, geonamesFeatureCategory = GeonamesFeatureCategory(featureClass="P", featureCode="PPL")),
            featureNames=FeatureNames(defaultName="Gazeran"),
            parentAdministrativeEntity=Some(AdministrativeDivisions.France.IleDeFrance.Yvelines.gazeran)))

        def rambouillet: Place = placeBasedOn(
          GisFeature(
            featureGeography=FeatureGeography(
              location=JtsPoint(1.8333332538604736,48.650001525878906),
              population=Some(0L),
              gTopo30ElevationInMeters=Some(161L),
              elevationInMeters=None,
              timeZone=Some(DateTimeZone.forID("Europe/Paris"))),
            geonamesFeature=GeonamesFeature(geonamesId=2984513L, geonamesFeatureCategory = GeonamesFeatureCategory(featureClass="P", featureCode="PPL")),
            featureNames=FeatureNames(defaultName="Rambouillet"),
            parentAdministrativeEntity=Some(AdministrativeDivisions.France.IleDeFrance.Yvelines.rambouillet)))

      }

    }

  }

  object UnitedStates {
    object California {
      object LosAngelesCounty {
        def losAngeles: Place = placeBasedOn(
          GisFeature(
            featureGeography=FeatureGeography(
              location=JtsPoint(-118.24368286132812,34.0522346496582),
              population=Some(3694820L),
              gTopo30ElevationInMeters=Some(115L),
              elevationInMeters=Some(89L),
              timeZone=Some(DateTimeZone.forID("America/Los_Angeles"))),
            geonamesFeature=GeonamesFeature(geonamesId=5368361L, geonamesFeatureCategory = GeonamesFeatureCategory(featureClass="P", featureCode="PPL")),
            featureNames=FeatureNames(defaultName="Los Angeles"),
            parentAdministrativeEntity=Some(AdministrativeDivisions.UnitedStates.California.losAngelesCounty)))
      }
    }
  }

  def placeBasedOn(gisFeature: GisFeature): Place = Place(
    parentAdministrativeEntityProvider=gisFeature,
    featureGeographyProvider=gisFeature,
    featureNameProvider=gisFeature,
    stableIdProvider=gisFeature)
}