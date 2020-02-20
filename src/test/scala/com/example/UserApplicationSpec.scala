package com.example

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

class UserApplicationSpec extends AsyncWordSpec with Matchers {

  private val userApplication = new UserApplication()

  "user application" should {
    "create user" in {
      userApplication.create("Same").map { v =>
        v shouldBe 1
      }
    }
  }
}
