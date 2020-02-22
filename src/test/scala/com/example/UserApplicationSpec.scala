package com.example

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class UserApplicationSpec extends AnyWordSpec with Matchers {

  private val userApplication = new UserApplication()

  "user application" should {
    "create user" in {
      userApplication.create("Same").unsafeRunAsync {
        case Right(v) =>
          v shouldBe 1

        case Left(ex) =>
          throw ex
      }
    }
  }
}
