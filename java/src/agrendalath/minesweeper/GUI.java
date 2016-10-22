package agrendalath.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class GUI {
    private Board board;
    private JFrame frame = new JFrame("Minesweeper");
    private JButton[][] buttons;
    private static final ImageIcon FLAG_ICON = new ImageIcon("resources/flag.png");
    private static final ImageIcon BOMB_ICON = new ImageIcon("resources/bomb.png");

    GUI(int width, int height, int bombs) {
        this.board = new Board(width, height, bombs);
        setUpGUI();
    }

    private void setUpGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        char[][] fields = board.getBoard();

        GridLayout grid = new GridLayout(fields.length, fields[0].length);
        JPanel panel = new JPanel(grid) {
            @Override
            public void paintComponent(Graphics graphics) {
                char[][] fields = board.getBoard();

                for (int i = 0; i < fields.length; ++i)
                    for (int j = 0; j < fields[0].length; ++j) {
                        String text = "";
                        Color color = Color.GREEN;
                        ImageIcon icon = null;

                        switch (fields[i][j]) {
                            case '#':
                                color = null;
                                break;
                            case 'B':
                                icon = BOMB_ICON;
                                color = Color.RED;
                                break;
                            case 'F':
                                color = null;
                                icon = FLAG_ICON;
                                break;
                            case '0':
                                // We don't want any text in such cell.
                                break;
                            default:
                                text = Character.toString(fields[i][j]);
                        }
                        buttons[i][j].setIcon(icon == null ? null :
                                new ImageIcon(icon.getImage().getScaledInstance(
                                        buttons[i][j].getWidth(),
                                        buttons[i][j].getHeight(),
                                        Image.SCALE_SMOOTH)
                                ));
                        buttons[i][j].setText(text);
                        buttons[i][j].setBackground(color);
                    }
            }
        };

        frame.setContentPane(panel);
        buttons = new JButton[fields.length][fields[0].length];

        for (int i = 0; i < fields.length; ++i) {
            for (int j = 0; j < fields[0].length; ++j) {
                final int x = i;
                final int y = j;
                buttons[i][j] = new JButton();
                buttons[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if (SwingUtilities.isLeftMouseButton(mouseEvent) && board.reveal(new Point(x, y))) {
                            panel.repaint();
                        } else if (SwingUtilities.isRightMouseButton(mouseEvent) && board.flag(new Point(x, y)))
                            panel.repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {
                        // Method forced by MouseListener interface.
                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {
                        // Method forced by MouseListener interface.
                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                        // Method forced by MouseListener interface.
                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                        // Method forced by MouseListener interface.
                    }
                });

                panel.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }
}
