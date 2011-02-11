package com.sirika.openplacesearch.api.gisgraphy.http;



final class FullTextQuery(private[this] val query: String,
                               private[this] val pagination: Pagination=Pagination(firstResult=1,numberOfResults=10)) {
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
}