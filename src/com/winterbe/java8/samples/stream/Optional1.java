package com.winterbe.java8.samples.stream;

import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class Optional1 {

    public static void main(String[] args) {
        /*
        Optional: NullPointerException 을 막기 위해 쓰는 클래스
        null 을 반환할 수도 있는 메서드를 Optional 을 반환하도록 하면
        null 이든 아니든 메서드는 Optional 객체를 반환하므로 예외가 발생하지 않음.
        */
        Optional<String> optional = Optional.of("bam");

        System.out.println(optional.isPresent()); // Optional 안에 값이 있는지 확인
        System.out.println(optional.get());
        Optional<String> optional2 = Optional.empty();
        // System.out.println(optional2.get()); // 이 때는 NoSuchElementException 이 반환됨
        System.out.println(optional2.orElse("fallback")); // Optional 안에 값이 없으면 인자의 값을 반환

        optional.ifPresent(s -> System.out.println(s.charAt(0))); // 보통은 ifPresent()를 써서 null 이 아닐 때
        // 뭘 해야 할지 정해줌, null 이면 그냥 실행 안함
    }

}