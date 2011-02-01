package com.sirika.openplacesearch.api.feature

import com.sirika.openplacesearch.api.language.Language
;

/**
 * @param preferred whether this name is marked as being the preferred one among all localized names
 * @param shortName whether this is a short name, such as California (for State of California)
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
final case class LocalizedName(val name: String,
                               val language: Option[Language],
                               val preferred: Boolean = false,
                               val shortName: Boolean = false) extends Ordered[LocalizedName] {
  require(Option(name) exists {_.nonEmpty}, "name is required")
  require(language != null, "language must be a non-null Option")

  def compare(that: LocalizedName) = name.compare(that.name)
}