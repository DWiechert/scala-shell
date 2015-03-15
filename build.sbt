name := "scala-shell"

version := "0.1"

scalaVersion := "2.11.2"

EclipseKeys.withSource := true

libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"

mainClass in assembly := Some("com.github.dwiechert.ScalaShell")

assemblyJarName in assembly := s"${name.value}-${version.value}.jar"