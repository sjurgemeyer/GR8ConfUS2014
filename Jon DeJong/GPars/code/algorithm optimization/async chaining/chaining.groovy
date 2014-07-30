import groovyx.gpars.GParsPool

GParsPool.withPool(4) {
    def aSquared = { a->
        sleep 4000
        a * a
    }.asyncFun()

    def bSquared = {b->
        sleep 4000
        b * b
    }.asyncFun()

    def sum = {a, b->
        sleep 1000
        a + b
    }.asyncFun()

    def squareRoot = {c->
        sleep 1000
        Math.sqrt(c)
    }.asyncFun()

    def done = false
    def start = new Date()
    def c  = squareRoot(sum(aSquared(3), bSquared(4)))

    c.whenBound { val->
        def end = new Date()
        done =  true
        println "\n\nCalculated C: ${val} in ${(end.time - start.time)/1000} seconds."
    }

    print "\nWhile this is running let's do something else."
    while(!done) {
        sleep 500
        print "."
    }

}