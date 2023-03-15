package reactions;

import global.I;
import graphicsLib.G;

import java.util.ArrayList;

public class Gesture {

    public static List UNDO = new List(); // deal with UNDO
    public Shape shape;
    public G.VS vs;

    // do not want users to access this
    private Gesture(Shape shape, G.VS vs) {
        this.shape = shape;
        this.vs = vs;
    }

    // this is how the user can get Gesture
    public static Gesture getNew(Ink ink) {// can return null
        Shape s = Shape.recognize(ink);
        return (s == null) ? null : new Gesture(s, ink.vs);
    }

    public void doGesture() {
        Reaction r = Reaction.best(this); // this gesture
        if (r != null) {
            UNDO.add(this); // first add to the UNDO list
            r.act(this); // then add to the reaction
        }
    }

    public void redoGesture() {
        Reaction r = Reaction.best(this); // this gesture
        if (r != null) {r.act(this);}// add to the reaction
    }

    public static void undo() { // quick and dirty undo with side effect (color rndColor may change)
        if (UNDO.size() == 0) {return;} // if size is 0, can not undo
        UNDO.remove(UNDO.size() - 1); // remove the latest element
        Layer.nuke(); // blow away the related layers, eliminate all mess
        Reaction.nuke(); // blow away the marketplace, clear the byShape map and reload the initial reaction
        UNDO.redo();
    }


    public static I.Area AREA = new I.Area() {
        // I.Area is an abstract class
        // I.Area is a signature; we will define anonymous class here
        public boolean hit(int x, int y) {return true;}

        public void dn(int x, int y) {Ink.BUFFER.dn(x, y);}

        public void drag(int x, int y) {Ink.BUFFER.drag(x, y);}

        public void up(int x, int y) {
            Ink.BUFFER.add(x, y);
            Ink ink = new Ink();
            Gesture gesture = Gesture.getNew(ink); // can fail
            Ink.BUFFER.clear();
//            if (gesture != null) {
//                System.out.println("Gesture: " + gesture.shape.name);
//                Reaction r = Reaction.best(gesture); // can fail
//                if (r != null) {
//                    System.out.println("Reaction not null");
//                    r.act(gesture);
//                }
//            }
            if (gesture != null) {
                if (gesture.shape.name.equals("N-N")) { // harewire the N-N gesture to "undo"
                    undo(); // side effect: color will be different since it's rndColor that changes upon each paintComponent()
                } else {
                    gesture.doGesture();
                }
            }
        }
    }; // Notice that there we need a ';' due to the constructor

    // Deal with UNDO
    // -------------------------------List-------------------------------------
    public static class List extends ArrayList<Gesture> {
        public void redo() {for (Gesture g : this) {g.redoGesture();}}
    }
}
