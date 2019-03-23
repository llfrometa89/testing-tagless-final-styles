package io.github.llfrometa89.mtl_style.interpreter

import cats.effect.Sync
import cats.implicits._
import io.github.llfrometa89.domain.model.Account
import io.github.llfrometa89.domain.repositories.AccountRepository
import org.pure4s.logger4s.cats.Logger

trait AccountRepositoryInstances {

  implicit def instance[F[_]: Sync: Logger] = new AccountRepository[F] {

    def findByNo(no: String): F[Option[Account]] =
      Logger[F].info(s"AccountRepository::findByNo with no=$no") *> none[Account].pure[F]

    def save(account: Account): F[Account] =
      Logger[F].info(s"AccountRepository::save with account=$account") *> account.pure[F]
  }
}

object AccountRepositoryInstances extends AccountRepositoryInstances
