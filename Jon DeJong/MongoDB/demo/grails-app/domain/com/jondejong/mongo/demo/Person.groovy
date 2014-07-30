package com.jondejong.mongo.demo
import com.mongodb.WriteConcern
import org.bson.types.ObjectId

class Person {

    ObjectId id
    String firstName
    String lastName
    Integer age

    String toString() {
        "${firstName} ${lastName}"
    }

    static mapping = {
//        writeConcern WriteConcern.ACKNOWLEDGED
    }

    static constraints = {
    }
}
