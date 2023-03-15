package reactions;

import global.UC;
import graphicsLib.G;
import graphicsLib.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ShapeTrainer extends Window {

    // feedback for users
    public static String UNKNOWN = " <- this name is currently unknown.";
    public static String ILLEGAL = " <- this name is NOT a legal shape name"; // illegal shape: dots, empty string
    public static String KNOWN = " <- this name is a known shape.";
    public static String curName = ""; // current name
    public static String curState = ILLEGAL; // current state
    public static Shape.Prototype.List pList = null;

    public ShapeTrainer() {
        super("ShapeTrainer", 1000, 700);
    }

    public void setState() {
        // curState = (curName.equals("") || curName.equals("DOT")) ? ILLEGAL : UNKNOWN;
        curState = (Shape.Database.isLegal(curName)) ? UNKNOWN : ILLEGAL;
        if (curState == UNKNOWN) {
            // test whether it is in the database
            if (Shape.DB.containsKey(curName)) {
                curState = KNOWN;
                pList = Shape.DB.get(curName).prototypes;
            } else {pList = null;}
        }
    }

    public static void main(String[] args) {
        (PANEL = new ShapeTrainer()).launch();
    }

    public void paintComponent(Graphics g) {
        G.clearBack(g);
        g.setColor(Color.BLACK);
        g.drawString(curName, 600, 30);
        g.drawString(curState, 700, 30);

        g.setColor(Color.RED);
        Ink.BUFFER.show(g);
        if (pList != null) {pList.show(g);}
    }

    // KeyTyped only correctly interprets ASCII values
    public void keyTyped(KeyEvent ke) { // keyTyped != keyPressed, such as delete or fn key
        char c = ke.getKeyChar();
        System.out.println("Typed: " + c);
        // hit space key: type new name
        curName = (c == ' ' || c == 0x0d || c == 0x0a) ? "" : curName + c; // can use space to redo typing
        // hit enter key: save
        if (c == 0x0d || c == 0x0a) {Shape.Database.save();}
        setState();
        repaint();
    }

    public void mousePressed(MouseEvent me) {
        Ink.BUFFER.dn(me.getX(), me.getY());
        repaint();
    }

    public void mouseDragged(MouseEvent me) {
        Ink.BUFFER.drag(me.getX(), me.getY());
        repaint();
    }

    public void mouseReleased(MouseEvent me) {
        Ink ink = new Ink();
        Shape recognized = Shape.recognize(ink);
        if (recognized == Shape.DOT) {
            removePrototype(me.getX(), me.getY());
            return;
        }
        Shape.DB.train(curName, ink.norm);
        setState();
        repaint();
        /*
         * if (curState != ILLEGAL) {
         * Ink ink = new Ink();
         * Shape.Prototype proto;
         * Shape recognized = Shape.recognize(ink);
         * if (recognized == Shape.DOT) {
         * removePrototype(me.getX(), me.getY());
         * return;
         * }
         *
         * if (pList == null) {
         * // create the new shape
         * Shape s = new Shape(curName);
         * Shape.DB.put(curName, s);
         * pList = s.prototypes;
         * }
         * if (pList.bestDist(ink.norm) < UC.noMatchDist) {
         * // found match
         * proto = Shape.Prototype.List.bestMatch;
         * proto.blend(ink.norm);
         * } else {
         * // no good match
         * proto = new Shape.Prototype();
         * pList.add(proto);
         * }
         * setState();
         * }
         * repaint();
         */
    }

    // clean unnecessary shape by clicking
    public void removePrototype(int x, int y) {
        if (pList == null) {return;}
        int idx = pList.hitProto(x, y);
        if (idx >= 0) {pList.remove(idx);}
        repaint();
    }

}
