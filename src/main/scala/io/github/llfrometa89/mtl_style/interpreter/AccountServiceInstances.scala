package io.github.llfrometa89.mtl_style.interpreter

import java.util.Date
import cats.Monad
import cats.effect.Sync
import cats.implicits._
import io.github.llfrometa89.domain.model._
import io.github.llfrometa89.domain.repositories.AccountRepository
import io.github.llfrometa89.domain.services.AccountMessage.{AlreadyExistAccount, NotFoundAccount}
import io.github.llfrometa89.domain.services.AccountService
import io.github.llfrometa89.implicits._
import monocle.std.option._

trait AccountServiceInstances {

  implicit def instance[F[_]: Sync: AccountRepository] =
    new AccountService[F] {

      def open(no: String, name: String, rate: Option[BigDecimal], accountType: AccountType): F[Account] = {

        def validate: Either[Throwable, Account] = Account.validate(no, name, rate, accountType)

        def save: F[Account] =
          for {
            accountValidated <- Sync[F].pure(validate).rethrow
            account          <- AccountRepository[F].save(accountValidated)
          } yield account

        for {
          maybeAccount <- AccountRepository[F].findByNo(no)
          account      <- maybeAccount.mapOrElse(_ => Sync[F].raiseError(AlreadyExistAccount(no)), save)
        } yield account

      }

      def close(no: String, closeDate: Date): F[Account] = {

        def save(account: Account): F[Account] = {
          val accountUpdated = account match {
            case ca: CheckingAccount => (CheckingAccount.dateOfClose composePrism some).set(closeDate)(ca)
            case sa: SavingsAccount  => (SavingsAccount.dateOfClose composePrism some).set(closeDate)(sa)
          }
          AccountRepository[F].save(accountUpdated)
        }

        for {
          maybeAccount <- AccountRepository[F].findByNo(no)
          account      <- maybeAccount.mapOrElse(save, Sync[F].raiseError(NotFoundAccount(no)))
        } yield account
      }

      def debit(no: String, amount: Amount): F[Account]  = ???
      def credit(no: String, amount: Amount): F[Account] = ???

      implicit val monad: Monad[F] = Sync[F]
    }
}

object AccountServiceInstances extends AccountServiceInstances
