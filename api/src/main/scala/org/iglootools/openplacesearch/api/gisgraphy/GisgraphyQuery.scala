package org.iglootools.openplacesearch.api.gisgraphy

import resultparser.ResultParser
import org.iglootools.openplacesearch.api.administrativedivision.{Place}

trait GisgraphyQuery extends UrlGenerator {

  def pagination: Pagination

  protected def gisgraphyServer: GisgraphyServer
  protected def resultParser: ResultParser

  def execute: List[Place] = {
    gisgraphyServer.execute(query = this, resultParser = resultParser)
  }


}