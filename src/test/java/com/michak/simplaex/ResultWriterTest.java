package com.michak.simplaex;

import com.michak.simplaex.model.Result;
import com.michak.simplaex.model.ValuePerUser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ResultWriterTest {

    @Test
    public void writeToFile() throws IOException {

        //create Result
        final Result result = new Result();
        result.addToUuidSet("uuid-1");
        result.addToUuidSet("uuid-2");
        result.addToUuidSet("uuid-2");
        result.setSumOfDataPoint5(new BigDecimal(10));
        Map<String, ValuePerUser> valuePerUserMap = new HashMap<>();
        result.setValuePerUserMap(valuePerUserMap);
        valuePerUserMap.put("uuid-1", new ValuePerUser("uuid-1", new BigDecimal(5), new BigDecimal(7)));
        valuePerUserMap.put("uuid-2", new ValuePerUser("uuid-2", new BigDecimal(15), new BigDecimal(17)));

        //write to file
        final ResultWriter resultWriter = new ResultWriter("testresults");
        resultWriter.writeToFile(result);

        //read result from file
        InputStream is = new FileInputStream("testresults/1.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        String fileAsString = sb.toString();

        String expectedResult =
            "10\n" +
                "2\n" +
                "uuid-1,5.0000000000000000,7\n" +
                "uuid-2,15.0000000000000000,17\n";

        //assert
        Assertions.assertThat(fileAsString).isEqualTo(expectedResult);

        //delete
        FileUtils.deleteDirectory(new File("testresults"));
    }
}
