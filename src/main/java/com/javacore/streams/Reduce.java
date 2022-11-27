package com.javacore.streams;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author ll
 */
public class Reduce {
    public static void main(String[] args) {
        // (a, b) -> c  从流中前两个元素开始, 作为a,b, 计算返回c, 然后c作为a, 下一个元素作为b, 再次计算出新的c, 以此类推
        // reduce求和
        Optional<Integer> reduce = Stream.of(1, 2, 3, 4, 5).reduce(Integer::sum);

        // 传入初始值, 注意此时返回类型是Integer
        Integer reduce1 = Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);

        // 字符串长度求和
        Integer reduce2 = Stream.of("abc", "cd").reduce(0, (total, str) -> total + str.length(), Integer::sum);
        // 更好的替换方法-字符串长度求和
        int sum = Stream.of("abc", "cd").mapToInt(String::length).sum();
    }
}
