import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    private JFrame frame;
    private JButton[][] buttons;
    private char currentPlayer = 'X';
    private boolean gameWon = false;

    public TicTacToeGUI() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].addActionListener(new CellClickListener(row, col));
                boardPanel.add(buttons[row][col]);
            }
        }
        
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private class CellClickListener implements ActionListener {
        private int row;
        private int col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gameWon && buttons[row][col].getText().equals("")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                buttons[row][col].setEnabled(false);

                if (checkWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                    gameWon = true;
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(frame, "It's a tie!");
                    gameWon = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(player)) &&
                buttons[i][1].getText().equals(String.valueOf(player)) &&
                buttons[i][2].getText().equals(String.valueOf(player))) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(String.valueOf(player)) &&
                buttons[1][i].getText().equals(String.valueOf(player)) &&
                buttons[2][i].getText().equals(String.valueOf(player))) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][2].getText().equals(String.valueOf(player))) {
            return true;
        }
        
        if (buttons[0][2].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][0].getText().equals(String.valueOf(player))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
