package sudokugame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SudokuFrame extends JFrame implements KeyListener {

    public static SudokuPanel sudokuPanel = new SudokuPanel();
    public static JTextField[][] textFields = new JTextField[9][9];
    public static JTextField[][] copyTextFields = new JTextField[9][9];

    public SudokuFrame() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku");
        this.setMinimumSize(new Dimension(800, 600));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opcje");

        JMenuItem info = new JMenuItem("Informacje o autorze");
        JMenuItem rules = new JMenuItem("Zasady gry");
        JMenuItem newGame = new JMenuItem("Nowa Gra");

        menu.add(newGame);
        menu.add(info);
        menu.add(rules);
        menuBar.add(menu);

        this.setJMenuBar(menuBar);

        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new FlowLayout());
        windowPanel.setPreferredSize(new Dimension(800, 600));

        sudokuPanel.setLayout(new GridLayout(9, 9, 3, 3));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = new JTextField();
                textFields[i][j].setFont(new Font("Lucida Console", Font.BOLD, 28));
                textFields[i][j].setForeground(Color.black);
                textFields[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                textFields[i][j].setEditable(false);
                textFields[i][j].addKeyListener(this);
                sudokuPanel.add(textFields[i][j]);
            }

        }

        windowPanel.add(sudokuPanel);
        sudokuPanel.setVisible(true);
        this.add(windowPanel);

        newGame.addActionListener(e -> {
            SudokuGenerator sudokuGenerator = new SudokuGenerator();
            sudokuGenerator.generator(textFields, 5, 4);

        });

        rules.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, " Kwadratowa plansza jest podzielona na dziewięć" +
                    "\n identycznych kwadratów 3 x 3 - w każdym z nich" +
                    "\n znajduje się dziewięć komórek. Twoim zadaniem" +
                    "\n jest  wypełnienie  wszystkich  komórek  planszy" +
                    "\n cyframi  od 1  do 9. W  każdym wierszu i każdej" +
                    "\n kolumnie  dana cyfra może  występować jedynie" +
                    "\n raz.");
        });

        info.addActionListener(e -> {
            ImageIcon icon = new ImageIcon("red.png");
            JOptionPane.showMessageDialog(this,
                    "Sudoku Swing Game v 1.01; Develop by:" +
                            "\n ChikenRed-GajdekDevelopment; Warszawa" +
                            "\n nasze motto: 'Nie mniej błędów niż w CyberPunku'",
                    "Informacja o deweloperze", 1, icon);
        });


    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuFrame frame = new SudokuFrame();
            frame.setVisible(true);
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        SudokuValidator sudokuValidator = new SudokuValidator();
        int v = 0;
        int v1 = 0;
        JTextField tf = (JTextField) e.getSource();
        tf.setForeground(Color.BLACK);
        String text = tf.getText();
        if ((!text.equals("1")) && (!text.equals("2")) && (!text.equals("3")) && (!text.equals("4")) &&
                (!text.equals("5")) && (!text.equals("6")) && (!text.equals("7")) && (!text.equals("8")) &&
                (!text.equals("9"))) {
            tf.setText("");
            JOptionPane.showMessageDialog(this, "Proszę wprowadzać cyfry od 1 do 9!!!");
        }

        for (int i = 0; i < textFields.length; i++)
            for (int j = 0; j < textFields[i].length; j++) {
                if (textFields[i][j] == tf || textFields[i][j].equals(tf)) {
                    try {
                        sudokuValidator.validSudokuFields(textFields, copyTextFields, i, j, i, j);
                        v = Integer.parseInt(textFields[i][j].getText());
                        v1 = Integer.parseInt(copyTextFields[i][j].getText());
                    } catch (NumberFormatException nfe) {
                    }
                    if (v != v1) textFields[i][j].setForeground(Color.red);

                    break;
                }
            }
        if (sudokuValidator.checkEquals(textFields, copyTextFields)) {
            JOptionPane.showMessageDialog(this, "GRATULACJE!!! " +
                    "UDAŁO CI SIĘ UZUPEŁNIĆ PLANSZĘ POPRAWNIE");
        }
    }
}


