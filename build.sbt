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
  "org.json4s"    % "json4s-jackson_2.13" % "3.6.7",
  "org.scalatest" %% "scalatest"          % "3.1.1" % Test
)
