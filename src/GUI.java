import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Provides methods to implement graphical user interface for accepting code block and displays final token information.
 *
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */

public class GUI extends JFrame implements ActionListener {

    private JTextArea editor;
    private Lexer lexer = new Lexer();

    public GUI() throws HeadlessException {
        createGui();
    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }

    /**
     * creates a GUI base JFrame window to host different JPanels.
     */
    private void createGui() {
        UMLPlotter umlPlotter = new UMLPlotter();
        lexer.addObserver(umlPlotter);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        editor = new JTextArea();
        editor.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setPreferredSize(new Dimension(250, 800));
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

    /**
     * This method creates the menu bar which provides option to Run the code.
     */
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

    /**
     * Implements method to pass the input data to tokenizer after clicking Run.
     * @param e - Action Event in this case clicking of run button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String inputText = editor.getText();
        String buttonName = e.getActionCommand();

        if (buttonName.equals("Run")) {
            Tokenizer tokenizer = new Tokenizer();
            StringBuilder code = new StringBuilder();
            for (String line : inputText.split("\n")) {
                code.append(line).append(" ");
            }
            String inputCode = code.toString().replaceAll("\\s+", " ").trim();
            ArrayList<String> tokenList = tokenizer.getTokens(inputCode);

            try {
                lexer.parseToken(tokenList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (buttonName.equals("Exit")) {
            System.exit(0);
        }
    }
}
