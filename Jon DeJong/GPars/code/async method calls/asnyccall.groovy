import groovyx.gpars.GParsPool

import java.util.concurrent.Future

GParsPool.withPool {
    println "Calling async function"
    Future longVal = {
        (1..25).collect {
            sleep 500
            it * 2
        }
    }.callAsync()

    print '\nAsync function called. Blocking on get call'
//    while(!longVal.isDone()) {
//        print "."
//        sleep 500
//    }
    println "val: ${longVal.get()}"
}
