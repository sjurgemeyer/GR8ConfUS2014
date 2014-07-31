//package com.jondejong.gparsdemo.collections

def sleepers = [
        new Sleeper(10),
        new Sleeper(2),
        new Sleeper(4),
        new Sleeper(6),
        new Sleeper(20),
        new Sleeper(14),
        new Sleeper(18)
]

def start = new Date()

println "Running sequentially"
def messages  = sleepers.collect {
    println "Starting to sleep for ${it.seconds} seconds."
    it.sleep()
}
def end = new Date()

println "Finished running in ${(end.time - start.time)/1000} seconds. With message:"
messages.each {
    println it
}
