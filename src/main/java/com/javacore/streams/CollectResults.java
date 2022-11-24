package com.javacore.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ll
 */
public class CollectResults {
    public static void main(String[] args) {

        // 使用获取流的iterator来遍历元素
        Iterator<String> iterator = Stream.of("1", "2", "3").iterator();
        // 使用foreach方法遍历元素
        Stream.of("1", "2", "3").forEach(System.out::println);
        // 对于并行流, foreach遍历的顺序的不确定的, 可以使用foreachOrdered, 但是丧失并行处理的部分
        Stream.of("1", "2", "3").parallel().forEachOrdered(System.out::println);


        // 将结果收集到数组中, 默认返回Object数组
        Object[] objects = Stream.of("1", "2", "3").toArray();
        // 将结果收集到数组中, 指定返回的元素类型
        String[] strings = Stream.of("1", "2", "3").toArray(String[]::new);

        // 将结果收集到List中
        List<String> collect = Stream.of("1", "2", "3").collect(Collectors.toList());
        // 将结果收集到Set中, 默认HashSet
        Set<String> collect1 = Stream.of("1", "2", "3").collect(Collectors.toSet());
        // 将结果收集到Set中, 明确指定Set类型
        TreeSet<String> collect2 = Stream.of("1", "2", "3").collect(Collectors.toCollection(TreeSet::new));


        // 将流中所有字符串连接在一起, 无分隔符, 该功能也可用String.join方法实现
        String collect3 = Stream.of("1", "2", "3").collect(Collectors.joining());
        // 将流中所有字符串连接在一起, 指定分隔符, 该功能也可以用String.join方法实现
        String collect4 = Stream.of("1", "2", "3").collect(Collectors.joining(","));

        // 获取流的统计数据, 包括总和, 数量, 平均值, 最大/小值
        IntSummaryStatistics collect5 = Stream.of("1", "2", "3").collect(Collectors.summarizingInt(String::length));
        long sum = collect5.getSum();
        long count = collect5.getCount();
        double average = collect5.getAverage();
        int max = collect5.getMax();
        int min = collect5.getMin();

    }
}
