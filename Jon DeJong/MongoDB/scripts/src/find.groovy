@Grab("org.mongodb:mongo-java-driver:2.12.2")

import com.mongodb.BasicDBObject
import com.mongodb.MongoClient

def mongoClient = new MongoClient("localhost", 27017)
def db = mongoClient.getDB("gr8-grails")

def collection = db.getCollection("person")

def query = new BasicDBObject("age", new BasicDBObject("\$lt", 20))

def cursor = collection.find(query)

// Individual document update
println("People under 20 before update:  ${cursor.count()}")
while(cursor.hasNext()) {
    def person = cursor.next()
    person.put("age", 20)
    collection.save(person)
}

cursor.close()

cursor = collection.find(query)
println("After update count: ${cursor.count()}")
cursor.close()