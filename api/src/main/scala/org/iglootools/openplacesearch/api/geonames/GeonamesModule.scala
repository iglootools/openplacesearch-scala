package org.iglootools.openplacesearch.api.geonames

import com.google.inject.{AbstractModule,Singleton}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final class GeonamesModule extends AbstractModule {
  def configure: Unit = {
    bind(classOf[StableIDMapper]).to(classOf[GeonamesStableIDMapper]).in(classOf[Singleton])
  }
}