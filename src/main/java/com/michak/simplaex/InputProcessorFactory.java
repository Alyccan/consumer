package com.michak.simplaex;

public class InputProcessorFactory {

    private Integer numberOfLinesToProcess;

    public InputProcessorFactory(final Integer numberOfLinesToProcess) {
        this.numberOfLinesToProcess = numberOfLinesToProcess;
    }

    public InputProcessor produce() {
        return new InputProcessor(numberOfLinesToProcess);
    }

}
