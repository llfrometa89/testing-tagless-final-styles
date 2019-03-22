name := "tagless-final-style"

version := "0.1"

scalaVersion := "2.12.8"

lazy val V = new {
  val cats                      = "1.5.0"
  val catsEffect                = "1.0.0"
  val logger4s                  = "0.3.0"
  val scalatest                 = "3.0.0"
}


libraryDependencies ++= Seq(
  "org.typelevel"                  %% "cats-core"        % V.cats,
  "org.typelevel"                  %% "cats-effect"      % V.catsEffect,
//  "org.pure4s"                     %% "logger4s-cats"    % V.logger4s,
)