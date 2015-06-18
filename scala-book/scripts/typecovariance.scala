import alex.scala.other.{Maybe, Just}

import scala.collection.GenTraversable

trait Func[-T, +M] {
  def apply(t: T): M
}

class A

class B extends A

class C extends B

val f1: Func[B, A] = new Func[A, C] {
  override def apply(t: A): C = new C
}

println("ok")


val a: Just[B] = Just(new B)
val o: Maybe[A] = Just(new C)

List(1, 2, 3).map({ (x: Int) => x + 1 })
List(1, 2).map(_ + 1)

def addOne(x: Int) = x + 1

lazy val l = List(1, 2).map(addOne)

def addOne2(num: Int) = {
  def ++ = (x: Int) => x + 1
  ++(num)
}

List(1, 3).map(addOne2)

val logEnabled = false

def message = "Get message"

def log(m: String) = if (logEnabled) println(m)

log("Hello, world" + message)

def log2(m: => String) = if (logEnabled) println(m)

log2("Hello, world" + message)
