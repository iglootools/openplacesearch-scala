package org.iglootools.openplacesearch.api.feature

import org.joda.time.DateTimeZone

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
trait TimeZoneProvider {
  def timeZone : Option[DateTimeZone]
}