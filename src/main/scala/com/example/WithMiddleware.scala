package com.example

trait WithMiddleware[F[_]] {

  @scala.annotation.tailrec
  private def recursiveRun[A](
      ms: List[Middleware[F]],
      runner: Runner[F, A]
  ): F[A] =
    ms match {
      case Nil =>
        runner.run()

      case head :: tail =>
        recursiveRun(tail, head.runner(runner))
    }

  def middlewares: List[Middleware[F]]

  protected def withMiddleware[A](cb: () => F[A]): F[A] =
    recursiveRun(middlewares, Runner(cb))
}
