name := "j2v8-scala"

organization := "org.intracer"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.11"

val sprayV = "1.3.4"
val akkaV = "2.4.18"

libraryDependencies ++= Seq(
  "com.eclipsesource.j2v8" % "j2v8_linux_x86_64" % "4.8.0",
  "io.spray" %% "spray-client" % sprayV,
  "io.spray" %% "spray-routing" % sprayV,
  "com.typesafe.akka" %% "akka-actor" % akkaV
)
