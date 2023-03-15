package sandbox;

import global.I;
import graphicsLib.G;
import graphicsLib.Spline;
import graphicsLib.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static global.UC.MAIN_WINDOW_HEIGHT;
import static global.UC.MAIN_WINDOW_WIDTH;

public class Squares extends Window implements ActionListener {
    // implements ActionListener: ----
    // public static G.VS theVS = new G.VS(100, 100, 200, 300);
    // public static Color theColor = G.rndColor();
    public static Square.List theList = new Square.List();
    public static Square theSquare;
    public static boolean dragging = false;
    public static G.V mouseDelta = new G.V(0, 0); // google: how dragging is implemented
    public static Timer timer;
    public static G.V pressedLoc = new G.V(0, 0);
    public static final int WIDTH = MAIN_WINDOW_WIDTH, HEIGHT = MAIN_WINDOW_HEIGHT;
    public static I.Area curArea;
    public static Square BACKGROUND = new Square(0, 0) {
        // Anonymous Classes in Java: Do something different than the normal Square
        public void dn(int x, int y) {theSquare = new Square(x, y); theList.add(theSquare);}

        public void drag(int x, int y) {theSquare.resize(x, y);}
    };

    // static {} is the static block
    static {BACKGROUND.c = Color.WHITE; BACKGROUND.size.set(5000, 5000); theList.add(BACKGROUND);}

    ;

    public Squares() {
        // public static final int WIDTH = 1000, HEIGHT = 700;

        super("squares", WIDTH, HEIGHT); // ~new
        timer = new Timer(30, this); // action listener is the window; 30 m-second is just rule of thumb
        timer.setInitialDelay(5000); // 5000 mil second = 5 second
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        G.clearBack(g);
        theList.draw(g);
        // call Spline when the window has three boxes; >3 as the backgorund is counted as 0
        if (theList.size() > 3) {
            Spline.pSpline(g, theList.get(1).loc, theList.get(2).loc, theList.get(3).loc, 5);
        }
    }

    public void mousePressed(MouseEvent me) {
        // if (theVS.hit(me.getX(), me.getY())) {theColor = G.rndColor();}
        int x = me.getX(), y = me.getY();
        curArea = theList.hit(x, y);
        curArea.dn(x, y);
        repaint();
    }

    public void mouseDragged(MouseEvent me) {
        int x = me.getX(), y = me.getY();
        curArea.drag(x, y);
        repaint();
    }

    public void mouseReleased(MouseEvent me) {
        if (dragging) {
            theSquare.dv.set(me.getX() - pressedLoc.x, me.getY() - pressedLoc.y);
        }
    }

    public static void main(String[] args) {
        (PANEL = new Squares()).launch();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    // ---------------------------------Square---------------------------------
    public static class Square extends G.VS implements I.Draw, I.Area {
        public Color c = G.rndColor();

        public G.V dv = new G.V(0, 0);
        // public G.V dv = new G.V(G.rnd(20) - 10, G.rnd(20) - 10); // generate rnd between -10 and 10

        public Square(int x, int y) {super(x, y, 100, 100);}

        public void draw(Graphics g) {
            fill(g, c);
            moveAndBounce();
        }

        public void resize(int x, int y) {
            if (x > loc.x && y > loc.y) {size.set(x - loc.x, y - loc.y);}
        }

        public void move(int x, int y) {loc.set(x, y);}

        public void moveAndBounce() {
            loc.add(dv);
            if (xL() < 0 && dv.x < 0) {dv.x = -dv.x;}
            if (xH() > WIDTH && dv.x > 0) {dv.x = -dv.x;}
            if (yL() < 0 && dv.y < 0) {dv.y = -dv.y;}
            if (yH() > HEIGHT && dv.y > 0) {dv.y = -dv.y;}
        }

        @Override // the down object sets up the mouse delta element
        public void dn(int x, int y) {mouseDelta.set(loc.x - x, loc.y - y);}

        @Override  // we don't do anything when it's going up
        public void up(int x, int y) {}

        @Override // set the location of the object
        public void drag(int x, int y) {loc.set(mouseDelta.x + x, mouseDelta.y + y);}

        // --------------------------List--------------------------------------
        public static class List extends ArrayList<Square> {
            public void draw(Graphics g) {for (Square s : this) {s.draw(g);}}

            public Square hit(int x, int y) {
                Square res = null; // res == result
                for (Square s : this) {if (s.hit(x, y)) {res = s;}}
                return res;
            }


        }
    }
}
