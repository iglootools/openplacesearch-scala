package com.sirika.openplacesearch.api.commons

import org.joda.time.DateTimeZone

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
trait TimeZoneProvider {
  def timeZone : Option[DateTimeZone]
}