import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

class SlowReceiver extends DefaultActor {

    void act() {

        def responses = ["Done", "Finished", "Completed"]

        loop {
            react { Long seconds->
                println "Processing for ${seconds}"
                sleep seconds * 1000
                reply responses[new Random().nextInt(3)]
            }
        }
    }
}

class FastSender extends DefaultActor {
    Actor receiver

    void act() {
        //Send something that takes between 1 and 5 seconds to process
        def seconds = new Random().nextInt(4) + 1
        println "Sending a message to process for ${seconds}"
        receiver.send(seconds)
        react { msg->
            println "${msg} processing for ${seconds} seconds."
        }

    }
}

def receiver = new SlowReceiver()

receiver.start()

while(true) {
    def senderCounts = new Random().nextInt(4) + 1
    println "Creating ${senderCounts} senders"
    (0..senderCounts).each {
        new FastSender(receiver: receiver).start()
        sleep 500
    }
    sleep 5000
}
