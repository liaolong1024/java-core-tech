package com.javacore.streams;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数据量大的时候, 并且数据可以很好的分成几个部分处理, 运算密集型才适合于并行流
 * 默认是使用ForkJoinPool.commonPool返回的fork-join池, 该线程池是全局共享的
 *
 * @author ll
 */
public class ParallelStream {
    public static void main(String[] args) {
        // 集合的paralleStream获取并行流
        Stream<Integer> integerStream = Arrays.asList(1, 2, 3).parallelStream();
        // 流的parallel方法获取并行流, 在终结方法执行前, 所有中间流都将被并行化
        Stream<Integer> parallel = Stream.of(1, 2, 3).parallel();

        // 放弃排序加快执行速度
        List<Integer> collect = Stream.of(1, 2, 2, 2, 3).parallel().unordered().distinct().collect(Collectors.toList());
        System.out.println(collect);

        // 获取一个自定义ForkJoinPool, 将并行流计算提交到该线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(()->Stream.of(1, 2, 3).parallel().collect(Collectors.toList()));

        // 提交异步任务
        CompletableFuture.supplyAsync(()->Stream.of(1, 23).collect(Collectors.toList()));
    }
}
