name := "scala-design-patterns-notes"
scalaVersion := "2.13.3"
scalacOptions ++= Seq(
  "-deprecation",
  "-Yrangepos",
  "-Ywarn-unused",
  "-P:semanticdb:failures:warning"
)
addCompilerPlugin(scalafixSemanticdb)

libraryDependencies ++= {
  val scalazVersion = "7.3.2"
  Seq(
    "org.json4s"                 %% "json4s-jackson"             % "3.6.9",
    "org.slf4j"                  % "slf4j-log4j12"               % "1.7.30",
    "com.typesafe.scala-logging" %% "scala-logging"              % "3.9.2",
    "commons-codec"              % "commons-codec"               % "1.14",
    "com.github.tototoshi"       %% "scala-csv"                  % "1.3.6",
    "org.scala-lang.modules"     %% "scala-parallel-collections" % "0.2.0",
    "org.scalaz"                 %% "scalaz-core"                % scalazVersion,
    "org.scalaz"                 %% "scalaz-effect"              % scalazVersion,
    "com.typesafe.akka"          %% "akka-actor"                 % "2.6.8",
    "com.h2database"             % "h2"                          % "1.4.200",
    "com.typesafe"               % "config"                      % "1.4.0",
    "org.scalatest"              %% "scalatest"                  % "3.2.0" % Test,
    "org.scalatestplus"          %% "mockito-3-3"                % "3.2.0.0" % Test,
    "org.scalatestplus"          %% "scalacheck-1-14"            % "3.2.0.0" % Test,
    "org.scalacheck"             %% "scalacheck"                 % "1.14.3" % Test,
    "org.scalaz"                 %% "scalaz-scalacheck-binding"  % scalazVersion % Test
  )
}
