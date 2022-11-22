package com.javacore.streams;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * 通过map, filter, flatmap等方法进行处理转换
 *
 * @author ll
 */
public class MapFlatMap {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path path = Paths.get(MapFlatMap.class.getResource("words.txt").toURI());
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> words = new ArrayList<>(10);
        Collections.addAll(words, contents.split(","));

        // 使用map方法通过Function处理后返回的元素组成新的流
        Stream<String> lowerCaseWords = words.stream().map(String::toLowerCase);

        // 使用map处理后返回一个由多个流元素组成的流
        Stream<Stream<String>> streamStream = words.stream().map(MapFlatMap::codePoints);
        // 使用flagMap, 由[元素 -> 流]映射产生的多个流, 再将这些流摊平->一个流
        Stream<String> flatResult = words.stream().flatMap(MapFlatMap::codePoints);
        System.out.println(streamStream.count());
        System.out.println(flatResult.count());
    }

    /**
     * 正确处理需要用两个char来表示的Unicode字符
     */
    public static Stream<String> codePoints(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = s.offsetByCodePoints(i, 1);
            result.add(s.substring(i, j));
            i = j;
        }
        return result.stream();
    }
}
