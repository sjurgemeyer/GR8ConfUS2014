import groovyx.gpars.dataflow.DataflowVariable

class StepC {

    final def value = new DataflowVariable()

    def process(input) {
        sleep 2000
        value << (4 * input.val)
    }
}
