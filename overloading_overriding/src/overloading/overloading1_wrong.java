package overloading;

import java.math.BigInteger;
import java.util.*;

/*
    오버로딩된 메소드 가운데 어떤 메소드가 호출할 것인지 컴파일 시점에 결정.
    이러한 이유로 컴파일 시점에 Collection<?> 타입이였던 객체 모두 Collection 을 파라미터로 가지는 메서드가 실행

    오버라이드한 메서드는 동적으로, 오버로딩한 메서드는 정적으로 선택된다!!
    이러한 문제를 해결하기 위해서는 instanceof 을 사용하면 된다.
        => (부모를 상속해서 만들어진 자식 객체가 여러 타입인 경우에 특정 클래스가 맞는지 확인하기 위해 사용)
 */
public class overloading1_wrong {
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

    public static String classify(Collection<?> s) {
        return "Collections";
    }
}