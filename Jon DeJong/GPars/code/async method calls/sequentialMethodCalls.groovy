
def reallyLongProcessLoop(values) {
    values.collect {
        sleep 500
        it * 2
    }.each {
        print " ${it}"
    }

}

def quickProcessLoop(values) {
    values.collect {
        it * 2
    }.each {
        print " ${it}"
    }
}

println "Long process: "
reallyLongProcessLoop(1..25)
println "\nQuick process: "
quickProcessLoop(26..50)
println "\nThose are done. We can do something else now"
