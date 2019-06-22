package com.michak.simplaex;

import com.michak.simplaex.helpers.InputProviderSimulator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ConsumerServerTest {

    @Test
    public void positive() throws Exception {

        final ConsumerServer consumerServer = new ConsumerServer
            (
                new InputProviderSimulator(),
                new InputProcessorFactory(2),
                new ResultWriter("servertestresults"),
                2
            );
        consumerServer.start();

        Assertions.assertThat(Objects.requireNonNull(new File("servertestresults").list()).length).isEqualTo(2);

        //check first file
        String expectedResult1 =
            "55216548\n" +
                "2\n" +
                "023316ec-c4a6-3e88-a2f3-1ad398172ada,0.3790267017026414,652953292\n" +
                "0977dca4-9906-3171-bcec-87ec0df9d745,0.1871548412292238,982761284\n";

        Assertions.assertThat(getResultAsString("servertestresults/1.txt")).isEqualTo(expectedResult1);

        //check second file
        String expectedResult2 =
            "132848928\n" +
                "1\n" +
                "5fac6dc8-ea26-3762-8575-f279fe5e5f51,0.7626710614484215,9005421520\n";

        Assertions.assertThat(getResultAsString("servertestresults/2.txt")).isEqualTo(expectedResult2);

        //delete
        FileUtils.deleteDirectory(new File("servertestresults"));
    }

    private String getResultAsString(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        return sb.toString();
    }
}
