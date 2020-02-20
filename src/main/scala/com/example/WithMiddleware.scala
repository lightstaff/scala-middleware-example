package com.example

import scala.concurrent.{ExecutionContext, Future}

trait WithMiddleware {

  @scala.annotation.tailrec
  private def recursiveRun[A](
      runners: List[Middleware],
      cb: () => Future[A]
  )(implicit ec: ExecutionContext): Future[A] =
    runners match {
      case Nil =>
        cb()

      case head :: tail =>
        recursiveRun(tail, head.run(cb))
    }

  def middlewares: List[Middleware]

  protected def withMiddleware[A](cb: () => Future[A])(
      implicit ec: ExecutionContext
  ): Future[A] = recursiveRun(middlewares, cb)
}
