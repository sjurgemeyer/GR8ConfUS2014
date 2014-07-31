import groovyx.gpars.GParsPool

def reallyLongProcessLoop(values) {
    values.collect {
        sleep 500
        it * 2
    }
}

def quickProcessLoop(values) {
    values.collect {
        it * 2
    }
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
    def finalLongLoopValues = asyncLongClosure()
    def finalQuickLoopValues = asyncQuickClosure()
    println "\n\n#################\nDone starting async processes. So we can do other stuff here\n################\n\n"

    print "Long values:"
    finalLongLoopValues.get().each {
        print " ${it}"
    }

    print "\nquick values:"
    finalQuickLoopValues.get().each {
        print " ${it}"
    }

}
