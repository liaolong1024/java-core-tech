package com.javacore.streams;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ll
 */
public class CollectToMap {
    public static void main(String[] args) {

        Stream<People> peopleStream = Stream.of(new People("1"), new People("2"));
        // 将对象流存储到Map中, 形成id->people映射表, 当key发生冲突的时候会抛出IllegalStateException
        Map<String, People> collect = peopleStream.collect(Collectors.toMap(People::getId, Function.identity()));
        // 将对象流存储到Map中, 为防止键冲突抛出异常, 提供第三个函数, 以旧值为准
        Map<String, String> collect1 = Stream.of(Locale.getAvailableLocales()).collect(
                Collectors.toMap(
                        Locale::getDisplayLanguage,
                        loc -> loc.getDisplayLanguage(loc),
                        (exist, newValue) -> exist
                )
        );
        System.out.println(collect1);

        // 将对象存储到Map中, value为Set类型, 国家名称映射多种语言
        Stream.of(Locale.getAvailableLocales()).collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l-> Collections.singleton(l.getDisplayLanguage()),
                        (a, b) -> {
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }
                )
        );

        // toMap默认使用的是HashMap, 如果要使用TreeMap, 则需要用第四个参数
        Stream<People> peopleStream1 = Stream.of(new People("1"), new People("2"));
        TreeMap<String, People> collect2 = peopleStream1.collect(Collectors.toMap(
                People::getId,
                Function.identity(),
                (exist, newValue) -> newValue,
                TreeMap::new
        ));

    }

    static class People {
        String id;
        String pwd;

        People(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }
}
