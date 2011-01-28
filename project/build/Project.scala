import sbt._
import de.element34.sbteclipsify._
 
class Project(info: ProjectInfo) extends DefaultProject(info) with Eclipsify with IdeaProject {
    //val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"
    val geotoolsRepository = "Open Source Geospatial Foundation Repository" at "http://download.osgeo.org/webdav/geotools/"
    val javanetRepository = "Java.net Repository" at "http://download.java.net/maven/2"
    
    // commons
    val jodaTime = "joda-time" % "joda-time" % "1.6.2" withSources()
    val icu4j = "com.ibm.icu" % "icu4j" % "4.6" withSources()
    val slf4jApi = "org.slf4j" % "slf4j-api" % "1.6.1" withSources()
    //val grizzled = "org.clapper" %% "grizzled-slf4j" % "0.3.2" // withSources()
    
    
    // HTTP and REST
    val HttpComponentsVersion = "4.1"
    val hcHelpers = "com.sirika.hchelpers" % "hchelpers-core" % "0.8" withSources()
    val httpComponentsClient = "org.apache.httpcomponents" % "httpclient" % HttpComponentsVersion withSources()
    val httpComponentsMime = "org.apache.httpcomponents" % "httpmime" % HttpComponentsVersion withSources()
    
    
    // GIS
    val jts = "com.vividsolutions" % "jts" % "1.8" // withSources()
    val geotools = "org.geotools" % "gt-main" % "2.6.5" withSources() // depends geotools and java.net repositories
    
    // test dependencies
    val junit = "junit" % "junit" % "4.8.2" % "test" withSources()
    val scalaTest = "org.scalatest" % "scalatest" % "1.2"  % "test" withSources()
    val mockito = "org.mockito" % "mockito-all" % "1.8.5" % "test" withSources()
    //val avsl = "org.clapper" %% "avsl" % "0.3.1" % "test" // withSources()
    val logback = "ch.qos.logback" % "logback-classic" % "0.9.27" % "test" // withSources()
    
    // hacks
    // needed for guava+scala-IDE : javax.annotation.Nullable 
    val jsr305 = "com.google.code.findbugs" % "jsr305" % "1.3.9" //withSources()
}
