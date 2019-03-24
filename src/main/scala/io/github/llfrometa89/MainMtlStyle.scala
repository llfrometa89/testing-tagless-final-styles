package io.github.llfrometa89

import cats.effect.{ExitCode, IO, IOApp, Sync}
import cats.implicits._
import io.github.llfrometa89.domain.model.Savings
import io.github.llfrometa89.domain.repositories.AccountRepository
import io.github.llfrometa89.domain.services.AccountService
import io.github.llfrometa89.domain.utils.Generator
import io.github.llfrometa89.implicits._

object MainMtlStyle extends IOApp {

  def program[F[_]: Sync](implicit G: Generator[String]): F[ExitCode] =
    for {
      accountNoL <- G.generate.pure[F]
      accountNoM <- G.generate.pure[F]
      _          <- AccountService[F].open(accountNoL, "Livan Frometa", None, Savings)
      _          <- AccountService[F].credit(accountNoL, 200)
      _          <- AccountService[F].open(accountNoM, "Martin Odersky", None, Savings)
      _          <- AccountService[F].credit(accountNoM, 300)
      _          <- AccountService[F].transfer(accountNoL, accountNoM, 50)
      _          <- Sync[F].delay(println(s"accounts = ${AccountRepository[F].findAll}"))
    } yield ExitCode.Success

  def run(args: List[String]): IO[ExitCode] = program[IO]

}
