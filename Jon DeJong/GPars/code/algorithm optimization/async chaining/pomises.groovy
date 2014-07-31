import groovyx.gpars.GParsPool
import groovyx.gpars.dataflow.Promise

def done = false

def longProcess(values) {
    println "Executing longProcess"
    values.collect {
        sleep 500
        it * 2
    }
}

def longProcessDoneHandler = {vals->
    done = true
    print '\n\nProcess is done with: '
    vals.each{print " ${it}"}
}

GParsPool.withPool() {
    def asyncLongProcess = {longProcess(1..25)}.asyncFun()
    Promise promise = asyncLongProcess()
    promise.whenBound longProcessDoneHandler
    print "\nWhile this runs, let's move on and do something else"
    while(!done) {
        print "."
        sleep 500
    }
}
