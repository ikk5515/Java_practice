package overloading;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
    Set.remove(i)의 시그니처는 remove(Object o)이다. 따라서 remove(int i)가 아닌 remove(Integer i)를 호출한다.
    Integer i는 Object o로 업캐스팅되어 호출되고, remove(Object o)는 해당 객체를 찾아서 삭제한다.

    List.remove(i)의 시그니처는 remove(int index)이다. 따라서 remove(Integer i)가 아닌 remove(int index)를 호출한다.
    remove(int index)는 해당 인덱스의 객체를 삭제한다.

    따라서 Set.remove(i)는 정상적으로 작동하지만, List.remove(i)는 예상치 못한 결과를 출력한다.

    이를 해결하기 위해서는 List.remove(i)를 List.remove((Integer) i)로 바꿔주면 된다.
 */
public class overloading2_wrong {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }

        for (int i = 0; i < 3; i++) {
            set.remove(i);
            list.remove(i);
        }

        System.out.println(set + "  " + list);
    }
}
