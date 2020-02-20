package com.example

import scala.concurrent.{ExecutionContext, Future}

class UserApplication {

  implicit val executionContext: ExecutionContext = ExecutionContext.global

  implicit val middlewares: List[Middleware] =
    List(LoggerMiddleware1, LoggerMiddleware2)

  def create(name: String): Future[Int] = Middleware.withMiddleware { () =>
    println("Inside Application")
    Future(1)
  }
}
