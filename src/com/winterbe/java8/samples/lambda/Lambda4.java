package com.winterbe.java8.samples.lambda;

/**
 * @author Benjamin Winterberg
 */
public class Lambda4 {

    static int outerStaticNum;

    int outerNum;

    /*
    익명 클래스와 마찬가지로 람다 식은 멤버 변수와 static 멤버 변수도 접근 가능
     */
    void testScopes() {
        /*
        람다 표현식의 변수 접근 범위는 익명 클래스와 매우 비슷하다
         */

        // 지역 변수는 final 이거나 final 처럼 변경이 되지 않는 지역변수의 경우 접근 가능

        int num = 1;

        Lambda2.Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);

        String convert = stringConverter.convert(2);
        System.out.println(convert);    // 3

        // 익명클래스와는 달리 Default Method 는 사용할 수 없다.
        // Formula formula = a -> sqrt(a * 100);

        Lambda2.Converter<Integer, String> stringConverter2 = (from) -> {
            outerNum = 13;
            return String.valueOf(from);
        };

        String[] array = new String[1];
        Lambda2.Converter<Integer, String> stringConverter3 = (from) -> {
            array[0] = "Hi there";
            return String.valueOf(from);
        };

        stringConverter3.convert(23);

        System.out.println(array[0]);
    }

    public static void main(String[] args) {
        new Lambda4().testScopes();
    }

}