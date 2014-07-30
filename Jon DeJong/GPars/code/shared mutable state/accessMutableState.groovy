import groovyx.gpars.agent.Agent
import groovyx.gpars.group.DefaultPGroup

def sms = new SharedMutableState()

// Put three in state to start
sms.save(new Person(firstName: 'Jon', lastName: 'DeJong', age: '34'))
sms.save(new Person(firstName: 'Megan', lastName: 'Jensen', age: '23'))
sms.save(new Person(firstName: 'Jimmy', lastName: 'Jameson', age: '42'))

def smsAgent = new Agent(sms)

def addNewPerson(agent, firstNames, lastNames) {
    agent << { state ->
        // Do all your processing before saving...
        sleep 500
        state.save(new Person(
                firstName: firstNames[new Random().nextInt(firstNames.size)],
                lastName: lastNames[new Random().nextInt(lastNames.size)],
                age: (new Random().nextInt(54) + 1)
        ))
    }
}

def updatePerson(agent) {
    agent << { state ->
        print "\nUpdate is starting. "
        def person = state.random()
        print "with ${person.firstName} ${person.lastName} who is ${person.age} years old. "
        //Take a wile to update this person
        sleep 1000
        person.age++
        print "\nDone working with ${person.firstName} ${person.lastName}. They are now ${person.age} years old."
        state.update(person)
    }
}

def deletePerson(agent) {
    agent << { state ->
        def person = state.random()
        //do a bunch stuff with this before deleting
        sleep 500
        state.delete(person)
    }
}

def firstNames = ['Sookie', 'Bill', 'Eric', 'Sam', 'Tara', 'Lafayette']
def lastNames = ['Stackhouse', 'Compton', 'Northman', 'Merlotte', 'Thornton', 'Reynolds']

(0..25).each{
    def switchVal = new Random().nextInt(3)

    switch (switchVal) {
        case 0:
            addNewPerson(smsAgent, firstNames, lastNames)
            break

        case 1:
            updatePerson(smsAgent)
            break

        case 2:
            deletePerson(smsAgent)

    }
    sleep 500
}
