package alex.scala.mongo

import com.mongodb.{DB => MongoDB}

import scala.collection.convert.Wrappers.JSetWrapper

class DB private(val underlying: MongoDB) {
  def collectionNames = for (name <- new JSetWrapper(underlying.getCollectionNames)) yield name

  private def collection(name: String) = underlying.getCollection(name)

  def readOnlyCollection(name: String) = new DBCollection(collection(name)) with Memorizer with LocalAware

  def administrableCollection(name: String) = new DBCollection(collection(name)) with Administrable with Memorizer

  def updatebleCollection(name: String) = new DBCollection(collection(name)) with Updatable with Memorizer
}

object DB {
  def apply(underlying: MongoDB) = new DB(underlying)
}
