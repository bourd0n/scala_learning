package alex.scala.mongo

import com.mongodb.{MongoClient => MongoClientDelegate}

class MongoScalaClient(val host: String, val port: Int) {
  require(host != null, "Host can't be null")
  private val underlying = new MongoClientDelegate(host, port)

  def this() = this("127.0.0.1", 27017)

  def version = underlying.getVersion

  def dropDB(name: String) = underlying.dropDatabase(name)

  def createDB(name: String) = DB(underlying.getDB(name))

  def getDB(name: String) = DB(underlying.getDB(name))

  
}
