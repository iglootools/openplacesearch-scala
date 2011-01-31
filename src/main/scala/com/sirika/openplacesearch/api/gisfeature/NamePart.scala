package com.sirika.openplacesearch.api.gisfeature

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class NamePart(val name : String, val userFriendlyCode: Option[String]) {
  require(Option(name) exists (_.nonEmpty), "name is required")
  require(userFriendlyCode != null, "userFriendlyCode must be a non-null Option")
}