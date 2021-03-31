import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {

    JTextArea editor = new JTextArea();
    Lexer lexer = new Lexer();

    public Main(String path) throws IOException {
//        String inputCode = readTextFile(path);
//        Tokenizer tokenizer = new Tokenizer();


        UMLPlotter umlPlotter = new UMLPlotter();
        lexer.addObserver(umlPlotter);
        //ArrayList<String> tokenList = tokenizer.getTokens(inputCode);
//        System.out.println(tokenList);
        //lexer.parseToken(tokenList);

        createGui(umlPlotter);
    }

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            new Main(args[0]);
        } else {
            throw new FileNotFoundException("Please pass a file in argument");
        }
    }

//    public String readTextFile(String path) throws IOException {
//
//        StringBuilder code = new StringBuilder();
//        BufferedReader br = new BufferedReader(new FileReader(path));
//        String str;
//        while ((str = br.readLine()) != null) code.append(str).append(" ");
//
//        return code.toString().replaceAll("\\s+", " ").trim();
//    }

    private void createGui(UMLPlotter umlPlotter) {

        //UMLPlotter umlPlotter = new UMLPlotter();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        //setLayout(null);
        //setLayout(new GridLayout(4,0));
        editor.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setPreferredSize(new Dimension(250, 800));
        //scrollPane.setBounds(0, 0, 300, 800);
        scrollPane.setBackground(new Color(235, 235, 235));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));

        createMenuBar();

        scrollPane.setBorder(BorderFactory.createTitledBorder("Source Code"));
        getContentPane().add(scrollPane);
        getContentPane().add(umlPlotter);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        menuFile.add(exitMenuItem);

        JMenuItem runButton = new JMenuItem("Run");
        runButton.addActionListener(this);

        menuBar.add(menuFile);
        menuBar.add(runButton);
        setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String inputText = editor.getText();
        String buttonName = e.getActionCommand();

        if (buttonName.equals("Run")) {
            System.out.println("+++++++++++++++++");
            //System.out.println(inputText);
            Tokenizer tokenizer = new Tokenizer();

            StringBuilder code = new StringBuilder();

            for (String line : inputText.split("\n")) {
                code.append(line).append(" ");
            }
            
            String inputCode = code.toString().replaceAll("\\s+", " ").trim();

            ArrayList<String> tokenList = tokenizer.getTokens(inputCode);
            System.out.println(tokenList);
            lexer.parseToken(tokenList);
        }
        else if (buttonName.equals("Exit")) {
            System.exit(0);
        }
    }
}
