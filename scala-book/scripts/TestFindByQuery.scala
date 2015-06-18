import alex.scala.mongo.{Query, MongoScalaClient}
import com.mongodb.BasicDBObject

def client = new MongoScalaClient
def db = client.getDB("test")

val readOnlyCol = db.readOnlyCollection("test")
val updatableCol = db.updatebleCollection("test")

for (i <- 1 to 100) updatableCol += new BasicDBObject("i", i)

var rangeQuery = new BasicDBObject("i", new BasicDBObject("$gt", 20))
var richQuery = Query(rangeQuery).skip(20).limit(10)
val cursor = readOnlyCol.find(richQuery)
while (cursor.hasNext){
  println(cursor.next)
}

