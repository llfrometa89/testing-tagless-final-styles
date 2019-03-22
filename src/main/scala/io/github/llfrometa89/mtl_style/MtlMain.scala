package io.github.llfrometa89.mtl_style

import cats.Monad
import cats.effect.{ExitCode, IO, IOApp, Sync}
import cats.implicits._

object Main extends IOApp {

  def program[F[_]: Monad: Sync]: F[ExitCode] = {
    for {
      _ <- Sync[F].pure(Unit)
    } yield ExitCode.Success
  }
  override def run(args: List[String]): IO[ExitCode] = program[IO]
}
