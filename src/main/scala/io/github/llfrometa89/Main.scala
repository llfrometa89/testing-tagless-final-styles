package io.github.llfrometa89

import cats.effect.{ExitCode, IO, IOApp, Sync}
import cats.implicits._
import io.github.llfrometa89.domain.model.Savings
import io.github.llfrometa89.domain.services.AccountService
import io.github.llfrometa89.mtl_style.interpreter.AccountRepositoryInstances._
import io.github.llfrometa89.mtl_style.interpreter.AccountServiceInstances
import io.github.llfrometa89.mtl_style.interpreter.AccountServiceInstances._
//import io.github.llfrometa89.implicits._

object Main extends IOApp {

  def programMtl[F[_]: Sync: AccountService]: F[ExitCode] = {
    for {
      _ <- AccountService[F].open("12345", "Livan Frometa", None, Savings)
    } yield ExitCode.Success
  }

  def run(args: List[String]): IO[ExitCode] =
//    implicit val s: AccountService[IO] = AccountServiceInstances.instance[IO]
    programMtl[IO]

}
