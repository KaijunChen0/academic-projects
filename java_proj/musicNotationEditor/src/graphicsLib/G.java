package graphicsLib;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class G {

    public static final Random RANDOM = new Random();

    public static int rnd(int max) {return RANDOM.nextInt(max);} // single line function, a wrapper

    public static Color rndColor() {return new Color(rnd(256), rnd(256), rnd(256));}

    public static void clearBack(Graphics g) {
        // clear background by fill the full screen to white for windows, mac will automatically do it for users
        g.setColor(Color.white);
        g.fillRect(0, 0, 5000, 5000);
    }

    // add a nested class: similar to the Point class, decided by (x, y)
    // ----------------------------------V---------------------------------------
    public static class V implements Serializable {
        public static Transform T = new Transform();
        public int x, y; // this keyword refers to this object unless otherwise specified

        public V(int x, int y) {set(x, y);}

        public void set(int x, int y) {this.x = x; this.y = y;} // this keyword to deal with name collision

        public void set(V v) {this.x = v.x; this.y = v.y;}

        public void add(V v) {x += v.x; y += v.y;}

        // blending individual item, k is the blending #
        public void blend(V v, int k) {set((k * x + v.x) / (k + 1), (k * y + v.y) / (k + 1));}

        // ------------------------Method dealing with Transform------------------
        public void setT(V v) {set(v.tx(), v.ty());}

        public int tx() {return (x * T.n / T.d + T.dx);}

        public int ty() {return (y * T.n / T.d + T.dy);}
    }

    // linear transform: transform a point by multiplying n/d
    // ---------------------------Transform----------------------------------
    public static class Transform {

        // n for numerator, d for denominator
        int n, d, dx, dy;

        // VS: a box; oVS: old box; nVS: new box
        public void set(VS oVS, VS nVS) {
            setScale(oVS.size.x, oVS.size.y, nVS.size.x, nVS.size.y);
            dx = setOff(oVS.loc.x, oVS.size.x, nVS.loc.x, nVS.size.x); // set offset
            dy = setOff(oVS.loc.y, oVS.size.y, nVS.loc.y, nVS.size.y); // set offset
        }

        public void set(BBox bBox, VS nVS) {
            setScale(bBox.h.size(), bBox.v.size(), nVS.size.x, nVS.size.y);
            dx = setOff(bBox.h.lo, bBox.h.size(), nVS.loc.x, nVS.size.x); // set offset
            dy = setOff(bBox.v.lo, bBox.v.size(), nVS.loc.y, nVS.size.y); // set offset
        }

        // figure out the right value of n, d
        // taking old width, old height, new width and new height
        public void setScale(int oW, int oH, int nW, int nH) {
            n = (nW > nH) ? nW : nH;
            d = (oW > oH) ? oW : oH;
        }

        // oX: where the old box is located; likewise for nX
        public int setOff(int oX, int oW, int nX, int nW) {return (-oX - oW / 2) * n / d + nX + nW / 2;}
    }


    // add a nested class: vector + size (for a rectangle): that is a box
    // ----------------------------------VS---------------------------------------
    public static class VS implements Serializable {
        public V loc, size; // location, size

        public VS(int x, int y, int w, int h) {loc = new V(x, y); size = new V(w, h);}

        public void fill(Graphics g, Color c) {g.setColor(c); g.fillRect(loc.x, loc.y, size.x, size.y);}

        // hit detection: check if it's clicked on the rectangle
        public boolean hit(int x, int y) {
            return loc.x <= x && loc.y <= y && x <= (loc.x + size.x) && y <= (loc.y + size.y);
        }

        public int xL() {return loc.x;}

        public int xH() {return loc.x + size.x;}

        public int xM() {return loc.x + size.x / 2;}

        public int yL() {return loc.y;}

        public int yH() {return loc.y + size.y;}

        public int yM() {return loc.y + size.y / 2;}
    }

    // add a nested class: low, high (tracking the bound of points)
    // ----------------------------------LoHi---------------------------------------
    public static class LoHi implements Serializable {
        public int lo, hi;

        public LoHi(int min, int max) {lo = min; hi = max;}

        // if v not in the range, reset
        public void add(int v) {
            if (v < lo) lo = v;
            if (v > hi) hi = v;
        }

        // clear the old range and set a new one
        public void set(int v) {lo = v; hi = v;}

        public int size() {return (hi - lo) > 0 ? hi - lo : 1;}
    }

    // add a nested class: bounding box: horizontal and vertical bound
    // ----------------------------------BBox---------------------------------------
    public static class BBox implements Serializable {
        public LoHi h, v;

        public BBox() {h = new LoHi(0, 0); v = new LoHi(0, 0);}

        public void set(int x, int y) {h.set(x); v.set(y);}

        public void add(int x, int y) {h.add(x); v.add(y);}

        public void add(V v) {h.add(v.x); this.v.add(v.y);} // add a single Vector E

        public VS getNewVS() {return new VS(h.lo, v.lo, h.size(), v.size());}

        public void draw(Graphics g) {g.drawRect(h.lo, v.lo, h.size(), v.size());}
    }


    // add a nested class: polyline
    // ----------------------------------PL---------------------------------------
    public static class PL implements Serializable {
        public V[] points;

        public PL(int n) { // # of points reserved for PL
            points = new V[n];
            for (int i = 0; i < n; i++) points[i] = new V(0, 0);
        }

        public int size() {
            return points.length;
        }

        public void transform() {
            for (int i = 0; i < points.length; i++) points[i].setT(points[i]);
        }

        public void drawN(Graphics g, int n) {
            for (int i = 1; i < n; i++) { // we started at 1 as we need to draw line with at least 2 points
                g.drawLine(points[i - 1].x, points[i - 1].y, points[i].x, points[i].y);
            }
        }

        public void draw(Graphics g) {drawN(g, size());}

        public void drawNDots(Graphics g, int n) {
            for (int i = 0; i < n; i++) { // we started at 1 as we need to draw line with at least 2 points
                g.drawOval(points[i].x - 1, points[i].y - 1, 3, 3);
            }
        }

        public void drawDots(Graphics g) {drawNDots(g, size());}
    }
}
