name := "AkkaHttp"

version := "1.0"

scalaVersion := "2.11.8"

//libraryDependencies += "com.typesafe.akka" % "akka-http_2.11" % "10.0.1"
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http-spray-json-experimental_2.11
//libraryDependencies += "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "2.4.11"


resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.bintrayRepo("hseeberger", "maven"))

libraryDependencies ++= {
  val AkkaVersion       = "2.3.9"
  val AkkaHttpVersion   = "2.4.11"
  val Json4sVersion     = "3.2.11"
  Seq(
    "com.typesafe.akka" %% "akka-slf4j"      % AkkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % AkkaHttpVersion,
    "ch.qos.logback"    %  "logback-classic" % "1.1.2",
    "org.json4s"        %% "json4s-native"   % Json4sVersion,
    "org.json4s"        %% "json4s-ext"      % Json4sVersion,
    "de.heikoseeberger" %% "akka-http-json4s" % "1.4.2"
  )
}