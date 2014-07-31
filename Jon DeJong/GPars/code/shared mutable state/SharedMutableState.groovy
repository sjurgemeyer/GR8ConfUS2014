class SharedMutableState {
    def people = [:]

    def load(id) {
        people[id]
    }

    def list() {
        people.values()
    }

    def save(Person person){
        println "State is saving person: ${person.firstName} ${person.lastName}"
        if(!person.id) {
            def id = people.keySet().size() > 0 ? people.keySet().max() + 1 : 0
            person.id = id
        }
        people[person.id] = person
    }

    def delete(Person person) {
        println "State is deleting person: ${person.firstName} ${person.lastName}"
        people.remove(person.id)
    }

    def ids() {
        people.keySet() as List
    }

    def random() {
        def keys = ids()
        def id = keys[new Random().nextInt(keys.size())]
        load(id)
    }

    def update(Person person) {
        println "State is updating person: ${person.firstName} ${person.lastName}."
        people[person.id] = person
    }



}
