package com.sirika.openplacesearch.api.feature

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.language.Languages

@RunWith(classOf[JUnitRunner])
class FeatureNamesSpec extends Spec with ShouldMatchers {

  describe("FeatureNames") {
    it("should find preferred name of given language when present") {
      val featureNames = FeatureNames(
        defaultName="default",
        localizedNamesSupplier=
          new InMemoryLocalizedNamesSupplier(
            localizedNames=List(
              LocalizedName(name="some name in english", language = Some(Languages.english), preferred = false, shortName=false),
              LocalizedName(name="some preferred name in french", language = Some(Languages.french), preferred = true, shortName=false),
              LocalizedName(name="some name in french", language = Some(Languages.english), preferred = false, shortName=false),
              LocalizedName(name="preferred", language = Some(Languages.english), preferred = true, shortName=false))))
      featureNames.preferredName(Languages.english) should be === "preferred"
    }

    it("should return default name when preferred name in a given language is when present") {
      val featureNames = FeatureNames(
        defaultName="default",
        localizedNamesSupplier=
          new InMemoryLocalizedNamesSupplier(
            localizedNames=List(
              LocalizedName(name="some name in english", language = Some(Languages.english), preferred = false, shortName=false),
              LocalizedName(name="some preferred name in french", language = Some(Languages.french), preferred = true, shortName=false),
              LocalizedName(name="some name in french", language = Some(Languages.english), preferred = false, shortName=false))))
      featureNames.preferredName(Languages.english) should be === "default"
    }

    it("should find short name of given language when present") {
      val featureNames = FeatureNames(
        defaultName="default",
        localizedNamesSupplier=
          new InMemoryLocalizedNamesSupplier(
            localizedNames=List(
              LocalizedName(name="some name in english", language = Some(Languages.english), preferred = false, shortName=false),
              LocalizedName(name="some short name in french", language = Some(Languages.french), preferred = false, shortName=true),
              LocalizedName(name="some name in french", language = Some(Languages.english), preferred = false, shortName=false),
              LocalizedName(name="short", language = Some(Languages.english), preferred = false, shortName=true))))
      featureNames.shortName(Languages.english) should be === "short"
    }

    it("should return default name when no short name of given language when present") {
      val featureNames = FeatureNames(
        defaultName="default",
        localizedNamesSupplier=
          new InMemoryLocalizedNamesSupplier(
            localizedNames=List(
              LocalizedName(name="some name in english", language = Some(Languages.english), preferred = false, shortName=false),
              LocalizedName(name="some short name in french", language = Some(Languages.french), preferred = false, shortName=true),
              LocalizedName(name="some name in french", language = Some(Languages.english), preferred = false, shortName=false))))
      featureNames.shortName(Languages.english) should be === "default"
    }

    it("should return default name when no localized name is available") {
      val featureNames = FeatureNames(defaultName="default")
      featureNames.shortName(Languages.english) should be === "default"
    }
  }
}