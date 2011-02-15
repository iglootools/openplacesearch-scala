package com.sirika.openplacesearch.api.language

import com.google.inject.AbstractModule
import internal.InMemoryLanguageRepository

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final class LanguageModule extends AbstractModule {
  def configure: Unit = {
    bind(classOf[LanguageRepository]).to(classOf[InMemoryLanguageRepository])
  }
}