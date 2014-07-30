package com.jondejong.mongo.demo

class Address {

    String line1
    String line2
    String city
    String state
    String postalCode

    static constraints = {
        line2 nullable: true
    }
}
