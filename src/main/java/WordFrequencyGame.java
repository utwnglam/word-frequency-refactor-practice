import java.util.*;
import java.util.stream.Collectors;

// naming
// constant
// it-else
// test case
// add test case

public class WordFrequencyGame {
    private final static String SPACE_PATTERN = "\\s+";

    public String getResult(String inputSentence) {
        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(inputSentence);
            wordInfoList.sort((wordFirst, wordSecond) -> wordSecond.getWordCount() - wordFirst.getWordCount());
            return generateFrequencyResult(wordInfoList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private String generateFrequencyResult(List<WordInfo> wordInfoList) {
        StringJoiner finalOutput = new StringJoiner("\n");
        for (WordInfo wordInfo : wordInfoList) {
            String wordFrequencyCountString = wordInfo.getValue() + " " + wordInfo.getWordCount();
            finalOutput.add(wordFrequencyCountString);
        }
        return finalOutput.toString();
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfoList = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int frequency = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            WordInfo wordInfo = new WordInfo(distinctWord, frequency);
            wordInfoList.add(wordInfo);
        });
        return wordInfoList;
    }
}
