package io.github.llfrometa89.mtl_style.domain.services

import java.util.Date

import cats.Monad
import cats.effect.Sync
import io.github.llfrometa89.mtl_style.domain.model._
import io.github.llfrometa89.mtl_style.domain.repositories.AccountRepository
import cats.implicits._

trait AccountService[F[_], AC, AM] {

  implicit val monad: Monad[F]

  def open(no: String, name: String, rate: Option[BigDecimal], accountType: AccountType): F[AC]
  def close(no: String, closeDate: Date): F[AC]
  def debit(no: String, amount: AM): F[AC]
  def credit(no: String, amount: AM): F[AC]
  def transfer(from: String, to: String, amount: AM): F[(AC, AC)] =
    for {
      a <- debit(from, amount)
      b <- credit(to, amount)
    } yield (a, b)
}

object AccountService {

  def apply[F[_]](implicit F: AccountService[F, Account, Amount]): AccountService[F, Account, Amount] = F

  implicit def instance[F[_]: Sync: AccountRepository]() =
    new AccountService[F, Account, Amount] {

      def open(no: String, name: String, rate: Option[BigDecimal], accountType: AccountType): F[Account] = {

        //TODO validate no, name before create Account

        val account = (accountType, rate) match {
          case (Checking, _)      => CheckingAccount(no, name)
          case (Savings, Some(r)) => SavingsAccount(no, name, r)
          case (Savings, _)       => SavingsAccount(no, name)
        }
        AccountRepository[F].save(account)
      }

      def close(no: String, closeDate: Date): F[Account] = ???
      def debit(no: String, amount: Amount): F[Account]  = ???
      def credit(no: String, amount: Amount): F[Account] = ???

      implicit val monad: Monad[F] = Sync[F]
    }

}
