@Grab("org.mongodb:mongo-java-driver:2.12.2")

import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import groovyx.gpars.GParsPool

def mongoClient = new MongoClient("localhost", 27017)
def db = mongoClient.getDB("gr8-grails")
Random statusRandom = new Random()
Random ageRandom = new Random()

def collection = db.getCollection("person")

GParsPool.withPool {
    (0..20).eachParallel {
        def person = new BasicDBObject("name", "Jonny").
                append("status", statusRandom.nextBoolean() ? "Awesome" : "Almost Awesome").
                append("age", ageRandom.nextInt(60))
        collection.insert(person)
    }
}