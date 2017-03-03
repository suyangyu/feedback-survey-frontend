import sbt._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning

object FrontendBuild extends Build with MicroService {
  val appName = "feedback-survey-frontend"
  override lazy val appDependencies: Seq[ModuleID] = AppDependencies()
}

private object AppDependencies {
    import play.sbt.PlayImport._
    import play.core.PlayVersion

    private val playHealthVersion = "2.0.0"
    private val logbackJsonLoggerVersion = "3.1.0"
    private val frontendBootstrapVersion = "7.10.0"
    private val govukTemplateVersion = "5.0.0"
    private val playUiVersion = "5.3.0"
    private val playPartialsVersion = "5.2.0"
    private val playAuthorisedFrontendVersion = "6.2.0"
    private val playConfigVersion = "3.0.0"
    private val hmrcTestVersion = "2.3.0"
    private val scalaTestVersion = "2.2.6"
    private val scalaTestPlusPlayVersion = "1.5.1"
    private val pegdownVersion = "1.6.0"
    private val mockitoVersion = "1.10.19"


    val compile = Seq(
      ws,
      "uk.gov.hmrc" %% "frontend-bootstrap" % "7.12.0",
      "uk.gov.hmrc" %% "play-partials" % "5.3.0",
      "uk.gov.hmrc" %% "play-authorised-frontend" % "6.3.0",
      "uk.gov.hmrc" %% "play-config" % "3.0.0",
      "uk.gov.hmrc" %% "logback-json-logger" % "3.1.0",
      "uk.gov.hmrc" %% "govuk-template" % "5.1.0",
      "uk.gov.hmrc" %% "play-health" % "2.1.0",
      "uk.gov.hmrc" %% "play-ui" % "7.0.0"
    )

    trait TestDependencies {
      lazy val scope: String = "test"
      lazy val test: Seq[ModuleID] = ???
    }

    object Test {
      def apply() = new TestDependencies {
        override lazy val test = Seq(
          "uk.gov.hmrc" %% "hmrctest" % hmrcTestVersion % scope,
          "org.scalatest" %% "scalatest" % scalaTestVersion % scope,
          "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlusPlayVersion % scope,
          "org.pegdown" % "pegdown" % pegdownVersion % scope,
          "org.jsoup" % "jsoup" % "1.8.1" % scope,
          "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
          "org.mockito" % "mockito-all" % mockitoVersion % scope
        )
      }.test
    }

  def apply() = compile ++ Test()
}