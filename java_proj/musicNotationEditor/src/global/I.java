package global;

import reactions.Gesture;

import java.awt.*;

public interface I {
    public interface Draw { public void draw(Graphics g);}

    public interface Hit { public boolean hit(int x, int y);}

    public interface Area extends Hit {

        public void dn(int x, int y); // dn: mouse down

        public void up(int x, int y);

        public void drag(int x, int y);
    }

    public interface Show { public void show(Graphics g);}

    public interface Act { /* Act for action */ public void act(Gesture g);}

    public interface React extends Act { // inherit some Act functions
        public int bid(Gesture g); // low number is a good bid
    }
}
