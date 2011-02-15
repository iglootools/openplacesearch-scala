package com.sirika.openplacesearch.api.gisgraphy

import resultparser.{ResultParser, FullTextResultParser}
import com.sirika.openplacesearch.api.administrativedivision.{AdministrativeDivisionRepository, CountryRepository}
import com.sirika.openplacesearch.api.language.LanguageRepository

protected[gisgraphy] final class FullTextQuery(private[this] val query: String,
                          private[this] val pagination: Pagination)
                         (implicit protected[this] val gisgraphyServer: GisgraphyServer,
                          implicit protected[this] val countryRepository: CountryRepository,
                          implicit protected[this] val administrativeDivisionRepository: AdministrativeDivisionRepository,
                          implicit protected[this] val languageRepository: LanguageRepository) extends GisgraphyQuery with UrlGenerator {
  require(Option(query) exists(_.nonEmpty), "query is required")
  require(pagination != null, "pagination is required")

  def toUrl(baseUrl: String): String = {
    "%s/fulltext/fulltextsearch?q=%s&placetype=City&country=&spellchecking=true&__checkbox_spellchecking=true&lang=&format=XML&style=FULL&__checkbox_indent=true&from=%d&to=%d".format(
      baseUrl,
      query,
      pagination.firstResult,
      pagination.endResult
    )
  }

  protected def resultParser: ResultParser = new FullTextResultParser
}