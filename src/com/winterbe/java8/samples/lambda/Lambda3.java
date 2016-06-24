package com.winterbe.java8.samples.lambda;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Common standard functions from the Java API.
 *
 * @author Benjamin Winterberg
 */
public class Lambda3 {

    @FunctionalInterface
    interface Fun {
        void foo();
    }

    public static void main(String[] args) throws Exception {
        /*
        Java 1.8 API 안에도 많은 built-in functional interface 가 존재한다.
        1.8에 새로 나온 인터페이스 말고도 이전에도 있었던 Comparator 나 Runnable 또한 여기 속한다.
        */

        // Predicates

        /*
        Predicate: 하나의 인자를 받고 boolean 반환하는 인터페이스, 주로 조건문에 쓰임
        또한 default method 로 and, or, negate 를 제공
        */
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();


        // Functions

        /*
        Function: 하나의 인자를 받고, 결과를 반환하는 인터페이스
        default method 로 compose(before)와 andThen(after)를 제공한다.
        */
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");     // "123"


        // Suppliers

        /*
        Supplier: 결과를 반환하는 인터페이스, 생산하는 함수이므로 인자를 받지 않는다.
        */
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person


        // Consumers

        /*
        Consumer: 하나의 인자를 받아서 행동하는 인터페이스, 소비하는 함수이므로 반환은 하지 않는다.
        */
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));



        // Comparators

        /*
        Comparator: 두 인자를 받아서 비교하고 int 결과를 반환하는 인터페이스,
         1.2부터 존재했지만 1.8에서 default method 들이 추가되었다.

        default method 로 비교 결과를 뒤집어서 반환하는 reverse(),
        특정 키 값을 얻어낸 다음, 비교하는 thenCompare()를 제공한다.
        */
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0


        // Runnables

        Runnable runnable = () -> System.out.println(UUID.randomUUID());
        runnable.run();


        // Callables

        Callable<UUID> callable = UUID::randomUUID;
        callable.call();
    }

}
