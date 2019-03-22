package io.github.llfrometa89.mtl_style.domain

import java.util.{Calendar, Date}

package object model {

  type Amount = BigDecimal

  def today: Date = Calendar.getInstance.getTime
}
