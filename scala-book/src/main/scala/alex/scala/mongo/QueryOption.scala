package alex.scala.mongo

import com.mongodb.DBObject

sealed trait QueryOption

case object NoOption extends QueryOption

case class Sort(sorting: DBObject, anotherOption: QueryOption) extends QueryOption

case class Skip(skip: Int, anotherOption: QueryOption) extends QueryOption

case class Limit(limit: Int, anotherOption: QueryOption) extends QueryOption
