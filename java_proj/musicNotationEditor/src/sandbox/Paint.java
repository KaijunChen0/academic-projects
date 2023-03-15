package sandbox;

import graphicsLib.G;
import graphicsLib.Window;

import java.awt.*; // import all
import java.awt.event.MouseEvent; // import sub directoris
import java.util.ArrayList;

public class Paint extends Window {
    public static int click = 0;
    public static Path thePath;
    public static Path.List paths = new Path.List();

    Paint() {super("Paint", 1000, 700);} // constructor has to use the name of the class

    @Override // check whether paintComponent is defined, overrun the definitions
    // g: draw a screen object
    public void paintComponent(Graphics g) {
        G.clearBack(g);
        g.setColor(G.rndColor()); // graphic object.function that's in Graphics (pass in the RED which is in the Color class)
        /*
        // g.fillRect(100,100,100,100); // x y set the location of the object
        // g.drawRect(100,100,100,100);
        // g.drawOval(100,100,100,200);
        g.setColor(G.rndColor());
        g.fillOval(100, 100, 100, 200);
        g.drawLine(100, 600, 600, 200);
        int x = 300, y = 300;
        String str = "Click: " + click; // conversion and append here
        g.setColor(Color.RED);
        g.drawString(str, x, y);
        g.fillRect(x,y,3,3);
        FontMetrics fm = g.getFontMetrics();
        int a = fm.getAscent();
        int h = fm.getHeight();
        int w = fm.stringWidth(str);
        g.drawRect(x,y-a, w,h);
        */
        paths.draw(g);
    }

    @Override
    public void mousePressed (MouseEvent me){
        // click++;
        click = 0;
        thePath = new Path();
        thePath.add(me.getPoint());
        paths.add(thePath);
        repaint();
    }

    @Override
    public void mouseDragged (MouseEvent me){
        click++;
        thePath.add(me.getPoint());
        repaint();
    }

    public static void main(String[] args) {
        PANEL = new Paint();
        launch();
    }

    // ---------------------nested class-----------PATH------------
    public static class Path extends ArrayList<Point> {
        // default constructor
        public void draw(Graphics g){
            for (int i = 1; i < size(); i++) {
                Point p = get(i-1), n = get(i); // previous and next points
                g.drawLine(p.x, p.y, n.x, n.y);
            }
        }
        // ---------------------list----------------------
        public static class List extends ArrayList<Path> {
            public void draw(Graphics g){for(Path p: this){p.draw(g);}}
        }
    }
}
