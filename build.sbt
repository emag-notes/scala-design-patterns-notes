name := "scala-design-patterns-notes"
scalaVersion := "2.13.1"
libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.1.1" % Test)
scalacOptions ++= Seq(
  "-deprecation",
  "-Yrangepos",
  "-Ywarn-unused",
  "-P:semanticdb:failures:warning"
)
addCompilerPlugin(scalafixSemanticdb)
