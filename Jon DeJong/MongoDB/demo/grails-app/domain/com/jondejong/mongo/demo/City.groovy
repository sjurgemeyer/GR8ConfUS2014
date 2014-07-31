package com.jondejong.mongo.demo

import grails.mongodb.geo.Point

class City {

    String country
//    String state
    String cityName
//    String postalCode
    Point point

    static mapping = {
        point geoIndex:'2dsphere' //, indexAttributes:[min:-90, max:90]
    }

    static constraints = {
        country nullable: true
//        state nullable: true
        cityName nullable: true
//        postalCode nullable: true
    }
}
