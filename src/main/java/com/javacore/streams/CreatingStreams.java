package com.javacore.streams;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 流的各种创建方式
 *
 * @author ll
 */
public class CreatingStreams {
    public static void main(String[] args) throws URISyntaxException, IOException {
        URL resource = CreatingStreams.class.getResource("words.txt");
        Path path = Paths.get(resource.toURI());
        String contents = new String(
                Files.readAllBytes(path),
                StandardCharsets.UTF_8
        );

        // 将数组转换为流
        Stream<String> words = Stream.of(contents.split(","));
        show("words", words);

        // 给定多个参数
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);

        // 空流
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        // 实现Supplier接口提供数据源, 数据无限
        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);
        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        // 第一个参数是seed, 第二个参数记为f, 该流产生的元素集合是
        //      {seed, 以seed为参数调用f产生第二个值, 以第二个值为参数调用f产生第三个值, 依此类推...}
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        // 以正则表达式为规则分割CharSequence对象
        Stream<String> wordsAnotherWay = Pattern.compile(",").splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay);

        // 读取文件, 以文件中每一行作为一个元素形成一个流
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            show("lines", lines);
        }

        // 将iterator转换为流
        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(), false);
        show("rootDirectories", rootDirectories);

        // 从根目录下的第一个目录开始到文件名(包含)所经过的目录组成的元素集合
        Iterator<Path> iterator = path.iterator();
        Stream<Path> pathComponents = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
        show("pathComponents", pathComponents);


    }

    public static <T> void show(String title, Stream<T> stream) {
        final int  SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i>0) System.out.print(", ");
            if (i<SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }

}
