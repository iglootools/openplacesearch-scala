package org.iglootools.openplacesearch.api.gisgraphy;
import scala.collection.immutable.TreeMap
import xml.XML
import org.iglootools.openplacesearch.api.referencedata.ReferenceData
import org.iglootools.commons.scala.io.{InputSupliers, Urls}
import java.io.InputStream
import grizzled.slf4j.Logging

class GisgraphyStableIDMapper extends StableIDMapper with Logging {

  val Mapping: Map[String,String] = parseAliases
  val Reverse: Map[String,String] = Mapping.map { case (k,v) => (v,k) }

  if(Mapping.size != Reverse.size) {
    reportDebugInformation
  }
  assume(Mapping.size == Reverse.size,"Duplicate entries are not allowed: " + Mapping.size + " vs " + Reverse.size)

  def originalID(stableID: String): Option[String] = Reverse.get(stableID)
  def stableID(originalID: String): Option[String] = Mapping.get(originalID)

  private def parseAliases: Map[String,String] = {
    InputSupliers.doWithInputStream(Urls.toInputStreamSupplier(ReferenceData.GeonamesIDAliases)) { is:InputStream =>
      Map((XML.load(is) \\ "alias") map { case n => ((n \ "@from").text, (n \ "@to").text)} : _*)
    }
  }

  private def reportDebugInformation: Unit = {
    InputSupliers.doWithInputStream(Urls.toInputStreamSupplier(ReferenceData.GeonamesIDAliases)) { is:InputStream =>
      var aliases: Set[String] = Set()
      (XML.load(is) \\ "alias") map { case n => ((n \ "@from").text, (n \ "@to").text)} foreach { case (from,to) =>
        if(aliases.contains(to)) {
          error("Alias [%s] is duplicated: %s".format(to, from))
        } else {
          aliases = aliases + to
        }
      }
    }
  }
}