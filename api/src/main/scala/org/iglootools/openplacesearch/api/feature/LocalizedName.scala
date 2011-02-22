package org.iglootools.openplacesearch.api.feature

import org.iglootools.openplacesearch.api.language.Language
;

/**
 * @param preferred whether this name is marked as being the preferred one among all localized names
 * @param shortName whether this is a short name, such as California (for State of California)
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
final case class LocalizedName(val name: String,
                               val language: Language,
                               val preferred: Boolean = false,
                               val shortName: Boolean = false) extends Ordered[LocalizedName] {
  require(Option(name) exists {_.nonEmpty}, "name is required")
  require(language != null, "language is required.")

  def compare(that: LocalizedName) = name.compare(that.name)
}