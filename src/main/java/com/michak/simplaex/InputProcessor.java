package com.michak.simplaex;

import com.michak.simplaex.model.ProducerEntry;
import com.michak.simplaex.model.Result;
import com.michak.simplaex.model.ValuePerUser;
import java.math.BigDecimal;
import java.util.Map;

public class InputProcessor {

    private int numberOfLinesToProcess;
    private int linesProcessed;
    private Result result = new Result();

    public InputProcessor(final int numberOfLinesToProcess) {
        this.numberOfLinesToProcess = numberOfLinesToProcess;
    }

    boolean shouldContinue() {
        if (linesProcessed < numberOfLinesToProcess) {
            System.out.println("Should continue");
        } else {
            System.out.println("Should not continue. "
                + linesProcessed + "lines processed. "
                + numberOfLinesToProcess + " number of lines to process.");
        }
        return linesProcessed < numberOfLinesToProcess;
    }

    public void processInput(String line) {

        Map<String, ValuePerUser> valuePerUserMap = result.getValuePerUserMap();

        final ProducerEntry producerEntry;
        try {
            producerEntry = new ProducerEntry(line);
            System.out.println("InputProcessor parsed line: " + line);
        } catch (Exception e) {
            System.out.println("InputProcessor can't parse line: " + line);
            return;
        }

        final String uuid = producerEntry.getUuid();
        result.addToUuidSet(uuid);

        if (!valuePerUserMap.containsKey(uuid)) {

            valuePerUserMap.put(uuid, new ValuePerUser(uuid, producerEntry.getFloatingPointValue(), producerEntry.getAnInteger()));

        } else {
            final ValuePerUser valuePerUser = valuePerUserMap.get(uuid);
            valuePerUser.setCount(valuePerUser.getCount().add(new BigDecimal(1)));
            valuePerUser.setSum(valuePerUser.getSum().add(producerEntry.getFloatingPointValue()));
            valuePerUser.setRecentValue4(producerEntry.getAnInteger());
        }

        result.setSumOfDataPoint5(result.getSumOfDataPoint5().add(producerEntry.getAnotherInteger()));

        System.out.println(linesProcessed + " lines processed");
        linesProcessed++;
    }

    public Result getResult() {
        return result;
    }
}
