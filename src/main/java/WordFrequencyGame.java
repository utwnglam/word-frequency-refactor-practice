import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String SPACE_PATTERN = "\\s+";
    private static final String NEWLINE_PATTERN = "\n";
    private static final String ERROR_MESSAGE = "Calculate Error";

    public String getResult(String inputSentence) {
        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(inputSentence);
            wordInfoList.sort((wordFirst, wordNext) -> wordNext.getWordCount() - wordFirst.getWordCount());
            return generateFrequencyResult(wordInfoList);
        } // catch (NullPointerException) {}
        catch (Exception exception) {
            return ERROR_MESSAGE;
        }
    }

    private String generateFrequencyResult(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
          .map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
          .collect(Collectors.joining(NEWLINE_PATTERN));
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        return distinctWords.stream()
          .map(distinctWord -> new WordInfo(distinctWord, Collections.frequency(words, distinctWord)))
          .collect(Collectors.toList());
    }
}
