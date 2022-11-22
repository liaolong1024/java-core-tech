package com.javacore.streams;

import java.util.stream.Stream;

/**
 * @author ll
 */
public class LimitSkipConcat {
    public static void main(String[] args) {
        // 只要前几个元素
        Stream<Double> limit = Stream.generate(Math::random).limit(10);

        // 忽略前几个元素
        Stream<String> skip = Stream.of("1", "2", "3", "4").skip(1);

        // 谓词为真时获取所有元素, JDK8以上的方法
        // Stream.of("1", "2", "3", "4").takeWhile(s->"0123456789".contains(s));
        // Stream.of("1", "2", "3", "4").dropWhile(s->"0123456789".contains(s));

        // 将两个流连接起来, 1, 2, 3, 4
        Stream<String> concat = Stream.concat(Stream.of("1", "2"), Stream.of("3", "4"));
    }
}
