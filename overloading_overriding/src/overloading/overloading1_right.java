package overloading;

import java.math.BigInteger;
import java.util.*;

public class overloading1_right {
    public static void main(String[] args) {

        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };
        for (Collection<?> c : collections) {
            System.out.println(classify(c));
        }
    }

    public static String classify(Set<?> s) {
        return "Set";
    }

    public static String classify(List<?> s) {
        return "List";
    }

    public static String classify(Collection<?> c) {
        return c instanceof Set ? "Set" :
                c instanceof List ? "List" : "Collections";
    }
}