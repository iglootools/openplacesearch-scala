import sbt._
import de.element34.sbteclipsify._
 
class MyProject(info: ProjectInfo) extends DefaultProject(info) with Eclipsify with IdeaProject {
    //val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"
    val hcHelpers = "com.sirika.hchelpers" % "hchelpers-core" % "0.8" withSources()
    
    // test dependencies
    val junit = "junit" % "junit" % "4.8.2" withSources()
    val scalaTest = "org.scalatest" % "scalatest" % "1.2"withSources()
}
