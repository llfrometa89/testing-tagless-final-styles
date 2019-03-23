package io.github.llfrometa89

import cats.effect.{ExitCode, IO, IOApp, Sync}
import cats.implicits._
import io.github.llfrometa89.domain.model.Savings
import io.github.llfrometa89.domain.services.AccountService
import io.github.llfrometa89.mtl_style.interpreter.AccountServiceInstances
import io.github.llfrometa89.implicits._

object Main extends IOApp {

  implicit val as: AccountService[IO] = AccountServiceInstances.instance[IO]

  def programMtl[F[_]: Sync: AccountService]: F[ExitCode] = {
    for {
      accountL <- AccountService[F].open("12345", "Livan Frometa", None, Savings)
      accountM <- AccountService[F].open("09876", "Martin Odersky", None, Savings)
      _        <- AccountService[F].transfer(accountL, accountM, 1000)
    } yield ExitCode.Success
  }

  def run(args: List[String]): IO[ExitCode] = programMtl[IO]

}
