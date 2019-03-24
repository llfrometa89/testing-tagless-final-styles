package io.github.llfrometa89.mtl_style.instances

import cats.effect.Sync
import cats.implicits._
import io.github.llfrometa89.domain.model.Account
import io.github.llfrometa89.domain.repositories.AccountRepository

trait AccountRepositoryInstances {

  implicit def instanceInMemory[F[_]: Sync]: AccountRepository[F] = new AccountRepository[F] {

    var db = Map.empty[String, Account]

    def findByNo(no: String): F[Option[Account]] = db.get(no).pure[F]

    def save(account: Account): F[Account] = {
      db += (account.no -> account)
      account.pure[F]
    }
  }
}

object AccountRepositoryInstances extends AccountRepositoryInstances
