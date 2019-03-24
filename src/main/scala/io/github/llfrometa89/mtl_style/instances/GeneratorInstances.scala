package io.github.llfrometa89.mtl_style.instances
import io.github.llfrometa89.domain.utils.Generator

import scala.util.Random

trait GeneratorInstances {

  implicit val accountNoGenerator: Generator[String] = new Generator[String] {
    override def generate: String = new Random().nextInt(10000).toString
  }

}

object GeneratorInstances extends GeneratorInstances
