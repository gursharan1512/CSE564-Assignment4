import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main extends JFrame {

    public Main(String path) throws IOException {
        String inputCode = readTextFile(path);
        Tokenizer tokenizer = new Tokenizer();

        Lexer lexer = new Lexer();
        UMLPlotter umlPlotter = new UMLPlotter();
        lexer.addObserver(umlPlotter);
        lexer.parseToken(tokenizer.getTokens(inputCode));
        //umlPlotter.printDetails();

        createGui(umlPlotter);
    }

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            new Main(args[0]);
        } else {
            throw new FileNotFoundException("Please pass a file in argument");
        }
    }

    public String readTextFile(String path) throws IOException {

        StringBuilder code = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str;
        while ((str = br.readLine()) != null) code.append(str).append(" ");

        return code.toString().replaceAll("\\s+", " ").trim();
    }

    private void createGui(UMLPlotter umlPlotter) {

        //UMLPlotter umlPlotter = new UMLPlotter();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        //setLayout(new GridLayout(4,0));
        getContentPane().add(umlPlotter);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
