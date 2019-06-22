package com.michak.simplaex;

import com.michak.simplaex.model.Result;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResultWriter {

    private int count = 1;
    private String targetFolderName;

    public ResultWriter(final String targetFolderName) {
        this.targetFolderName = targetFolderName;
    }

    public void writeToFile(Result result) throws IOException {

        List<List<String>> rows = new java.util.ArrayList<>(Collections.emptyList());
        result.getValuePerUserMap()
            .forEach((key, value) ->
                rows.add(Arrays.asList(key, value.getAverageValue().toString(), value.getRecentValue4().toString())));

        final File targetFolder = new File(targetFolderName);

        if (!targetFolder.exists()) {
            if (!targetFolder.mkdirs()) {
                throw new IllegalStateException("can't create directory " + targetFolder.getPath());
            }
        }

        try (java.io.FileWriter csvWriter = new java.io.FileWriter(new File(targetFolder.getPath(), count + ".txt"));) {
            csvWriter.append(result.getSumOfDataPoint5().toString());
            csvWriter.append("\n");
            csvWriter.append(String.valueOf(result.getUuidSet().size()));
            csvWriter.append("\n");
            for (List<String> rowData : rows) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            System.out.println(count + ".txt created");

            count++;
        }
    }
}
