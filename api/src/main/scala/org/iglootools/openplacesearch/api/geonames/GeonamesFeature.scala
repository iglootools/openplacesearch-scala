package org.iglootools.openplacesearch.api.geonames

import org.iglootools.openplacesearch.api.feature.StableIdProvider
import com.google.common.base.Objects

protected[openplacesearch] object GeonamesFeature {
  def apply(geonamesId: Long, geonamesFeatureCategory: GeonamesFeatureCategory)(implicit stableIDMapper: StableIDMapper): GeonamesFeature = {
    new GeonamesFeature(geonamesId=geonamesId, geonamesFeatureCategory=geonamesFeatureCategory)
  }
}
/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
protected[openplacesearch] final class GeonamesFeature(val geonamesId: Long,
                                                       val geonamesFeatureCategory: GeonamesFeatureCategory)
                                                       (implicit private[this] val stableIDMapper: StableIDMapper)
  extends GeonamesFeatureProvider with StableIdProvider {
  require(geonamesFeatureCategory != null, "geonamesFeatureCategory is required")

  protected def geonamesFeature: GeonamesFeatureProvider = this

  def stableId: String = {
    val idAsString = String.valueOf(geonamesFeature.geonamesId)
    stableIDMapper.stableID(idAsString).getOrElse(idAsString)
  }

  override def equals(other: Any): Boolean = other match {
    case g: GeonamesFeature if Objects.equal(stableId, g.stableId) => true
    case _ => false
  }

  override def hashCode: Int = Objects.hashCode(stableId)
}