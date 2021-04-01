import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    static final List<Character> OPERATOR_DELIMITER_LIST = Arrays.asList('=', ',', '(', ')', '{', '}', ';', '+', '-', '*', '/', '%', '>', '<', '[', ']');
    boolean isDoubleOperator = false;
    private ArrayList<String> lineTokens = new ArrayList<>();
    private boolean isString = false;

    public ArrayList<String> getTokens(String inputCode) {


        int charPosition = 0;
        char[] inputChar = inputCode.toCharArray();
        for (int i = 0; i < inputChar.length; i++) {
            if (inputChar[i] == '"') {
                isString = !isString;
            }
            if (!isString) {
                charPosition = identifyToken(inputCode, charPosition, i);
                if (isDoubleOperator) {
                    isDoubleOperator = false;
                    i++;
                    charPosition++;
                }
            }
        }

        if (isString) {
            lineTokens.add(inputCode.substring(charPosition));
        }

        return lineTokens;
    }

    /**
     * Separates the given code into tokens which dio not have spaces using operators and handles multiple characters operators such as '>='
     *
     * @param inputText    - Input code block.
     * @param charPosition - pointer position where given token started.
     * @param i            - counter for the input string.
     * @return - new position of the counter where tokens needs to be identified further.
     */
    private int identifyToken(String inputText, int charPosition, int i) {
        char[] inputChar = inputText.toCharArray();
        if (OPERATOR_DELIMITER_LIST.contains(inputChar[i]) && !inputText.substring(charPosition, i).equals(" ")) {
            if (!inputText.substring(charPosition, i).equals("")) {
                lineTokens.add(inputText.substring(charPosition, i));
            }
            if ((inputChar[i] == '>' || inputChar[i] == '<' || inputChar[i] == '=') && (i + 1 < inputChar.length)) {
                if (inputChar[i + 1] == '=') {
                    isDoubleOperator = true;
                    lineTokens.add(inputText.substring(i, i + 2));
                }
            }
            if (inputText.charAt(i) != '\n' && !isDoubleOperator) {
                lineTokens.add(inputText.substring(i, i + 1));
            }
            charPosition = i + 1;
        } else if (inputChar[i] == ' ') {
            if (!inputText.substring(charPosition, i).equals("") && !inputText.substring(charPosition, i).equals("\t")) {
                lineTokens.add(inputText.substring(charPosition, i));
            }
            charPosition = i + 1;
        }
        return charPosition;
    }
}
