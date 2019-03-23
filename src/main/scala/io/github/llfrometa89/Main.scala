package io.github.llfrometa89

import cats.Monad
import cats.implicits._
import cats.effect.{ExitCode, IO, IOApp, Sync}
import io.github.llfrometa89.domain.model.Savings
import io.github.llfrometa89.domain.services.AccountService
import io.github.llfrometa89.mtl_style.interpreter.{AccountRepositoryInstances, AccountServiceInstances}
import io.github.llfrometa89.mtl_style.interpreter.AccountRepositoryInstances._
import org.pure4s.logger4s.cats.Logger

class TestL
object Main extends IOApp {

  def programMtl[F[_]: Monad: Sync: AccountService]: F[ExitCode] = {
    for {
      _ <- AccountService[F].open("12345", "Livan Frometa", None, Savings)
    } yield ExitCode.Success
  }

  def run(args: List[String]): IO[ExitCode] = {
    implicit val l  = Logger.instance[IO](classOf[TestL])
    implicit val ev = AccountRepositoryInstances.instance[IO]
    implicit val s  = AccountServiceInstances.instance[IO]
    programMtl[IO]
  }
}
