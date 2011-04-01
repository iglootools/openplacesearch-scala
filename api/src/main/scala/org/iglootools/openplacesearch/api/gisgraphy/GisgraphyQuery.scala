package org.iglootools.openplacesearch.api.gisgraphy

import resultparser.ResultParser
import org.iglootools.openplacesearch.api.administrativedivision.{Place}
import grizzled.slf4j.Logging

trait GisgraphyQuery extends UrlGenerator with Logging {

  def pagination: Pagination

  protected def gisgraphyServer: GisgraphyServer
  protected def resultParser: ResultParser

  def execute: List[Place] = {
    debug("Executing query: " + this)
    gisgraphyServer.execute(query = this, resultParser = resultParser)
  }


}