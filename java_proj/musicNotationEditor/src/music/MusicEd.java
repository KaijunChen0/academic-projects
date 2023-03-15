package music;

import global.UC;
import graphicsLib.G;
import graphicsLib.Window;
import reactions.Gesture;
import reactions.Ink;
import reactions.Layer;
import reactions.Reaction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class MusicEd extends Window {

    public static Page PAGE; // will create in gesture

    static{Layer.creatAll("BACK FORE".split(" "));}

    public MusicEd(){
        super("Music Editor", UC.MAIN_WINDOW_WIDTH, UC.MAIN_WINDOW_HEIGHT);

        Reaction.initialReactions.addReaction(new Reaction("E-E"){
            public int bid(Gesture g) {return 10;}
            public void act(Gesture g) {
                int y = g.vs.yM();
                Sys.Fmt sf = new Sys.Fmt();
                PAGE = new Page(sf);
                PAGE.margin.top = y;
                PAGE.addNewSys();
                PAGE.addNewStaff(0);
                this.disable();
            }
        });
    }

    public void paintComponent(Graphics g) {
        G.clearBack(g);
        g.setColor(Color.GREEN);
        Ink.BUFFER.show(g);
        Layer.ALL.show(g);
    }

    public void mousePressed(MouseEvent me) {
        Gesture.AREA.dn(me.getX(), me.getY());
        repaint();
    }

    public void mouseDragged(MouseEvent me) {
        Gesture.AREA.drag(me.getX(), me.getY());
        repaint();
    }

    public void mouseReleased(MouseEvent me) {
        Gesture.AREA.up(me.getX(), me.getY());
        repaint();
    }

    public static void main(String[] args) {
        (PANEL = new MusicEd()).launch();
    }
}
