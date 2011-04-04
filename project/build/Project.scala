import java.io.File
import sbt._

class Project(info: ProjectInfo) extends ParentProject(info) with IdeaProject  {
//  lazy val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"
//  lazy val geotoolsRepository = "Open Source Geospatial Foundation Repository" at "http://download.osgeo.org/webdav/geotools/"
//  lazy val javanetRepository = "Java.net Repository" at "http://download.java.net/maven/2"
//  lazy val iglootoolsRepository = "Iglootools Releases Repository" at "http://developers.sirika.com/maven2/releases/"
    lazy val iglootoolsRepository = "Iglootools Releases Repository" at "http://www.iglootools.org/artifactory/iglootools-release"

  override def managedStyle = ManagedStyle.Maven

  override def pomExtra =
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        <distribution>repo</distribution>
      </license>
    </licenses>

  // Publishing
//  lazy val keyFile: File = (Path.userHome / ".ssh" / "id_rsa").asFile
//  lazy val publishTo = Resolver.sftp("Iglootools maven2", "iglootools.org", "/srv/http/iglootools.org/maven2/internal") as ("samokk", keyFile)
  val publishTo = "Iglootools" at "http://www.iglootools.org/artifactory/iglootools-release-local"
  Credentials(Path.userHome / ".ivy2" / ".credentials", log)

  // Project Definitions
  override def parallelExecution = true
  lazy val api = project("api", "openplacesearch-scala-api", new Api(_))
  lazy val updater = project("updater", "openplacesearch-updater", new Updater(_), api)
  lazy val integrationTests = project("integration-tests", "openplacesearch-integration-tests", new IntegrationTests(_), api)



  object Dependencies {
    // test dependencies
    lazy val junit = "junit" % "junit" % "4.8.2" % "test" withSources()
    lazy val scalaTest = "org.scalatest" % "scalatest" % "1.2"  % "test" withSources()
    lazy val mockito = "org.mockito" % "mockito-all" % "1.8.5" % "test" withSources()
    //val avsl = "org.clapper" %% "avsl" % "0.3.1" % "test" // withSources()
    lazy val logback = "ch.qos.logback" % "logback-classic" % "0.9.27" % "test" // withSources()

     def ivyXML =
      <dependencies>
          <override org="com.vividsolutions" rev="1.8" />
      </dependencies>
  }

  class Api(info: ProjectInfo) extends DefaultProject(info) with IdeaProject  {
    override def ivyXML = Dependencies.ivyXML
    //val docsArtifact = Artifact.javadoc("core")
    //override def packageToPublishActions = super.packageToPublishActions ++ Seq(packageSrc) //packageDocs,, packageSrc, packageTest, packageTestSrc,
    //lazy val sourceArtifact = Artifact.sources(artifactID)

    //override def packageDocsJar = defaultJarPath("-javadoc.jar")
    override def packageSrcJar = defaultJarPath("-sources.jar")
    override def packageTestJar = defaultJarPath("-tests.jar")
    //override def packageTestSrcJar = defaultJarPath("-tests.jar")
    lazy val sourceArtifact = Artifact.sources(artifactID)
    lazy val testsArtifact = Artifact(artifactID, "tests")
    //lazy val docsArtifact = Artifact.javadoc(artifactID)
    override def packageToPublishActions = super.packageToPublishActions ++ Seq(packageSrc, packageTest) //packageDocs, packageTestSrc


    // commons
    lazy val jodaTime = "joda-time" % "joda-time" % "1.6.2" withSources()
    lazy val icu4j = "com.ibm.icu" % "icu4j" % "4.6" withSources()
    lazy val slf4jApi = "org.slf4j" % "slf4j-api" % "1.6.1" withSources()
    lazy val grizzled = "org.clapper" %% "grizzled-slf4j" % "0.4" // withSources()

    // IoC
    lazy val guice = "com.google.code.guice" % "guice" % "1.0" withSources()

    // HTTP and REST
    lazy val HttpComponentsVersion = "4.1"
    lazy val hcHelpers = "org.iglootools.hchelpers" % "hchelpers-scala" % "0.10" withSources()
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

  class IntegrationTests(info: ProjectInfo) extends DefaultProject(info) with IdeaProject {
    override def ivyXML = Dependencies.ivyXML
    override def testOptions = TestFilter(s => true) :: super.testOptions.toList

    // test dependencies
    lazy val junit = Dependencies.junit
    lazy val scalaTest = Dependencies.scalaTest
    lazy val logback = Dependencies.logback
  }

  class Updater(info: ProjectInfo) extends DefaultProject(info) with IdeaProject{
    override def ivyXML = Dependencies.ivyXML
  }

  // Additional Tasks
  lazy val skipIntegrationTests = systemOptional[Boolean]("skipIntegrationTests", false).value
  lazy val printit = task {
    println("SKIP INTEGRATION TESTS: " + skipIntegrationTests)
    None
  }

}
