name := "scala-design-patterns-notes"
scalaVersion := "2.13.2"
scalacOptions ++= Seq(
  "-deprecation",
  "-Yrangepos",
  "-Ywarn-unused",
  "-P:semanticdb:failures:warning"
)
addCompilerPlugin(scalafixSemanticdb)
libraryDependencies ++= Seq(
  "org.json4s"                 %% "json4s-jackson" % "3.6.9",
  "org.slf4j"                  % "slf4j-log4j12"   % "1.7.30",
  "com.typesafe.scala-logging" %% "scala-logging"  % "3.9.2",
  "commons-codec"              % "commons-codec"   % "1.14",
  "org.scalatest"              %% "scalatest"      % "3.2.0" % Test
)
