package com.javacore.streams;

import java.util.stream.Stream;

/**
 * @author ll
 */
public class DistinctSortedPeek {
    public static void main(String[] args) {
        // 去除重复元素
        Stream<String> distinct = Stream.of("1", "1", "1", "1").distinct();

        // 排序-Comparator
        Stream<Integer> sortedComparator = Stream.of(4, 5, 3, 5).sorted(Integer::compareTo);
        // 排序-Comparable
        Stream<Integer> sortComparable = Stream.of(4, 5, 3, 5).sorted();

        // peek方法调用不会过滤或修改流, 但每个元素都会作为Consumer接口函数参数调用一次, 方便进行流的程序调试
        Object[] objects = Stream.iterate(1.0, p -> p * 2).peek(System.out::println).limit(20).toArray();

    }
}
