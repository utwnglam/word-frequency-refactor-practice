import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class WordFrequencyGameTest {

    @Test
    public void should_get_the_1_when_input_the() throws Exception {
        //Given
        String inputSentence = "the";
        String expectResult = "the 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_two_words() throws Exception {
        //Given
        String inputSentence = "the is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_spaces() throws Exception {
        //Given
        String inputSentence = "the      is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_enter() throws Exception {
        //Given
        String inputSentence = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_two_same_words_with_sorted() throws Exception {
        //Given
        String inputSentence = "the the is";
        String expectResult = "the 2\nis 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_sorted_with_count_descending() throws Exception {
        //Given
        String inputSentence = "the is is";
        String expectResult = "is 2\nthe 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_return_error_message_given_an_input_null() {
        String expectResult = "Calculate Error";
        validate_Input_words_process_to_expected_word(null, expectResult);
    }

    private void validate_Input_words_process_to_expected_word(String inputSentence, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String result = game.getResult(inputSentence);
        //Then
        assertThat(result).isEqualTo(expectResult);
    }
}
