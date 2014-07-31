import groovyx.gpars.GParsPool

def reallyLongProcessLoop(values) {
    def longVals = values.collect {
        sleep 500
        it * 2
    }

    print '\nDone getting values in long process loop:'
    longVals.each {print " ${it}"}
}

def quickProcessLoop(values) {
    def quickVals = values.collect {
        sleep 50
        it * 2
    }
    print '\nDone getting values in quick process loop:'
    quickVals.each {print " ${it}"}
}

GParsPool.withPool() {
    println "Setting up closures"
    def longClosure = { reallyLongProcessLoop(1..25) }
    def quickClosure = { quickProcessLoop(26..50) }
    println "Done setting up closures"
    println "Creating async closures"
    def asyncLongClosure = longClosure.async()
    def asyncQuickClosure = quickClosure.async()
    println "Done creating async closures"

    println "Starting async processes"
    asyncLongClosure()
    asyncQuickClosure()
    println "\n\n#################\nDone starting async processes. So we can do other stuff here\n################\n\n"
}
