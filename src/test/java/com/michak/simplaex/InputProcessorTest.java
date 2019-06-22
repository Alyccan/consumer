package com.michak.simplaex;

import com.michak.simplaex.model.Result;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class InputProcessorTest {

    @Test
    public void hasNextTrue() {
        final InputProcessor inputProcessor = new InputProcessor(1);
        Assertions.assertThat(inputProcessor.shouldContinue()).isTrue();
    }

    @Test
    public void hasNextFalse() {
        final InputProcessor inputProcessor = new InputProcessor(1);
        inputProcessor.processInput(
            "e0b0d876-c40f-3fa1-a878-8943f797a073,YMhHQa2bbSHrjP9+hgbkyqnqZ38=,0.43172933354362786,2060964419,6260490080057718427"
        );
        Assertions.assertThat(inputProcessor.shouldContinue()).isFalse();
    }

    @Test
    public void processInputPositive() {
        final InputProcessor inputProcessor = new InputProcessor(1);
        inputProcessor.processInput(
            "e0b0d876-c40f-3fa1-a878-8943f797a073,YMhHQa2bbSHrjP9+hgbkyqnqZ38=,0.43172933354362786,2060964419,6260490080057718427"
        );
        final Result result = inputProcessor.getResult();
        Assertions.assertThat(result.getUuidSet().size()).isEqualTo(1);
        Assertions.assertThat(result.getUuidSet().contains("e0b0d876-c40f-3fa1-a878-8943f797a073"));
    }

    @Test
    public void incorrectLineFromProducer() {
        final InputProcessor inputProcessor = new InputProcessor(1);
        inputProcessor.processInput("lalala");
        final Result result = inputProcessor.getResult();
        Assertions.assertThat(result.getUuidSet().size()).isEqualTo(0);
    }
}
