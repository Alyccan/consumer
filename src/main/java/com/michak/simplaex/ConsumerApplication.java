package com.michak.simplaex;

import java.io.IOException;

public class ConsumerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Consumer application started");
        new ConsumerServer(
            new InputProviderImpl(9000, 1000),
            new InputProcessorFactory(1000),
            new ResultWriter("results"),
            -1
        ).start();
    }
}
