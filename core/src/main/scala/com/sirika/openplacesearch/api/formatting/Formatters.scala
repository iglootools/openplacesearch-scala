package com.sirika.openplacesearch.api.formatting

import internal._

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object Formatters {
  def fullyQualifiedNameFormatter(separator: String): NameFormatter =
    new ConcatenatingNameFormatter(separator = separator, namesExtractor=new FullyQualifiedNamesExtractor())

  def shortQualifiedNameFormatter(separator: String): NameFormatter =
    new ConcatenatingNameFormatter(separator = separator, namesExtractor=new ShortQualifiedNamesExtractor())

  def veryShortQualifiedNameFormatter(separator: String): NameFormatter =
    new ConcatenatingNameFormatter(separator = separator, namesExtractor=new VeryShortQualifiedNamesExtractor())

  def shortUsStyleNameFormatter(separator: String): NameFormatter =
    new ConcatenatingNameFormatter(separator = separator, namesExtractor=new ShortUsStyleNamesExtractor())

  def longUsStyleNameFormatter(separator: String): NameFormatter =
    new ConcatenatingNameFormatter(separator = separator, namesExtractor=new ShortQualifiedNamesExtractor())
}