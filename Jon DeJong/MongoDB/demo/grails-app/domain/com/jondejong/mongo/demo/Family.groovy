package com.jondejong.mongo.demo

import org.bson.types.ObjectId

class Family {

    ObjectId id
    String familyName
    Address address;

    static hasMany = [people: Person]
    static embedded = ['address']

    static constraints = {
        address nullable: true
    }
}
