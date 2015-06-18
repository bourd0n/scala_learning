import scala.collection.parallel.immutable.ParVector
import scala.io.Source
import scala.xml.{Elem, XML}

def map[A, B](xs: List[A], f: A => B): List[B] = {
  xs match {
    case List() => Nil
    case head :: tail => f(head) :: map(tail, f)
  }
}

def map1[A, B](xs: List[A], f: A => B): List[B] = for (x <- xs) yield f(x)

def flatMap[A, B](xs: List[A], f: A => List[B]): List[B] = {
  def flatten[T](xss: List[List[T]]): List[T] = {
    xss match {
      case List() => Nil
      case head :: tail => head ::: flatten(tail)
    }
  }
  flatten(map(xs, f))
}

def flatMap2[A, B](xs: List[A])(f: A => List[B]): List[B] = {
  def flatten[T](xss: List[List[T]]): List[T] = {
    xss match {
      case List() => Nil
      case head :: tail => head ::: flatten(tail)
    }
  }
  flatten(map(xs, f))
}

def flatten3[B](xss: List[List[B]]): List[B] = {
  def _flatten3(oldList: List[List[B]], newList: List[B]): List[B] = {
    oldList match {
      case List() => newList
      case head :: tail => _flatten3(tail, newList ::: head)
    }
  }
  _flatten3(xss, Nil)

}

List(1, 2, 3).foldLeft()

def map2[A, B](xs: List[A])(f: A => B): List[B] = {
  val startValue = List.empty[B]
  xs.foldRight(startValue) {
    f(_) :: _
  }
}

def map3[A, B](xs: List[A])(f: A => B): List[B] = {
  val startValue = List.empty[B]
  xs.foldLeft(startValue)((a, x) => f(x) :: a).reverse
}


def map4[A, B](xs: List[A])(f: A => B): List[B] = {
  val startValue = List.empty[B]
  xs.foldLeft(startValue)((a, x) => f(x) :: a)
}

List(1, 2, 3).apply(1)

val default: PartialFunction[Int, String] = {
  case _ => "Hello, world"
}

val languages = List(1, 2, 3)
val languagesWithDefault = languages.orElse(default)

List(1, 2).view.map(_ + 1).head

def tweets(handle: String): Elem = {
  println("processing tweets for " + handle)
  val source = Source.fromURL(new java.net.URL("http://search.twitter.com/search.atom?q=" + handle))
  val iterator = source.getLines()
  val builder = new StringBuilder
  for (line <- iterator) builder.append(line)
  XML.loadString(builder.toString())
}
def tweetsLazy = tweets _
val allTweets = Map("bourdon" -> tweetsLazy, "other" -> tweetsLazy)
allTweets.view.map { t => t._2(t._1) }.head

val fib: Stream[Int] = Stream.cons(0, Stream.cons(1, fib.zip(fib.tail).map(t => t._1 + t._2)))

ParVector(10, 20, 30, 40, 50, 60, 70, 80, 90).map{x => println(Thread.currentThread.getName); x / 2}


