package com.michak.simplaex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

class InputProviderImpl implements InputProvider {

    private final ServerSocket serverSocket;
    private BufferedReader reader;
    private long timeout;

    public InputProviderImpl(int port, long timeout) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.timeout = timeout;
    }

    public String readLine() throws IOException, InterruptedException {

        if (reader == null) {
            System.out.println("reader == null");
            reader = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));
            System.out.println("new connection established");

        } else if (!reader.ready()) {
            System.out.println("reader is not ready. Let's wait " + timeout + " millis");
            Thread.sleep(timeout);

            if (!reader.ready()) {
                System.out.println("reader is not ready after timeout.");
                reader.close();
                System.out.println("reader closed.");
                reader = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));
                System.out.println("new connection established");
            }
        }
        return reader.readLine();
    }
}
