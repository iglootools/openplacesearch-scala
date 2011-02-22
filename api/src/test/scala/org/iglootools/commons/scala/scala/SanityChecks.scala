package org.iglootools.commons.scala

import org.scalatest.matchers.ShouldMatchers

object SanityChecks extends ShouldMatchers {

  def hashCodeAndEqualsShouldBeSane(obj: AnyRef, eqObj: AnyRef, neObj: AnyRef) {
    require(obj.ne(eqObj), "different instances must be passed")
    require(obj.ne(neObj), "different instances must be passed")

    obj should be === obj
    obj.hashCode should be === obj.hashCode

    obj should be === eqObj
    obj.hashCode should be === eqObj.hashCode

    obj should not be neObj
    obj.hashCode should not be neObj.hashCode
  }
}