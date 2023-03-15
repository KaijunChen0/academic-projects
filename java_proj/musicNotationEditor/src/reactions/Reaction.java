package reactions;

import global.I;
import global.UC;

import java.util.ArrayList;
import java.util.HashMap;


// no implementing methods here: ancestor, just book-keeping of all reactions, hence abstract
// when abstract is added, the red line goes away: let the children handling that
public abstract class Reaction implements I.React {

    private static Map byShape = new Map();
    public static List initialReactions = new List(); // used by undo to restart
    public Shape shape;

    public Reaction(String shapeName) {
        shape = Shape.DB.get(shapeName);
        if (shape == null) {System.out.println("WTF? Shape DB does not contain " + shapeName);}
    }

    // loop through the list to see if it contains the reaction; if not, add to the byShape
    public void enable() {
        System.out.println("enable" + shape.name);
        List list = byShape.getList(shape);
        if (!list.contains(this)) {list.add(this);}
    }

    public void disable() {
        List list = byShape.getList(shape);
        list.remove(this);
    }

    public static Reaction best(Gesture g) { // it can return null
        return byShape.getList(g.shape).loBid(g);
    }

    public static void nuke() { // used before undo
        byShape.clear();
        initialReactions.enable();
    }

    // ---------------------------List----------------------------------
    public static class List extends ArrayList<Reaction> {

        public void addReaction(Reaction r) {add(r); r.enable();}

        public void removeReaction(Reaction r) {remove(r); r.disable();}

        public void clearAll() {
            for (Reaction r : this) {r.disable();}
            this.clear();
        }

        public Reaction loBid(Gesture g) { // can return null
            Reaction res = null;
            int bestSoFar = UC.noBid;
            System.out.println("loBid: " + bestSoFar);
            for (Reaction r : this) {
                int b = r.bid(g);
                System.out.println("bid" + b);
                if (b < bestSoFar) {bestSoFar = b; res = r;}
            }
            return res;
        }

        public void enable() {
            for (Reaction r : this) {r.enable();}
        }
    }

    // ----------------------------Map----------------------------------
    public static class Map extends HashMap<Shape, List> {
        // The Map interface provides methods to add, remove, and retrieve elements from the map.
        // Some commonly used methods include put(key, value) to add a key-value pair,
        // get(key) to retrieve the value associated with a given key, and remove(key) to remove a key-value pair

        // this function forces returning a list
        public List getList(Shape s) {
            List res = get(s);
            if (res == null) {res = new List(); put(s, res);}
            return res;
        }
    }

}
