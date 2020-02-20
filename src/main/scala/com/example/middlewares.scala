package com.example

import scala.concurrent.{ExecutionContext, Future}

trait Middleware {

  def run[A](cb: () => Future[A])()(implicit ec: ExecutionContext): Future[A]
}

object LoggerMiddleware1 extends Middleware {

  def run[A](
      cb: () => Future[A]
  )()(implicit ec: ExecutionContext): Future[A] = {
    println("Logger1 Start")

    cb().map { v =>
      println(s"Logger1 End: $v")
      v
    }
  }
}

object LoggerMiddleware2 extends Middleware {

  def run[A](
      cb: () => Future[A]
  )()(implicit ec: ExecutionContext): Future[A] = {
    println("Logger2 Start")

    cb().map { v =>
      println(s"Logger2 End: $v")
      v
    }
  }
}
