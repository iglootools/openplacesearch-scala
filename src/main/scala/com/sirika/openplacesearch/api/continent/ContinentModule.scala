package com.sirika.openplacesearch.api.continent

import com.google.inject.AbstractModule
import internal.InMemoryContinentRepository

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

class ContinentModule extends AbstractModule {
  def configure: Unit = {
    bind(classOf[ContinentRepository]).to(classOf[InMemoryContinentRepository])
  }
}