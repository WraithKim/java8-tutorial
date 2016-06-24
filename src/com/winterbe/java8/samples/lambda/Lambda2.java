package com.winterbe.java8.samples.lambda;

/**
 * @author Benjamin Winterberg
 */
public class Lambda2 {

    // 이 어노테이션은 개발자가 functional interface로 쓸 인터페이스에 두 개 이상의
    // 추상 메서드를 선언했는지 컴파일러가 확인하게 함.
    @FunctionalInterface
    public static interface Converter<F, T> {
        /*
       각각의 람다는 인터페이스를 통해 정의할 수 있음(이전의 익명클래스->람다 표현식 예제를 보면 당연함)

       람다 식을 보면 알지만 람다는 하나의 메서드만 정의함. 그래서 람다로 표현할 수 있는 인터페이스는 하나의
       추상 메서드만 선언할 수 있음. 이런 인터페이스를 Functional Interface 라고 부름.

       단, default method 는 람다 식으로 구현할 필요가 없기 때문에 functional interface 에 몇 개를 정의해도 상관 없음
        */
        T convert(F from);
    }

    static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    public static void main(String[] args) {
        Converter<String, Integer> integerConverter1 = (from) -> Integer.valueOf(from);
        Integer converted1 = integerConverter1.convert("123");
        System.out.println(converted1);   // result: 123


        // method reference
        /*
        "::" 키워드는 이미 있는 메서드를 참조할 때 쓰는 키워드이다.
         */

        // 이전 Functional Interface 예제는 다음과 같이 줄어들 수 있다.
        // static 인 경우, Class명::static메서드명
        Converter<String, Integer> integerConverter2 = Integer::valueOf;
        Integer converted2 = integerConverter2.convert("123");
        System.out.println(converted2);   // result: 123

        // 멤버 메서드인 경우, 변수명::메서드명
        Something something = new Something();

        Converter<String, String> stringConverter = something::startsWith;
        String converted3 = stringConverter.convert("Java");
        System.out.println(converted3);    // result J

        // constructor reference

        // 생성자에도 적용 가능, Class명::new
        // 매개변수 형태가 일치하는 생성자를 호출함.
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
    }
}
