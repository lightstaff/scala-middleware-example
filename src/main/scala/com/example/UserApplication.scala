package com.example

import scala.concurrent.ExecutionContext

import cats.effect.{ContextShift, IO}

class UserApplication extends WithMiddleware[IO] {

  implicit val executionContext: ExecutionContext = ExecutionContext.global

  implicit val contextShift: ContextShift[IO] =
    IO.contextShift(executionContext)

  override val middlewares: List[Middleware[IO]] =
    List(LoggerMiddlewareIO_1, LoggerMiddlewareIO_2)

  def create(name: String): IO[Int] = withMiddleware { () =>
    println(s"Inside Application: $name")
    IO.pure(1)
  }
}
