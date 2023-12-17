import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class DoubleStreamProcessor {
    public static int countOf0(DoubleStream doubleStream) {
        return (int) doubleStream.filter(value -> value == 0.0).count();
    }

    public static boolean haveFractionalPart(DoubleStream doubleStream) {
        return doubleStream.anyMatch(value -> value % 1 != 0);
    }

    public static double rangeOfValues(DoubleStream doubleStream) {
        var array = doubleStream.toArray();
        OptionalDouble max = Arrays.stream(array).max();
        OptionalDouble min = Arrays.stream(array).min();
        if (max.isPresent() && min.isPresent()) {
            return Math.abs(max.getAsDouble() - min.getAsDouble());
        }
        return 0;
    }

    public static double[] array(DoubleStream doubleStream, double limit) {
        return doubleStream.filter(value -> value > limit).toArray();
    }

    public static double maxLength(DoubleStream doubleStream) {
        var newStream = doubleStream.mapToObj(String::valueOf);
        Optional<String> max = newStream.max(Comparator.comparingInt(String::length));
        return max.map(Double::parseDouble).orElse((double) 0);
    }
}
