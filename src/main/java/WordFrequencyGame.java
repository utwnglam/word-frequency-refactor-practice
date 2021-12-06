import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String SPACE_PATTERN = "\\s+";
    private static final String NEWLINE_PATTERN = "\n";
    private static final String ERROR_MESSAGE = "Calculate Error";

    public String getResult(String inputSentence) {
        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(inputSentence);
            wordInfoList.sort((wordFirst, wordSecond) -> wordSecond.getWordCount() - wordFirst.getWordCount());
            return generateFrequencyResult(wordInfoList);
        } catch (Exception e) {
            return ERROR_MESSAGE;
        }
    }

    private String generateFrequencyResult(List<WordInfo> wordInfoList) {
        StringJoiner finalOutput = new StringJoiner(NEWLINE_PATTERN);
        for (WordInfo wordInfo : wordInfoList) {
            finalOutput.add(String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount()));
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
