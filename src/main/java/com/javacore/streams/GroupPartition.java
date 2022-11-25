package com.javacore.streams;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ll
 */
public class GroupPartition {
    public static void main(String[] args) {
        Locale[] locales = Locale.getAvailableLocales();
        // 根据国家分组
        Map<String, List<Locale>> collect = Stream.of(locales).collect(Collectors.groupingBy(Locale::getCountry));

        // 将流中元素分成两类, 使用partitionBy更为高效
        Map<Boolean, List<Locale>> englishAndOther = Stream.of(locales).collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
    }
}
