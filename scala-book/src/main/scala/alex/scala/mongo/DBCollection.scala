package alex.scala.mongo

import java.util.Locale

import com.mongodb.{DBCollection => MongoDBCollection, DBCursor, DBObject}

class DBCollection(override val underlying: MongoDBCollection) extends ReadOnly

trait Administrable extends ReadOnly {
  def drop(): Unit = underlying.drop()

  def dropIndexes(): Unit = underlying.dropIndexes()
}

trait ReadOnly {
  val underlying: MongoDBCollection

  def name = underlying.getName

  def fullName = underlying.getFullName

  def find(doc: DBObject) = underlying.find(doc)

  def findOne(doc: DBObject) = underlying.findOne(doc)

  def findOne = underlying.findOne()

  def getCount(doc: DBObject) = underlying.getCount(doc)

  def find(query: Query) = {
    def applyOptions(cursor: DBCursor, option: QueryOption): DBCursor = {
      option match {
        case Sort(sorting, anotherOption) => applyOptions(cursor.sort(sorting), anotherOption)
        case Limit(limit, anotherOption) => applyOptions(cursor.limit(limit), anotherOption)
        case Skip(skip, anotherOption) => applyOptions(cursor.skip(skip), anotherOption)
        case NoOption => cursor
      }
    }

    applyOptions(underlying.find(query.q), query.option)
  }
}

trait Updatable extends ReadOnly {

  def +=(doc: DBObject) = underlying.save(doc)

  def -=(doc: DBObject) = underlying.remove(doc)
}

trait Memorizer extends ReadOnly {
  val history = scala.collection.mutable.Map[Int, DBObject]()

  override def findOne = history.getOrElseUpdate(-1, super.findOne)

  override def findOne(doc: DBObject) = {
    history.getOrElseUpdate(doc.hashCode, {
      super.findOne(doc)
    })
  }
}

trait LocalAware extends ReadOnly {

  private def withLocale(doc: DBObject) = {
    doc.put("locale", Locale.getDefault.getLanguage)
    doc
  }

  override def findOne(doc: DBObject) = super.findOne(withLocale(doc))

  override def find(doc: DBObject) = super.find(withLocale(doc))
}




