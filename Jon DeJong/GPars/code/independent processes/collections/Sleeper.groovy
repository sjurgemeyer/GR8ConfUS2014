class Sleeper {

    def seconds
    def message

    Sleeper(seconds) {
        this.seconds = seconds
        message = "Finished my ${seconds} second sleeper."
    }

    def sleep() {
        sleep seconds * 1000
        message
    }

}