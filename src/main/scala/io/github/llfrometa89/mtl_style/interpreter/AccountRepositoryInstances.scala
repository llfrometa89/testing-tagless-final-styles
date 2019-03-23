package io.github.llfrometa89.mtl_style.interpreter

import cats.effect.Sync
import cats.implicits._
import io.github.llfrometa89.domain.model.Account
import io.github.llfrometa89.domain.repositories.AccountRepository

trait AccountRepositoryInstances {

  implicit def instance[F[_]: Sync] = new AccountRepository[F] {

    def findByNo(no: String): F[Option[Account]] = none[Account].pure[F]

    def save(account: Account): F[Account] = account.pure[F]
  }
}

object AccountRepositoryInstances extends AccountRepositoryInstances
