import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

class DrawClick extends JComponent {
    Image click = new ImageIcon("src/иконка.png").getImage();
    int x, y;
    DrawClick (int x, int y) {
        this.x = x;
        this.y = y;
        setOpaque(false);
    }
    public void paint (Graphics g) {
        g.drawImage(click, 0, 0, 50, 50, null);
    }
}

public class Main extends JFrame implements MouseListener, MouseMotionListener {
    int x = 0;
    int y = 0;
    JLayeredPane lp;
    ArrayList <DrawClick> drawClicks = new ArrayList<>();

    public Main () {
        super ("Кликер");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,200);
        setSize(600,500);
        lp = getLayeredPane();
        addMouseListener(this);
        addMouseMotionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    public Integer checkDraw (int a, int b) {
        for (int i = 0; i < drawClicks.size(); i++) {
            DrawClick drawClick = drawClicks.get(i);
            if (a>=drawClick.x&&(a<=drawClick.x+50)&&b>=drawClick.y&&(b<=drawClick.y+50))
                return i;
        }
        return null;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton()==1) {  //левая кнопка мыши
            x = mouseEvent.getX() - 25;
            y = mouseEvent.getY() - 50;
            DrawClick d = new DrawClick(x,y);
            drawClicks.add(d);
            d.setBounds(x, y, 50, 50);
            lp.add(d, JLayeredPane.PALETTE_LAYER);
        }
        if (mouseEvent.getButton()==3) {
            x = mouseEvent.getX();
            y = mouseEvent.getY() - 25;
            Integer c = checkDraw (x,y);
            if (c != null) {
                lp.remove(drawClicks.get(c));
                drawClicks.remove((int)c);
                repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        lp.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }








    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }



    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
