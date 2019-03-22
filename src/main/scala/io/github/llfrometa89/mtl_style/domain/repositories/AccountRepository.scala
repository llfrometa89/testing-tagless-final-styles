package io.github.llfrometa89.mtl_style.domain.repositories

import io.github.llfrometa89.mtl_style.domain.model.Account

trait AccountRepository[F[_]] {

  def findByNo(no: String): F[Account]
  def save(account: Account): F[Account]
}

object AccountRepository {
  def apply[F](implicit F: AccountRepository[F]): AccountRepository[F] = F
}
