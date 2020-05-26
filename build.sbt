name := "scala-design-patterns-notes"
scalaVersion := "2.13.1"
scalacOptions ++= Seq(
  "-deprecation",
  "-Yrangepos",
  "-Ywarn-unused",
  "-P:semanticdb:failures:warning"
)
addCompilerPlugin(scalafixSemanticdb)
libraryDependencies ++= Seq(
  "org.json4s"                 % "json4s-jackson_2.13" % "3.6.7",
  "org.slf4j"                  % "slf4j-log4j12"       % "1.7.30",
  "com.typesafe.scala-logging" %% "scala-logging"      % "3.9.2",
  "org.scalatest"              %% "scalatest"          % "3.1.1" % Test
)
