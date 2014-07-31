import groovyx.gpars.dataflow.DataflowVariable

class StepD {

    final def value = new DataflowVariable()

    def process(input) {
        sleep 1000
        value << (3 * input.val)
    }
}
