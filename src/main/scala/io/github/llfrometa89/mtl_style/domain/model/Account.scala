package io.github.llfrometa89.mtl_style.domain.model

import java.util.Date

sealed trait AccountType
case object Checking extends AccountType
case object Savings  extends AccountType

sealed trait Account {
  def no: String
  def name: String
  def dateOfOpen: Date          = today
  def dateOfClose: Option[Date] = None
  def balance: Balance
}

final case class CheckingAccount(no: String, name: String, balance: Balance = Balance()) extends Account

final case class SavingsAccount(no: String, name: String, rateOfInterest: Amount = 0, balance: Balance = Balance())
    extends Account
