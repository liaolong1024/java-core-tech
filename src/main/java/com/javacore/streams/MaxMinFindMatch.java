package com.javacore.streams;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author ll
 */
public class MaxMinFindMatch {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> words = CountLongWords.getWords();

        // 从流中获取最大值
        Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
        System.out.println("largest: " + largest.orElse(""));

        // 找到符合条件的第一个元素
        Optional<String> startsWithQ = words.stream().filter(w -> w.startsWith("a")).findFirst();

        // 找到符合条件的任一个元素
        Optional<String> startsWithQAny = words.parallelStream().filter(w -> w.startsWith("a")).findAny();

        // 是否存在符合条件的元素, 即只需要一个布尔值
        boolean anyMatch = words.parallelStream().anyMatch(w -> w.startsWith("a"));
    }
}
