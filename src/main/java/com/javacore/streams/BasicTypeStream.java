package com.javacore.streams;

import javax.lang.model.element.VariableElement;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 基本类型流: Stream<Integer>等对象流每次都需要将基本类型进行包装, 比较低效, 使用基本类型流来解决这个问题
 *
 * @author ll
 */
public class BasicTypeStream {
    public static void main(String[] args) {
        // IntStream的of方法获取IntStream
        IntStream intStream = IntStream.of(1, 2, 3, 4);

        // Arrays.stream方法获取IntStream
        int[] arr = new int[]{1, 2, 3, 4};
        IntStream stream = Arrays.stream(arr);

        // generate, range方法获取IntStream
        IntStream generate = IntStream.generate(()->1);
        IntStream iterate = IntStream.iterate(0, num -> Integer.sum(num, 1));

        // range [0, 100), rangeClosed [0, 100]
        IntStream range = IntStream.range(0, 100);
        IntStream intStream1 = IntStream.rangeClosed(0, 100);

        // Stream  ---(mapToInt)---->   IntStream
        IntStream intStream2 = Stream.of("1", "2").mapToInt(String::length);

        // IntStream  ---(boxed)---->  Stream
        Stream<Integer> boxed = IntStream.of(1, 2, 3).boxed();

        //  IntStream.toArray, 注意是int基本类型
        int[] ints = IntStream.of(1, 23).toArray();

        // 基本类型流有直接的统计方法
        int sum = IntStream.of(1, 2, 3).sum();
        OptionalDouble average = IntStream.of(1, 2, 3).average();
        IntSummaryStatistics intSummaryStatistics = IntStream.of(1, 2, 3).summaryStatistics();
        long sum1 = intSummaryStatistics.getSum();


        // 随机数生成随机数流
        IntStream ints1 = new Random().ints();
    }
}
