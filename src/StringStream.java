import java.util.Arrays;
import java.util.stream.Collectors;

public class StringStream {
    public static String newString(String str) {
        String[] allWords = str.split("[^\\wА-Яа-я\\d]+");
        return Arrays.stream(allWords).distinct().filter(s -> s.length() > 2).map(String::toUpperCase).sorted(String::compareTo).collect(Collectors.joining(" "));
    }
}
