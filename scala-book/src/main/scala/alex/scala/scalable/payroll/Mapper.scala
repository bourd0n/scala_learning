package alex.scala.scalable.payroll

trait Mapper[F[_]] {
  def fmap[A, B](xs: F[A], f: A => B): F[B]
}