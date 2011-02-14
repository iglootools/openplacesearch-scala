package com.sirika.openplacesearch.api.gisgraphy

import resultparser.ResultParser
import com.sirika.openplacesearch.api.administrativedivision.{AdministrativeDivisionRepository, CountryRepository, Place}
import com.sirika.openplacesearch.api.language.LanguageRepository

trait GisgraphyQuery {
  self: UrlGenerator =>

  protected def gisgraphyServer: GisgraphyServer
  protected def resultParser: ResultParser

  def execute(): List[Place] = {
    gisgraphyServer.execute(urlGenerator = self, resultParser = resultParser)
  }
}