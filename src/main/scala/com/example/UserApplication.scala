package com.example

import scala.concurrent.{ExecutionContext, Future}

class UserApplication extends WithMiddleware {

  implicit val executionContext: ExecutionContext = ExecutionContext.global

  override val middlewares: List[Middleware] =
    List(LoggerMiddleware1, LoggerMiddleware2)

  def create(name: String): Future[Int] = withMiddleware { () =>
    println(s"Inside Application: $name")
    Future(1)
  }
}
