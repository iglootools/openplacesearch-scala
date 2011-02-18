import sbt._
import de.element34.sbteclipsify._

class Project(info: ProjectInfo) extends ParentProject(info) with Eclipsify with IdeaProject {
  trait Repositories {
    lazy val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"
    lazy val geotoolsRepository = "Open Source Geospatial Foundation Repository" at "http://download.osgeo.org/webdav/geotools/"
    lazy val javanetRepository = "Java.net Repository" at "http://download.java.net/maven/2"
  }
  object Dependencies {
    // test dependencies
    lazy val junit = "junit" % "junit" % "4.8.2" % "test" withSources()
    lazy val scalaTest = "org.scalatest" % "scalatest" % "1.2"  % "test" withSources()
    lazy val mockito = "org.mockito" % "mockito-all" % "1.8.5" % "test" withSources()
    //val avsl = "org.clapper" %% "avsl" % "0.3.1" % "test" // withSources()
    lazy val logback = "ch.qos.logback" % "logback-classic" % "0.9.27" % "test" // withSources()
  }

  override def parallelExecution = true

  lazy val core = project("core", "openplacesearch-core", new Core(_))
  lazy val updater = project("updater", "openplacesearch-updater", new Updater(_), core)
  lazy val integrationTests = project("integration-tests", "openplacesearch-integration-tests", new IntegrationTests(_), core)


  lazy val skipIntegrationTests = systemOptional[Boolean]("skipIntegrationTests", false).value
  lazy val printit = task {
    println("SKIP INTEGRATION TESTS: " + skipIntegrationTests)
    None
  }

  class Core(info: ProjectInfo) extends DefaultProject(info) with Repositories with IdeaProject with Eclipsify {
    // commons
    lazy val jodaTime = "joda-time" % "joda-time" % "1.6.2" withSources()
    lazy val icu4j = "com.ibm.icu" % "icu4j" % "4.6" withSources()
    lazy val slf4jApi = "org.slf4j" % "slf4j-api" % "1.6.1" withSources()
    lazy val grizzled = "org.clapper" %% "grizzled-slf4j" % "0.4" // withSources()

    // IoC
    lazy val guice = "com.google.code.guice" % "guice" % "1.0" withSources()

    // HTTP and REST
    lazy val HttpComponentsVersion = "4.1"
    lazy val hcHelpers = "com.sirika.hchelpers" % "hchelpers-scala" % "0.9" withSources()
    lazy val httpComponentsClient = "org.apache.httpcomponents" % "httpclient" % HttpComponentsVersion withSources()
    lazy val httpComponentsMime = "org.apache.httpcomponents" % "httpmime" % HttpComponentsVersion withSources()

    // GIS
    lazy val jts = "com.vividsolutions" % "jts" % "1.8" // withSources()
    lazy val geotools = "org.geotools" % "gt-main" % "2.6.5" withSources() // depends geotools and java.net repositories

    // hacks
    // needed for guava+scala-IDE : javax.annotation.Nullable
    lazy val jsr305 = "com.google.code.findbugs" % "jsr305" % "1.3.9" //withSources()

    // needed for guice
    lazy val aopalliance = "aopalliance" % "aopalliance" % "1.0" withSources()

    // test dependencies
    lazy val junit = Dependencies.junit
    lazy val scalaTest = Dependencies.scalaTest
    lazy val mockito = Dependencies.mockito
    lazy val logback = Dependencies.logback
  }

  class IntegrationTests(info: ProjectInfo) extends DefaultProject(info) with Repositories with IdeaProject with Eclipsify{
    def doNothing = task { None }
    override def publishLocalAction = doNothing
    override def deliverLocalAction = doNothing
    override def publishAction = doNothing
    override def deliverAction = doNothing

    override def testOptions = TestFilter(s => true) :: super.testOptions.toList

    // test dependencies
    lazy val junit = Dependencies.junit
    lazy val scalaTest = Dependencies.scalaTest
    lazy val logback = Dependencies.logback
  }

  class Updater(info: ProjectInfo) extends DefaultProject(info) with Repositories with IdeaProject with Eclipsify{
    def doNothing = task { None }
    override def publishLocalAction = doNothing
    override def deliverLocalAction = doNothing
    override def publishAction = doNothing
    override def deliverAction = doNothing
  }



}
