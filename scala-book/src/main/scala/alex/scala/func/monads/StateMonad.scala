package alex.scala.func.monads

object StateMonad {

  trait State[S, +A] {
    def apply(s: S): (S, A)

    def map[B](f: A => B): State[S, B] = {
      val func: S => (S, B) = apply(_) match {
        case (s, a) => (s, f(a))
      }
      State.state(func)
    }

    def flatMap[B](f: A => State[S, B]): State[S, B] = {
      val func: S => (S, B) = apply(_) match {
        case (s, a) => f(a)(s)
      }
      State.state(func)
    }
  }

  object State {
    def state[S, A](f: S => (S, A)) = new State[S, A] {
      override def apply(s: S): (S, A) = f(s)
    }

    def init[S]: State[S, S] = state[S, S](s => (s, s))

    def modify[S](f: S => S) = init[S] flatMap (s => state(_ => (f(s), ())))
  }

}