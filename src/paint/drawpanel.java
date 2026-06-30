package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class drawpanel extends JComponent implements MouseMotionListener, MouseListener {
    private infopanel Info;
    private BufferedImage backgroundImage = null;

    private Point pen[] = new Point[1000];
    private int cpen = 0;
    private float strokeSize = 1.0f;  // مقدار اولیه ضخامت قلم

    private line[] lines = new line[1000];

    private int cLineAll = 0;
    private int cLine = 0;
    private int tnum = 1;
    private int x1, y1;
    private int shpenum = shapetool.Line;

    private Color penColor = Color.BLACK;  // ذخیره رنگ قلم (پنسل)

    public drawpanel(infopanel Info) {
        super();
        this.Info = Info;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        setPreferredSize(new Dimension(main.gWidth - 170, main.gHeight - 20));
    }

    // متد برای تغییر رنگ قلم
    public void setPenColor(Color color) {
        this.penColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //ضخامت پاک کن
        g2.setStroke(new BasicStroke(strokeSize));

        
        // اگر تصویری لود شده، اون رو بکش
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
        }

        // رسم خطوط و اشکال دیگر
        for (int i = 0; i < cLine; i++) {
            g2.setColor(lines[i].color);
            if (lines[i].shapeTool == shapetool.Line) {
                g2.drawLine(lines[i].x1, lines[i].y1, lines[i].x2, lines[i].y2);
            }
            if (lines[i].shapeTool == shapetool.Rect) {
                g2.fillRect(lines[i].x1, lines[i].y1, lines[i].x2 - lines[i].x1, lines[i].y2 - lines[i].y1);
            }
            if (lines[i].shapeTool == shapetool.Oval) {
                g2.fillOval(lines[i].x1, lines[i].y1, lines[i].x2 - lines[i].x1, lines[i].y2 - lines[i].y1);
            }
        }

        // رسم مداد (پنسل)
        g.setColor(penColor);
        for (int i = 0; i < cpen - 1; i++) {
            if (pen[i] != null && pen[i + 1] != null) {
                g.drawLine(pen[i].x, pen[i].y, pen[i + 1].x, pen[i + 1].y);
            }
        }
    }


    public void setshapeTool(int tnum) {
        this.shpenum = tnum;
    }

    public void Clear() {
        cLine = 0;
        cpen = 0;
        repaint();
    }

    public void Clearback() {
        if (cLine > 0)
            cLine--;
        repaint();
    }

    public void undoClear() {
        cLine = cLineAll;
        repaint();
    }

    public void undoLast() {
        if (cLine < cLineAll)
            cLine++;
        repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        Info.setInfo("(" + e.getX() + "," + e.getY() + ")", 0);

        if (shpenum == shapetool.pencil) {
            if (cpen < pen.length) {
                pen[cpen++] = new Point(e.getX(), e.getY());
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Info.setInfo("(" + e.getX() + "," + e.getY() + ")", 0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Info.setInfo("(x1,y1):(" + e.getX() + "," + e.getY() + ")", 1);
        x1 = e.getX();
        y1 = e.getY();

        if (shpenum == shapetool.pencil) {
            if (cpen > 0 && pen[cpen - 1] != null) {
                pen[cpen++] = null; // جدا کردن مسیر جدید
            }
            pen[cpen++] = new Point(x1, y1);
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        Info.setInfo("(x2,y2):(" + e.getX() + "," + e.getY() + ")", 2);

        if (shpenum != shapetool.pencil) { // اگر ابزار مداد نباشد، یک خط معمولی کشیده می‌شود
            line a = new line();
            a.x1 = x1;
            a.y1 = y1;
            a.x2 = e.getX();
            a.y2 = e.getY();
            a.shapeTool = this.shpenum;
            a.color = penColor;
            lines[cLine++] = a;
            cLineAll++;
        }

        // جلوگیری از اتصال خطوط در کلیک‌های بعدی
        x1 = -1;
        y1 = -1;

        repaint();
    }
    public void setBackgroundImage(BufferedImage image) {
        this.backgroundImage = image;
        repaint();
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void setStrokeSize(int size) {
        this.strokeSize = size;
    }

   
}
