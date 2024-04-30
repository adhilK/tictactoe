import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {

    private JButton[][] buttons;
    private boolean player1Turn;
    private int count;

    public TicTacToe() {
        super("Tic Tac Toe");
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("-");
                button.addActionListener(new ButtonListener());
                panel.add(button);
                buttons[i][j] = button;
            }
        }
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetListener());
        buttonPanel.add(resetButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitListener());
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        player1Turn = true;
        count = 0;

        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("-")) {
                if (player1Turn) {
                    button.setText("X");
                } else {
                    button.setText("O");
                }
                count++;
                player1Turn =!player1Turn;
                checkWin();
            }
        }
    }

    private class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("-");
                }
            }
            player1Turn = true;
            count = 0;
        }
    }

    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private void checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][1].getText().equals(buttons[i][2].getText()) &&!buttons[i][0].getText().equals("-")) {
                JOptionPane.showMessageDialog(this, "Player " + (player1Turn? 2 : 1) + " wins!");
                resetGame();
                return;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[1][i].getText().equals(buttons[2][i].getText()) &&!buttons[0][i].getText().equals("-")) {
                JOptionPane.showMessageDialog(this, "Player " + (player1Turn? 2 : 1) + " wins!");
                resetGame();
                return;
            }
        }
        // Check diagonals
        if ((buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) &&!buttons[0][0].getText().equals("-")) ||
                (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) &&!buttons[0][2].getText().equals("-"))) {
            JOptionPane.showMessageDialog(this, "Player " + (player1Turn? 2 : 1) + " wins!");
            resetGame();
            return;
        }
        // Check for tie
        if (count == 9) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            resetGame();
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
            }
        }
        player1Turn = true;
        count = 0;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
