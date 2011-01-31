package com.sirika.openplacesearch.api.gisfeature

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait NameFormatter {
  def formatName(nameProvider: FeatureNameProvider)
}