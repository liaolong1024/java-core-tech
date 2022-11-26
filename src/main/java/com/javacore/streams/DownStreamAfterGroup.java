package com.javacore.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 处理分组后的数据
 * @author ll
 */

public class DownStreamAfterGroup {
    public static void main(String[] args) {
        Locale[] locales = Locale.getAvailableLocales();
        // 分组后再进行收集, 放在Set中
        Map<String, Set<Locale>> collect = Stream.of(locales).collect(
                Collectors.groupingBy(
                        Locale::getCountry, Collectors.toSet()
                )
        );
        // 分组后计算每个分组的元素个数
        Map<String, Long> collect1 = Stream.of(locales).collect(
                Collectors.groupingBy(
                        Locale::getCountry, Collectors.counting()
                )
        );
        // 计算每个分组的和中某个数据的和
        Map<String, Integer> collect2 = Stream.of(locales).collect(
                Collectors.groupingBy(
                        Locale::getCountry, Collectors.summingInt(l -> 1)
                )
        );
        // 分组后获取个分组的最大值
        Map<String, Optional<Locale>> collect3 = Stream.of(locales).collect(
                Collectors.groupingBy(
                        Locale::getCountry, Collectors.maxBy(Comparator.comparing(Locale::getLanguage))
                )
        );
        // 在上面处理的基础之上在加上一个处理步骤
        Map<String, Integer> collect4 = Stream.of(locales).collect(
                Collectors.groupingBy(
                        Locale::getCountry,
                        Collectors.collectingAndThen(
                                Collectors.toSet(), Set::size
                        )
                )
        );
        // 分组后做映射
        Map<String, Set<Integer>> collect5 = Stream.of(locales).collect(
                Collectors.groupingBy(
                        Locale::getCountry, Collectors.mapping(
                                l -> l.getLanguage().length(), Collectors.toSet()
                        )
                )
        );
        // 收集统计数据IntSummaryStatistics: summarizingInt(...)

        // 分组后过滤 (jdk 9)
        // Stream.of(locales).collect(
        //         Collectors.groupingBy(Locale::getCountry, Collectors.filtering(...))
        // )
    }
}
