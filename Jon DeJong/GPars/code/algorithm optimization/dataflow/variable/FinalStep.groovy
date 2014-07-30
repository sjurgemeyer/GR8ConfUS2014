import groovyx.gpars.dataflow.DataflowVariable

class FinalStep {

    final def value = new DataflowVariable()

    def process(input1, input2, input3) {
        sleep 5000
        value << (input1.val + input2.val + input3.val)
    }
}
