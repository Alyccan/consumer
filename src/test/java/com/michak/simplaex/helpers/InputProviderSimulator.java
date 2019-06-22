package com.michak.simplaex.helpers;

import com.michak.simplaex.InputProvider;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputProviderSimulator implements InputProvider {

    private InputStream is = this.getClass().getResourceAsStream("/inputprovider.csv");
    private BufferedReader buf = new BufferedReader(new InputStreamReader(is));

    public InputProviderSimulator() throws FileNotFoundException {
    }

    @Override
    public String readLine() throws IOException {

        return buf.readLine();
    }
}
