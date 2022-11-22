package com.javacore.streams;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用stream进行统计符合条件的单词个数
 *
 * @author ll
 */
public class CountLongWords {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // URL resource = CountLongWords.class.getResource("words.txt");
        // System.out.println(resource);
        // String contents = new String(
        //         Files.readAllBytes(Paths.get(resource.toURI())),
        //         StandardCharsets.UTF_8
        // );
        // Collections.addAll(words, contents.split(","));

        List<String> words = getWords();
        long count = 0;
        for (String word : words) {
            if (word.length()>12) count++;
        }
        System.out.println(count);

        count = words.stream().filter(w->w.length()>12).count();
        System.out.println(count);

        count = words.parallelStream().filter(w->w.length()>12).count();
        System.out.println(count);
    }

    public static List<String> getWords() throws URISyntaxException, IOException {
        URL resource = CountLongWords.class.getResource("words.txt");
        System.out.println(resource);
        String contents = new String(
                Files.readAllBytes(Paths.get(resource.toURI())),
                StandardCharsets.UTF_8
        );
        List<String> words = new ArrayList<>(10);
        Collections.addAll(words, contents.split(","));
        return words;
    }
}
