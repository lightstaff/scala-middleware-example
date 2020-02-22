name := "scala-middleware-example"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq("org.typelevel" %% "cats-core"   % "2.1.0",
                            "org.typelevel" %% "cats-effect" % "2.1.1",
                            "org.scalatest" %% "scalatest"   % "3.1.0" % "test")

addCompilerPlugin(
  "org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full
)
