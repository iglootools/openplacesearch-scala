package com.sirika.openplacesearch.api.continent

import com.google.common.base.Objects
object Continent {
  def apply(geonamesCode : String, name : String) = new Continent(geonamesCode, name)
  def unapply(c : Continent) = Some(c.geonamesCode , c.name)
}

final class Continent(val geonamesCode: String, val name : String) {
  require(Option(geonamesCode) exists {_.nonEmpty})
  require(Option(name) exists {_.nonEmpty})

  override def hashCode() : Int = Objects.hashCode(geonamesCode)

  override def equals(other: Any) : Boolean = other match {
    case Continent(`geonamesCode`, _) => true
    case _ => false
  }

  override def toString() : String = Objects.toStringHelper(this)
    .add("geonamesCode", geonamesCode)
    .add("name", name)
    .toString()

}