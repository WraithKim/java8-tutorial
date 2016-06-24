package com.winterbe.java8.samples.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Benjamin Winterberg
 */
public class Maps1 {

    public static void main(String[] args) {
        /*
        Map 은 앞서 말한대로 스트림을 지원하지 않는다. 그 대신 비슷하게 할 수 있도록
        여러 메서드가 추가되었다. 주로 기존 메서드에 null 값 확인이 추가되었다.
         */

        // putIfAbsent() 메서드는 map 에 넣기 전에 키 값 쌍이 이미 있는지 확인하고 없으면 넣는다.
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
            // 더 이상 부가적으로 키 값 쌍이 이미 있는지 없는지 코드를 더 적을 필요가 없다.
        }

        // map 의 forEach 는 키 값 쌍을 반환한다.
        map.forEach((id, val) -> System.out.println(val));

        /*
        여러 가지 map 에 추가된 메서드들
         */
        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));             // val33

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));     // false

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));    // true

        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));             // val33, 이미 key = 3에는 값이 있기 때문에 num -> "bam"이 실행되지 않음.

        // 없으면 기본 값 반환하게 해서 안전하게 값을 받아오는 방법
        System.out.println(map.getOrDefault(42, "not found"));      // not found

        /*
        안전하게 map 의 키 값 쌍을 제거하는 방법
         */
        map.remove(3, "val3"); // 인자로 들어온 키, 값 둘 다 동일한 키 값 쌍을 제거
        System.out.println(map.get(3));             // 값이 다르므로 (3, "val33")은 남아있다.

        map.remove(3, "val33");
        System.out.println(map.get(3));             // null, 인자의 키 값과 일치하므로 키 값 쌍이 제거됨

        // 맵의 값들을 합침
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));              // val9, 원래 값이 없었으므로 새로운 값을 넣는다.

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9concat, 원래 값이 존재하므로 새 값을 뒤에 이어붙인다.
    }

}