

def stepA = {it->
    println "Starting step A"
    sleep 1000
    println "Finishing step A"
    2 * it
}

def stepB = {it->
    println "Starting step B"
    sleep 5000
    println "Finishing step B"
    3 * it
}

def stepC = {it->
    println "Starting step C"
    sleep 2000
    println "Finishing step C"
    4 * it
}

def stepD = {it->
    println "Starting step D"
    sleep 1000
    println "Finishing step D"
    3 * it
}

def start = new Date()

//def aVal = stepA(5)
//def finalVal = stepB(aVal) + stepC(aVal) + stepD(aVal)

def finalVal = stepB(stepA(5)) + stepC(stepA(5)) + stepD(stepA(5))

def end = new Date()

println "Calculated final value of ${finalVal} in ${(end.time - start.time)/1000} seconds"

