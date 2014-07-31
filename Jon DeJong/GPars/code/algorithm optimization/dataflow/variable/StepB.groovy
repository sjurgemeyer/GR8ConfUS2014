import groovyx.gpars.dataflow.DataflowVariable

class StepB {

    final def value = new DataflowVariable()

    def process(input) {
        sleep 5000
        value << (3 * input.val)
    }
}
