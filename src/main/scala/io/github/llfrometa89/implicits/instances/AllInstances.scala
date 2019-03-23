package io.github.llfrometa89.implicits.instances

import io.github.llfrometa89.mtl_style.instances.{
  AccountRepositoryInstances,
  AccountServiceInstances,
  GeneratorInstances
}

trait AllInstances extends AccountServiceInstances with AccountRepositoryInstances with GeneratorInstances
