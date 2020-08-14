package geekbrains.hw_1_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleField extends JPanel {
    private geekbrains.hw_1_8.GameWindow gameWindow;
    private int mode;
    private int fieldSize;
    private int winningLength;

    private boolean isInit;

    private int cellWidth;
    private int cellHeight;

    public BattleField(geekbrains.hw_1_8.GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        setBackground(Color.LIGHT_GRAY);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBattleField(e);
            }
        });
    }

    private void clickBattleField(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        repaint();

        if(!geekbrains.hw_1_8.Logic.isFinished){
            geekbrains.hw_1_8.Logic.humanTurn(cellX, cellY);
        }

    }

    public void startNewGame(int mode, int fieldSize, int winningLength) {
        this.mode = mode;
        this.fieldSize = fieldSize;
        this.winningLength = winningLength;

        isInit = true;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isInit) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellHeight = panelHeight / fieldSize;
        cellWidth = panelWidth / fieldSize;

        g.setColor(Color.BLACK);

        for (int i = 0; i < fieldSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (geekbrains.hw_1_8.Logic.map[i][j] == geekbrains.hw_1_8.Logic.DOT_X) {
                    drawX(g, j, i);
                }
                if (geekbrains.hw_1_8.Logic.map[i][j] == geekbrains.hw_1_8.Logic.DOT_O) {
                    drawO(g, j, i);
                }
            }
        }
    }

    private void drawX(Graphics g, int x, int y) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.DARK_GRAY);
        g.drawLine(cellWidth * x + 10, cellHeight * y + 10, cellWidth * (x + 1) - 10, cellHeight * (y + 1) - 10);
        g.drawLine(cellWidth * x + 10, cellHeight * (y + 1) - 10, cellWidth * (x + 1) - 10, cellHeight * y + 10);
    }

    private void drawO(Graphics g, int x, int y) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.BLUE);
        g.drawOval(cellWidth * x + 10, cellHeight * y + 10, cellWidth - 20, cellHeight - 20);
    }
}
