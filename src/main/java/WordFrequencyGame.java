import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private final static String SPACE_PATTERN = "\\s+";

    public String getResult(String inputStr){
        if (inputStr.split(SPACE_PATTERN).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = calculateWordFrequency(inputStr);

                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner finalOutput = new StringJoiner("\n");
                for (WordInfo wordInfo : wordInfoList) {
                    String wordFrequencyCountString = wordInfo.getValue() + " " + wordInfo.getWordCount();
                    finalOutput.add(wordFrequencyCountString);
                }
                return finalOutput.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
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
