package com.michak.simplaex;

import java.io.IOException;

class ConsumerServer {

    private final InputProvider inputProvider;
    private final InputProcessorFactory inputProcessorFactory;
    private final ResultWriter resultWriter;
    private final int numberOfFilesToCreate;

    ConsumerServer(
        final InputProvider inputProvider,
        final InputProcessorFactory inputProcessorFactory,
        final ResultWriter resultWriter,
        final int numberOfFilesToCreate
    ) {
        this.inputProvider = inputProvider;
        this.inputProcessorFactory = inputProcessorFactory;
        this.resultWriter = resultWriter;
        this.numberOfFilesToCreate = numberOfFilesToCreate;
    }

    void start() throws IOException, InterruptedException {

        if (numberOfFilesToCreate == -1) {
            while (true) {
                execute();
            }
        }
        for (int i = 0; i < numberOfFilesToCreate; i++) {
            execute();
        }

    }

    private void execute() throws IOException, InterruptedException {
        final InputProcessor inputProcessor = inputProcessorFactory.produce();
        while (inputProcessor.shouldContinue()) {
            final String line = inputProvider.readLine();
            System.out.println("processing line " + line);
            inputProcessor.processInput(line);
        }
        resultWriter.writeToFile(inputProcessor.getResult());
    }

}
