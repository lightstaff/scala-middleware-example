package com.example

import cats.effect.IO
import cats.implicits._

final case class Runner[F[_], A](run: () => F[A])

abstract class Middleware[F[_]] {

  def runner[A](runner: Runner[F, A]): Runner[F, A]
}

object LoggerMiddlewareIO_1 extends Middleware[IO] {

  override def runner[A](runner: Runner[IO, A]): Runner[IO, A] = Runner {
    println("Logger1 Start")

    runner.run.map { v =>
      println("Logger1 End")

      v
    }
  }
}

object LoggerMiddlewareIO_2 extends Middleware[IO] {

  override def runner[A](runner: Runner[IO, A]): Runner[IO, A] = Runner {
    println("Logger2 Start")

    runner.run.map { v =>
      println("Logger2 End")

      v
    }
  }
}
