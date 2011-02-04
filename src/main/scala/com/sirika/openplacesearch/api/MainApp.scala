package com.sirika.openplacesearch.api.language
import com.google.common.base.Charsets
import com.google.common.io.{Resources,CharStreams,LineProcessor}
import com.sirika.openplacesearch.api.language.internal.InMemoryLanguageRepository
object MainApp {
  def main(args : Array[String]) : Unit = {
    val languageRepository : LanguageRepository = new InMemoryLanguageRepository()
    val language = languageRepository.getByAlpha3Code("fra");
    println(language)
  }
}
