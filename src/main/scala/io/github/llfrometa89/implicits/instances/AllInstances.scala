package io.github.llfrometa89.implicits.instances

import io.github.llfrometa89.mtl_style.interpreter.{AccountRepositoryInstances, AccountServiceInstances}

trait AllInstances extends AccountServiceInstances with AccountRepositoryInstances
