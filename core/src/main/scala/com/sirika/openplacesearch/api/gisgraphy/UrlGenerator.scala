package com.sirika.openplacesearch.api.gisgraphy

trait UrlGenerator {
  def toUrl(baseUrl: String): String
}