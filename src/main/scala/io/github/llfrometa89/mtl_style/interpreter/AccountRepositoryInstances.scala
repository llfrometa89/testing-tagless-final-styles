package io.github.llfrometa89.mtl_style.interpreter

import cats.effect.Sync
import cats.implicits._
import io.github.llfrometa89.domain.model.{Account, CheckingAccount}
import io.github.llfrometa89.domain.repositories.AccountRepository

trait AccountRepositoryInstances {

  implicit def instance[F[_]: Sync]: AccountRepository[F] = new AccountRepository[F] {

    def findByNo(no: String): F[Option[Account]] = Option[Account](CheckingAccount(no, "John Goes")).pure[F]

    def save(account: Account): F[Account] = account.pure[F]
  }
}

object AccountRepositoryInstances extends AccountRepositoryInstances
