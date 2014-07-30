package com.jondejong.mongo.demo

import grails.converters.JSON
import grails.mongodb.geo.Circle
import grails.mongodb.geo.Point

class CityController {

    def index() {
        def cities = City.findAll()
        def resp = [count: cities.size(), cities: cities]
        render resp as JSON
    }

    def state() {
        def cities = City.findAllByState(params.state)
        def resp = [count: cities.size(), cities: cities]
        render resp as JSON
    }

    def name() {
        def all = City.findAllByCityName(params.name)
        render all as JSON
    }

    def near() {
        def center = Point.valueOf(Double.parseDouble(params.long), Double.parseDouble(params.lat))
        def city = City.findByPointNear(center)
        def resp = [city: city]
        render resp as JSON
    }

    def around() {
        def longitude = Double.parseDouble(params.long)
        def latitude = Double.parseDouble(params.lat)

        def radius = (params.radius ? Double.parseDouble(params.radius) : 50)

        def cities = City.findAllByPointGeoWithin(Circle.valueOf([[longitude, latitude], radius]))
        def resp = [count: cities.size(), cities: cities]
        render resp as JSON
    }

    def empty() {
        def count = 0
        City.findAll().each {
            it.delete()
            count++
        }

        def resp = [status: "success", count: count]
        render resp as JSON
    }

    def load() {
        def point1 = Point.valueOf(50,50)
        def point2 = Point.valueOf(60,40)
        def point3 = Point.valueOf(55,45)
        def point4 = Point.valueOf(4,4)

        def city1 = new City(country: "Westeros", cityName: "Capital", point: point1).save(failOnError: true)
        def city2 = new City(country: "Westeros", cityName: "Winterfell", point: point2).save(failOnError: true)
        def city3 = new City(country: "Westeros", cityName: "Other Place", point: point3).save(failOnError: true)
        def city4 = new City(country: "Easteros", cityName: "Capital", point: point4).save(failOnError: true)

        def resp = [status: "success", count: 4]
        render resp as JSON
    }

}