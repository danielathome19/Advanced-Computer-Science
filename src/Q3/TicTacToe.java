package Q3;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TicTacToe {
    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JLabel statusLabel;

    private char currentPlayer = 'X';
    private JButton[][] buttons;

    public TicTacToe() {
        buttons = new JButton[][]{
                {button1, button2, button3},
                {button4, button5, button6},
                {button7, button8, button9}
        };

        ActionListener buttonClickListener = e -> {
            JButton clickedButton = (JButton) e.getSource();

            // If the button is empty, allow a move
            if (clickedButton.getText().isEmpty()) {
                clickedButton.setText(String.valueOf(currentPlayer)); // Set the button text to current player

                // Check for win or draw
                if (checkWin()) {
                    statusLabel.setText("Player " + currentPlayer + " wins!");
                    disableAllButtons();
                } else if (isBoardFull()) {
                    statusLabel.setText("It's a draw!");
                } else {
                    // Switch player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    statusLabel.setText("Player " + currentPlayer + "'s turn");
                }
            }
        };

        for (JButton[] row : buttons)
            for (JButton button : row)
                button.addActionListener(buttonClickListener);
    }

    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        // Check diagonals
        return (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) ||
                (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[2][0].getText().equals(String.valueOf(currentPlayer)));
    }

    private boolean isBoardFull() {
        for (JButton[] row : buttons)
            for (JButton button : row)
                if (button.getText().isEmpty())
                    return false;
        return true;
    }

    private void disableAllButtons() {
        for (JButton[] row : buttons)
            for (JButton button : row)
                button.setEnabled(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setContentPane(new TicTacToe().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
