/*
From GPars Docs
 */

import static groovyx.gpars.GParsPool.withPool
def words = "This is just a plain text to count words in"

def count(arg) {
    withPool {
        return arg.parallel
                .map{[it, 1]}
                .groupBy{it[0]}.getParallel()
                .map {it.value=it.value.size();it}
                .sort{-it.value}.collection
    }
}

print count(words)