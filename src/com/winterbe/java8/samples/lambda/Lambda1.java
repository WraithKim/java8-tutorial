package com.winterbe.java8.samples.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class Lambda1 {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        /*
        기존의 익명 클래스를 이용한 Collections.sort() 사용
        익명 클래스로써 객체를 생성해서 sort 메서드보고 정렬할 때 비교하는 방법을 제공함.
         */
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

         /*
        람다 표현식을 이용한 Collections.sort() 사용
         */

        // 가장 기본적으로 보는 형태(그나마 처음 보면 이해할 수 있는 형태)
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        // 어차피 String a와 b를 받으면 b.compareTo(a)를 반환하는 거 한 줄 써져있으니까 중괄호 없애자
        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        // 옆에 리스트에서 a와 b를 인자로 받는거면 리스트만 보고도
        // 자료형이 뭔지 알 수 있으니까 굳이 자료형 두번 적지 말자
        Collections.sort(names, (a, b) -> b.compareTo(a));

        // 만약 자료형을 추측할 수 없는 경우에는 자료형을 생략하면 안된다.
        // (예로 들어 한 메서드가 오버로딩 되어서 (int, int)를 받는 경우와 (String, String)을 받는 경우가
        // 있다면, 둘 다 매개변수가 두 개이기 때문에 자료형을 생략하면 어느 메서드인지 애매함)

        System.out.println(names);

        names.sort(Collections.reverseOrder());

        System.out.println(names);

        List<String> names2 = Arrays.asList("peter", null, "anna", "mike", "xenia");
        names2.sort(Comparator.nullsLast(String::compareTo));
        System.out.println(names2);

        List<String> names3 = null;

        Optional.ofNullable(names3).ifPresent(list -> list.sort(Comparator.naturalOrder()));

        System.out.println(names3);
    }

}