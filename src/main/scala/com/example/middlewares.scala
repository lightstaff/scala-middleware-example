package com.example

import scala.concurrent.{ExecutionContext, Future}

trait Middleware {

  def run[A](cb: () => Future[A])()(implicit ec: ExecutionContext): Future[A]
}

object Middleware {

  @scala.annotation.tailrec
  private def withMiddleware[A](
      middlewares: List[Middleware],
      cb: () => Future[A]
  )(implicit ec: ExecutionContext): Future[A] =
    middlewares match {
      case Nil =>
        cb()

      case head :: tail =>
        withMiddleware(tail, head.run(cb))
    }

  def withMiddleware[A](
      cb: () => Future[A]
  )(implicit middlewares: List[Middleware], ec: ExecutionContext): Future[A] =
    withMiddleware(middlewares, cb)
}

object LoggerMiddleware1 extends Middleware {

  def run[A](
      cb: () => Future[A]
  )()(implicit ec: ExecutionContext): Future[A] = {
    println("Logger1 Start")

    cb().map { v =>
      println("Logger1 End")
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
      println("Logger2 End")

      v
    }
  }
}
