/*
Modified from GPars docs
 */
import groovyx.gpars.stm.GParsStm
import org.multiverse.api.references.TxnInteger
import static org.multiverse.api.StmUtils.newTxnInteger

import static groovyx.gpars.dataflow.Dataflow.task

final TxnInteger amount = newTxnInteger(0);

def t = task {
    (0..50).each {val->
        GParsStm.atomic {
            amount.increment(val);
        }
        sleep 100
    }
}

def checkedAmount = -1
def done = false

def t2 = task {
    while (!done) {
        GParsStm.atomic {
            def val = amount.get()
            if (val == checkedAmount) {
                done = true
            } else {
                checkedAmount = val
                println "Amount is at: ${val}"
            }
        }
        sleep 500
    }
}

[t, t2]*.join()

def finalVal = GParsStm.atomicWithInt {
    amount.get()
}

println "\n#####################\nFinal amount: ${finalVal}\n#####################"