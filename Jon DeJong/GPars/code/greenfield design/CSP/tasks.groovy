/**
 * From GPars docs
 */
import groovyx.gpars.dataflow.DataflowQueue
import static groovyx.gpars.dataflow.Dataflow.task

def words = ['Groovy', 'fantastic', 'concurrency', 'fun', 'enjoy', 'safe', 'GPars', 'data', 'flow']
final def buffer = new DataflowQueue()


def t1 = task {
    for (word in words) {
        buffer << word.toUpperCase()
        sleep 500
    }
}

def t2 = task {
    def count = 0
    while (count++ < words.size()) {
        println(buffer.val)
    }
}

[t1, t2]*.join()

