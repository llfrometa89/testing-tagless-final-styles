name := "tagless-final-style"

version := "0.1"

scalaVersion := "2.12.8"

lazy val V = new {
  val cats                      = "1.5.0"
  val catsEffect                = "1.0.0"
  val circe                     = "0.10.0"
  val hammock                   = "0.8.7"
  val scalatest                 = "3.0.0"
}


libraryDependencies ++= Seq(
  "io.circe"                       %% "circe-generic"               % V.circe,
  "io.circe"                       %% "circe-jackson28"             % V.circe,
  "io.circe"                       %% "circe-java8"                 % V.circe,
  "org.typelevel"                  %% "cats-core"                   % V.cats,
  "org.typelevel"                  %% "cats-effect"                 % V.catsEffect,
  "com.pepegar"                    %% "hammock-asynchttpclient"     % V.hammock,
  "com.pepegar"                    %% "hammock-circe"               % V.hammock,
)