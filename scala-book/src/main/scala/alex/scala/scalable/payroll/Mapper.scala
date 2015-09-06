package alex.scala.scalable.payroll

import scala.language.higherKinds

trait Mapper[F[_]] {
  def fmap[A, B](xs: F[A], f: A => B): F[B]
}