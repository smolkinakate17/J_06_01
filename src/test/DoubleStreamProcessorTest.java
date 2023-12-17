import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.*;

class DoubleStreamProcessorTest {

    @Test
    void countOf0() {
        Assertions.assertEquals(0, DoubleStreamProcessor.countOf0(DoubleStream.builder().build()));
        Assertions.assertEquals(0, DoubleStreamProcessor.countOf0(DoubleStream.of(1, 1, 1)));
        Assertions.assertEquals(3, DoubleStreamProcessor.countOf0(DoubleStream.of(0, 0, 0)));
        Assertions.assertEquals(0, DoubleStreamProcessor.countOf0(DoubleStream.of(0.01, 0.35, 0.75, 0.49, 0.99)));
        Random rnd = new Random(10);
        Assertions.assertEquals(5, DoubleStreamProcessor.countOf0(DoubleStream.
                of(0, 0, 0, 0, 0, 43, -64, 12345.0987654321, rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10))));
    }

    @Test
    void haveFractionalPart() {
        assertFalse(DoubleStreamProcessor.haveFractionalPart(DoubleStream.builder().build()));
        assertFalse(DoubleStreamProcessor.haveFractionalPart(DoubleStream.of(1, 1, 1)));
        assertFalse(DoubleStreamProcessor.haveFractionalPart(DoubleStream.of(0, 0, 0)));
        assertTrue(DoubleStreamProcessor.haveFractionalPart(DoubleStream.of(0.01, 0.35, 0.75, 0.49, 0.99)));
        Random rnd = new Random(10);
        assertTrue(DoubleStreamProcessor.haveFractionalPart(DoubleStream.
                of(0, 0, 0, 0, 0, 43, -64, 12345.0987654321, rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10))));
    }

    @Test
    void rangeOfValues() {
        Assertions.assertEquals(0, DoubleStreamProcessor.rangeOfValues(DoubleStream.builder().build()));
        Assertions.assertEquals(0, DoubleStreamProcessor.rangeOfValues(DoubleStream.of(1, 1, 1)));
        Assertions.assertEquals(0, DoubleStreamProcessor.rangeOfValues(DoubleStream.of(0, 0, 0)));
        Assertions.assertEquals(Math.abs(0.01 - 0.99), DoubleStreamProcessor.rangeOfValues(DoubleStream.of(0.01, 0.35, 0.75, 0.49, 0.99)));
        Random rnd = new Random(10);
        Assertions.assertEquals(Math.abs(12345.0987654321 - (-64)), DoubleStreamProcessor.rangeOfValues(DoubleStream.
                of(0, 0, 0, 0, 0, 43, -64, 12345.0987654321, rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                        rnd.nextDouble(-10, 10))));
    }

    @Test
    void array() {
        //все элементы больше limit
        assertArrayEquals(new double[]{1, 1, 1}, DoubleStreamProcessor.array(DoubleStream.of(1, 1, 1), 0));
        assertArrayEquals(new double[]{0, 0, 0}, DoubleStreamProcessor.array(DoubleStream.of(0, 0, 0), -100));
        assertArrayEquals(new double[]{0.01, 0.35, 0.75, 0.49, 0.99}, DoubleStreamProcessor.array(DoubleStream.of(0.01, 0.35, 0.75, 0.49, 0.99), -100));
        Random rnd = new Random(10);
        double[] arr = new double[]{0, 0, 0, 0, 0, 43, -64, 12345.0987654321, rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10)};
        assertArrayEquals(arr, DoubleStreamProcessor.array(Arrays.stream(arr), -100));
        //часть элементов больше limit
        assertArrayEquals(new double[]{0.75, 0.99}, DoubleStreamProcessor.array(DoubleStream.of(0.01, 0.35, 0.75, 0.49, 0.99), 0.49));
        assertArrayEquals(new double[]{43, 12345.0987654321}, DoubleStreamProcessor.array(Arrays.stream(arr), 40));
        //ни одно значение не больше limit
        assertArrayEquals(new double[0], DoubleStreamProcessor.array(DoubleStream.builder().build(), 0));
        assertArrayEquals(new double[0], DoubleStreamProcessor.array(DoubleStream.of(1, 1, 1), 100));
        assertArrayEquals(new double[0], DoubleStreamProcessor.array(DoubleStream.of(0.01, 0.35, 0.75, 0.49, 0.99), 100));
        assertArrayEquals(new double[0], DoubleStreamProcessor.array(Arrays.stream(arr), 100000));

    }

    @Test
    void maxLength() {
        assertEquals(0, DoubleStreamProcessor.maxLength(DoubleStream.builder().build()));
        assertEquals(1, DoubleStreamProcessor.maxLength(DoubleStream.of(1, 1, 1)));
        assertEquals(0, DoubleStreamProcessor.maxLength(DoubleStream.of(0, 0, 0)));
        assertEquals(0.356, DoubleStreamProcessor.maxLength(DoubleStream.of(0.01, 0.356, 0.75, 0.49, 0.99)));
        Random rnd = new Random(10);
        double[] arr = new double[]{0, 0, 0, 0, 0, 43, -64, 12345.0987654321, rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10),
                rnd.nextDouble(-10, 10)};
        String max = Arrays.stream(arr).mapToObj(String::valueOf).max(Comparator.comparingInt(String::length)).orElse("0");
        assertEquals(Double.parseDouble(max), DoubleStreamProcessor.maxLength(Arrays.stream(arr)));
    }
}