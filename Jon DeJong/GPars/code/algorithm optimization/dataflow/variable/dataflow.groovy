import groovyx.gpars.GParsPool

import static groovyx.gpars.dataflow.Dataflow.task

def stepA = new StepA()
def stepB = new StepB()
def stepC = new StepC()
def stepD = new StepD()
def finalStep = new FinalStep()

def start = new Date()
GParsPool.withPool(4) {
    task {
        println "Executing Step A"
        stepA.process(5)
        println "Step A is Done"
    }

    task {
        println "Executing Step B"
        stepB.process(stepA.value)
        println "Step B is Done"
    }

    task {
        println "Executing Step C"
        stepC.process(stepA.value)
        println "Step C is Done"
    }

    task {
        println "Executing Step D"
        stepD.process(stepA.value)
        println "Step D is Done"
    }

    task {
        println "Executing Final Step"
        finalStep.process(stepB.value, stepC.value, stepD.value)
    }
}

println "Done starting tasks"
println "Final Step returned: ${finalStep.value.val} in ${((new Date()).time - start.time)/1000} seconds"

