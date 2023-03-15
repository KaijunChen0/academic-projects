package reactions;

import global.UC;
import graphicsLib.G;

import java.awt.Graphics;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Shape implements Serializable {
    // go from String to Shape (para being String; will return Shape)
    // keeping track of or updating the shapes in the hashmap
    // revised to a new ShapeDB class below
    public static Database DB = Database.load(); // DB for database

    public static Shape DOT = DB.get("DOT");
    public static Collection<Shape> LIST = DB.values(); // auto update the list of shapes
    public static Shape bestMatch;

    // non-static variables
    public Prototype.List prototypes = new Prototype.List();
    public String name;

    public Shape(String name) {this.name = name;}

    public static Shape recognize(Ink ink) { // handle dots
        if (ink.vs.size.x < UC.dotThreshold && ink.vs.size.y < UC.dotThreshold) {return DOT;}
        bestMatch = null;
        int bestSoFar = UC.noMatchDist;
        for (Shape s : LIST) {
            int d = s.prototypes.bestDist(ink.norm);
            if (d < bestSoFar) {
                bestMatch = s;
                bestSoFar = d;
            }
        }
        return bestMatch;
    }

    // -----------------------Prototype-----------------------
    public static class Prototype extends Ink.Norm implements Serializable {

        public int nBlend = 1; // keep track of # of prototype

        public void blend(Ink.Norm norm) {
            blend(norm, nBlend);
            nBlend++;
        }

        // ---------------List Of Prototype--------------
        public static class List extends ArrayList<Prototype> implements Serializable {

            public static Prototype bestMatch; // set by side effect by min/best dist

            public int bestDist(Ink.Norm norm) { // does not have to succeed
                bestMatch = null; // assume there is no match
                int res = UC.noMatchDist;
                for (Prototype p : this) {
                    int d = p.dist(norm);
                    if (d < res) {
                        bestMatch = p;
                        res = d;
                    }
                }
                return res;
            }

            private static int m = 10, w = 60; // m for margin, w for width
            private static G.VS showBox = new G.VS(m, m, w, w); // small box to show thing

            public void show(Graphics g) {
                g.setColor(Color.ORANGE);
                for (int i = 0; i < size(); i++) {
                    Prototype p = get(i); // get the i element from the list
                    int x = m + i * (m + w);
                    showBox.loc.set(x, m);
                    p.drawAt(g, showBox);
                    g.drawString("" + p.nBlend, x, 20);
                }
            }

            public int hitProto(int x, int y) {
                // return index of hit prototype (or -1, indicating miss)
                if (y < m || x < m || y > m + w) {
                    return -1;
                }
                int res = (x - m) / (m + w);
                return res < size() ? res : -1;
            }

            public void train(Ink.Norm norm) {
                if (bestDist(norm) < UC.noMatchDist) {// found match --> blend
                    bestMatch.blend(norm);
                } else {add(new Shape.Prototype());}
            }

        }
    }

    // -----------------------ShapeDB-------------------------
    public static class Database extends HashMap<String, Shape> {

        public Database() {
            super();
            String dot = "DOT";
            put(dot, new Shape(dot));
        }

        public static Database load() {
            Database res; // res for result
            // res.put("DOT", new Shape("DOT"));
            // Load prior saved DB
            try {
                System.out.println("attempting DB load....");
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(UC.shapeDbFileName));
                res = (Database) ois.readObject(); // cast from string to shape
                System.out.println("successfully loaded - found " + res.keySet());
                ois.close();
            } catch (Exception e) {
                System.out.println("load failed");
                System.out.println(e);
                res = new Database();
                res.put("DOT", new Shape("DOT"));
            }
            return res;
        }

        // serialize objects in the DB
        public static void save() {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(UC.shapeDbFileName));
                oos.writeObject(DB);
                System.out.println("saved " + UC.shapeDbFileName);
                oos.close();
            } catch (Exception e) {
                System.out.println("failed db saved");
                System.out.println(e);
            }
        }

        public Shape forceGet(String name) {
            if (!DB.containsKey(name)) {DB.put(name, new Shape(name));}
            return DB.get(name);
        }

        public void train(String name, Ink.Norm norm) {
            if (isLegal(name)) {forceGet(name).prototypes.train(norm);}
        }

        public static boolean isLegal(String name) {
            return !name.equals("") && !name.equals("DOT");
        }

    }
}
