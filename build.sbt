import java.util.Locale

name := "j2v8-scala"

organization := "org.intracer"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

val sprayV = "1.3.4"
val akkaV = "2.4.18"
val j2v8V = "4.6.0"
val rhinoV = "1.7.7.2"
val specsV = "3.8.4"

val isWindows = System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("windows")
val j2v8Lib = if (isWindows) "j2v8_win32_x86_64" else "j2v8_linux_x86_64"

libraryDependencies ++= Seq(
  "com.eclipsesource.j2v8" % j2v8Lib % j2v8V,
  "org.mozilla" % "rhino" % rhinoV,
  "io.spray" %% "spray-client" % sprayV,
  "io.spray" %% "spray-routing" % sprayV,
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "org.specs2" %% "specs2-core" % specsV % Test
)