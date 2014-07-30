def aSquared = { it->
    println "Calculating A Squared"
    sleep 4000
    it * it
}

def bSquared = {it->
    println "Calculating B Squared"
    sleep 4000
    it * it
}

def sum = {a, b->
    sleep 1000
    a + b
}

def squareRoot = {
    sleep 1000
    Math.sqrt(it)
}

def start = new Date()
def c  = squareRoot(sum(aSquared(3), bSquared(4)))
def end = new Date()

println "Calculated C: ${c} in ${(end.time - start.time)/1000} seconds."


