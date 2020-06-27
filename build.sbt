name := "akka-remoting"

version := "0.1"

scalaVersion := "2.13.3"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.6"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.6.6"
libraryDependencies += "io.netty" % "netty" % "3.10.6.Final"
libraryDependencies += "com.typesafe.akka" %% "akka-serialization-jackson" % "2.6.6"