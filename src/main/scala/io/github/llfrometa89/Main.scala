package io.github.llfrometa89

import cats.Monad
import cats.implicits._
import cats.effect.{ExitCode, IO, IOApp, Sync}

object Main extends IOApp {

  def program[F[_]: Monad: Sync]: F[ExitCode] = {
    for {
      _ <- Sync[F].pure(Unit)
    } yield ExitCode.Success
  }
  override def run(args: List[String]): IO[ExitCode] = program[IO]
}
