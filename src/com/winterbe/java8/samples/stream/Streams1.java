package com.winterbe.java8.samples.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class Streams1 {

    public static void main(String[] args) {
        /*
        1.8 Stream 순열에 하나 이상의 연산을 적용하는 걸 말함 (입출력 스트림과는 다르다)

        스트림은 중간에서 스트림을 받아서 연산 후 그 스트림을 내놓을 수도 있고 (intermediate)
        맨 끝에서 스트림을 받은 다음 특정 결과를 내놓을 수도 있다. (terminal)

        stream.oper1().oper2().oper3().oper4().result();가 가능하다는 말

        스트림은 기본적으로 list 나 set 으로 만든다.(map 은 지원하지 않는다)

        스트림은 순차적으로 할 수도 있고, 병행적으로 할 수도 있다.
         */

        // 먼저 스트림을 만들기 위해 필요한 리스트 하나 생성한다.
        // 스트림은 리스트.stream()으로도 만들어지고, Stream.of(가변인자)로도 만들어진다.
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");


        // filtering
        /*
        Filter: Predicate 를 받아서 조건에 따라 일부 원소를 걸러낸다.
        이 연산은 스트림을 반환하므로 intermediate 다.
        */
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        // forEach 는 각각의 원소에 대해 결과를 뽑아내는 연산이므로 terminal 이다.

        // "aaa2", "aaa1"


        // sorting
        /*
        Sorted: 원소들을 정렬한다. 인자가 없다면 natural order 를 따르고,
        인자로 Comparator 를 넘겨주면 거기에 맞게 정렬한다.
         */
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println); // 아까와 달리 정렬된 상태로 나온다.

        // "aaa1", "aaa2"

        // sorted 를 한다고 해서 원본인 리스트가 정렬되는 건 아니다.
        System.out.println(stringCollection);


        // mapping
        /*
        Map: 각각의 원소를 다른 객체로 바꾼다. 바뀐 원소들의 스트림을 반환하므로
        intermediate 다.
        */
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);

        // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"


        // matching
        /*
        Match: filter 와 달리 match 는 스트림 전체에 대해 Predicate 가
        true 인지 확인한다. terminal.
        */

        // 스트림 전체에서 a로 시작하는 문자열이 하나라도 있는지 확인
        boolean anyStartsWithA = stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        // 스트림 전체의 문자열들이 전부 a로 시작하는지 확인
        boolean allStartsWithA = stringCollection
                .stream()
                .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        // 스트림 전체에 대해서 문자열들이 전부 z로 시작하지 않는지 확인
        boolean noneStartsWithZ = stringCollection
                .stream()
                .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true


        // counting
        /*
        Count: 스트림에 원소가 몇 개인지 반환한다. terminal.
         */
        long startsWithB = stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .count();

        System.out.println(startsWithB);    // 3


        // reducing
        /*
        Reduce: 스트림을 하나의 값으로 줄여서 Optional 객체에 넣는다. terminal.
         */
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"


    }

}
