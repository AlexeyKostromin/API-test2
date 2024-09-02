package tests.posts;

import api.models.posts.Post;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.util.*;

@Slf4j
public class TopFrequentWordsTest extends BaseTest {
    @Test
    @DisplayName("Print top 10 frequent words from post`s bodies")
    public void getTopTenFrequentWordsFromBodyTest() {
        List<Post> allPosts = postUtilMethods.getAllPosts(200);

        List<String> bodies = new ArrayList<>();
        for (Post post : allPosts) {
            bodies.add(post.getBody());
        }

        String combinedText = String.join(" ", bodies);

        String[] words = combinedText.toLowerCase().split("\\W+");

        Map<String, Long> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordFrequency.merge(word, 1L, Long::sum);
            }
        }

        List<Map.Entry<String, Long>> entries = new ArrayList<>(wordFrequency.entrySet());
        entries.sort(Comparator.comparing(
                (Map.Entry<String, Long> entry) -> entry.getValue()).reversed());

        List<Map.Entry<String, Long>> topTenWords = entries.subList(0, Math.min(10, entries.size()));

        logTopWords(topTenWords);
    }

    private static void logTopWords(List<Map.Entry<String, Long>> topTenWords) {
        StringBuilder topWordsLog = new StringBuilder("The top ten frequent words are:\n");

        int sequence = 1;
        for (Map.Entry<String, Long> entry : topTenWords) {
            topWordsLog.append(String.format("%d. %s - %d%n", sequence++, entry.getKey(), entry.getValue()));
        }

        log.info(topWordsLog.toString());
        Allure.addAttachment("Top 10 Frequent Words", topWordsLog.toString());

    }
}
