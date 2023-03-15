package music;

import global.UC;
import reactions.Gesture;
import reactions.Mass;
import reactions.Reaction;

import java.awt.*;

import static music.MusicEd.PAGE;

public class Bar extends Mass {
    private static final int FAT = 2, RIGHT = 4, LEFT = 8;
    public Sys sys; // keep track of which system it is in
    public int x, barType = 0;

    public Bar(Sys sys, int x) {
        super("BACK");
        this.sys = sys;
        this.x = x;
        if (Math.abs(PAGE.margin.right - x) < UC.barToMarginSnap) {this.x = PAGE.margin.right;} // fix border case

        addReaction(new Reaction("S-S") { // cycle the barType
            @Override
            public int bid(Gesture g) {
                int x = g.vs.xM();
                if (Math.abs(x - Bar.this.x) > UC.barToMarginSnap) {return UC.noBid;}
                int y1 = g.vs.yL(), y2 = g.vs.yH();
                if (y1 < Bar.this.sys.yTop() - 20 || y2 > Bar.this.sys.yBot() + 20) {return UC.noBid;}
                return Math.abs(x - Bar.this.x);
            }

            @Override
            public void act(Gesture g) {
                Bar.this.cycleType();
            }
        });

        addReaction(new Reaction("DOT") { // Dot on bar lines
            @Override
            public int bid(Gesture g) {
                int x = g.vs.xM(), y = g.vs.yM();
                if (y < Bar.this.sys.yTop() || y > Bar.this.sys.yBot()) {return UC.noBid;}
                int dist = Math.abs(x - Bar.this.x);
                if (dist > 3 * PAGE.sysFmt.maxH) {return UC.noBid;} // distance too big
                return dist;
            }

            @Override
            public void act(Gesture g) {
                if (g.vs.xM() < Bar.this.x) {
                    Bar.this.toggleLeft();
                } else {Bar.this.toggleRight();}
            }
        });

    }

    public void cycleType() {barType++; if (barType > 2) {barType = 0;}}

    // toggle: switch between two stages, returning 0 or 1, 1: toggle the things
    public void toggleLeft() {barType = barType ^ LEFT;} // ^ being exclusive or (XOR)

    public void toggleRight() {barType = barType ^ RIGHT;}

    // Class 'Bar' must either be declared abstract or implement abstract method 'show(Graphics)' in 'Show'
    @Override
    public void show(Graphics g) {
//        g.setColor(barType == 1 ? Color.RED : Color.BLACK);
//        int yTop = sys.yTop(), N = sys.fmt.size();
//        for (int i = 0; i < N; i++) {
//            Staff.Fmt sf = sys.fmt.get(i);
//            int topLine = yTop + sys.fmt.staffOffset.get(i); // how far you go down from system top
//            g.drawLine(x, topLine, x, topLine + sf.height()); // a vertical line
//        }
        int y1 = 0, y2 = 0; // mark top and bottom of connected components
        boolean justSawBreak = true;
        for (int i = 0; i < sys.fmt.size(); i++) {
            Staff staff = sys.staffs.get(i);
            int staffTop = staff.yTop();
            if (justSawBreak) {y1 = staffTop;} // remember staff of connected components
            y2 = staff.yBot();
            Staff.Fmt sf = sys.fmt.get(i);
            if (!sf.barContinues) { // now we have connected components
                drawLines(g, x, y1, y2);
            }
            justSawBreak = !sf.barContinues;
            if (barType > 3) {drawDots(g, x, staffTop);} // deal with dots
        }
    }

    public static void wings(Graphics g, int x, int y1, int y2, int dx, int dy) { // x, y: delta for the angle line
        g.drawLine(x, y1, x + dx, y1 - dy);
        g.drawLine(x, y2, x + dx, y2 + dy);
    }

    public static void fatBar(Graphics g, int x, int y1, int y2, int dx) {
        g.fillRect(x, y1, dx, y2 - y1);
    }

    public static void thinBar(Graphics g, int x, int y1, int y2) {
        g.drawLine(x, y1, x, y2);
    }

    public void drawDots(Graphics g, int x, int top) {
        // dots are drawn from top to single staff
        // notice: this code assume nLine = 5
        int H = PAGE.sysFmt.maxH;

        if ((barType & LEFT) != 0) { // bitwise operation
            g.fillOval(x - 3 * H, top + 11 * H / 4, H / 2, H / 2);
            g.fillOval(x - 3 * H, top + 19 * H / 4, H / 2, H / 2);
        } // test and trial to get the magic humber

        if ((barType & RIGHT) != 0) {
            g.fillOval(x + 3 * H, top + 11 * H / 4, H / 2, H / 2);
            g.fillOval(x + 3 * H, top + 19 * H / 4, H / 2, H / 2);
        }
    }

    public void drawLines(Graphics g, int x, int y1, int y2) { // drawing vertical lines
        int H = PAGE.sysFmt.maxH;

        if (barType == 0) {thinBar(g, x, y1, y2);}
        if (barType == 1) {thinBar(g, x, y1, y2); thinBar(g, x - 2 * H, y1, y2);} // draw double lines
        if (barType == 2) {fatBar(g, x - H, y1, y2, H); thinBar(g, x - 2 * H, y1, y2);}
        if (barType >= 4) {
            fatBar(g, x - H, y1, y2, H);
            if ((barType & LEFT) != 0) {thinBar(g, x - 2 * H, y1, y2); wings(g, x - 2 * H, y1, y2, -H, H);}
            if ((barType & RIGHT) != 0) {thinBar(g, x + H, y1, y2); wings(g, x + H, y1, y2, H, H);}
        }
    }

}
