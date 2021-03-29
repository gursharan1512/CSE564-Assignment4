import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            String inputCode = readTextFile(args[0]);
            Tokenizer tokenizer = new Tokenizer();
            tokenizer.getTokens(inputCode);
        }
        else {
            throw new FileNotFoundException("Please pass a file in argument");
        }
    }

    public static String readTextFile (String path) throws IOException {

        StringBuilder code = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str;
        while ( (str = br.readLine()) != null) code.append(str).append(" ");
        //return code.toString();

        return code.toString().replaceAll("\\s+", " ").trim();
    }
}
