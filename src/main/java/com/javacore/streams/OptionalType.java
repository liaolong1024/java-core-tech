package com.javacore.streams;

import java.util.Optional;

/**
 * @author ll
 */

public class OptionalType {
    public static void main(String[] args) {
        Optional<String> optionalString = Optional.of("username");

        // 获取Optional的值, 如果为null, 返回指定的默认值
        String username1 = optionalString.orElse("anonymous");
        // 获取Optional的值, 如果为null, 返回Supplier接口提供的值
        String username2 = optionalString.orElseGet(() -> System.getProperty("username"));
        // 获取Optional的值, 如果为null, 抛出指定异常(RuntimeException)
        String username3 = optionalString.orElseThrow(IllegalStateException::new);

        // 在确保Optional值存在的情况下, 消费该值
        optionalString.ifPresent(System.out::println);
        // Optional值存在时执行方法1, 不存在时执行方法2, Java 9
        // optionalString.ifPresentOrElse(...);

        // 管道化Optional, 如果存在, 将原Optional转换为新的Optional, 如果不存在, 新Optional的值为空
        Optional<String> transformed = optionalString.map(String::toUpperCase);
        Optional<String> transformed2 = optionalString.filter(s -> s.length() > 2);
        // 管道化Optional, 如果存在, 保持Optional, 如果值不存在, 转换为新的Optional, Java 9
        // optionalString.or(...)

        // Optional使用原则
        // 1. Optional类型的变量永远不能为null
        // 2. 集合中要放置Optional对象, 也不要将其当做Map的key

        // 创建Optional, 创建一个空Optional
        Optional<Object> empty = Optional.empty();
        // 创建Optional, 指定一个值, 指定的值不能是null
        Optional<String> usernameOf = Optional.of("username");
        // 指定值为null时, 返回Optional.empty, 否则同Optional.of
        Optional<String> nullable = Optional.ofNullable(null);

        // flagMap, 处理Optional, lambda表达式应该为一个将string元素转换为Optional对象的方法, Map与之不同的是, Map没有限定转换后的类型
        Optional<String> s = optionalString.flatMap(Optional::of);


        // Optional转换为流  Java 8以上
        // Stream<String> ids = ...;
        // ids.map(...).flatMap(Optional::stream)

    }
}
