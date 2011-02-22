package org.iglootools.openplacesearch.api.formatting

import org.iglootools.openplacesearch.api.feature.FeatureNameProvider

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait NameFormatter {
  def formatName(nameProvider: FeatureNameProvider): String
}