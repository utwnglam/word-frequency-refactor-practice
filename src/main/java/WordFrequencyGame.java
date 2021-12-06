import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private final static String SPACE_PATTERN = "\\s+";

    public String getResult(String inputStr){
        if (inputStr.split(SPACE_PATTERN).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = calculateWordFrequencyTemp(inputStr);

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

    private List<WordInfo> calculateWordFrequency(String inputStr) {
        //split the input string with 1 to n pieces of spaces
        String[] words = inputStr.split(SPACE_PATTERN);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordInfo>> map = getListMap(wordInfoList);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        wordInfoList = list;
        return wordInfoList;
    }

    private List<WordInfo> calculateWordFrequencyTemp(String sentence) {
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

    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }

            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }


        return map;
    }
}
