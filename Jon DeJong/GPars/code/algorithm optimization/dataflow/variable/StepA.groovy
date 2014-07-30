import groovyx.gpars.dataflow.DataflowVariable

class StepA {

    final def value = new DataflowVariable()

    def process(input) {
        sleep 1000
        value << (2 * input)
    }
}
