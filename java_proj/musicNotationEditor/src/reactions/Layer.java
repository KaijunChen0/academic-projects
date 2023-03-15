package reactions;

import global.I;
// import graphicsLib.G;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

// showing the layers in order = draw the layer lists

public class Layer extends ArrayList<I.Show> implements I.Show {

    // notice that the declaration order of 'byName' and 'ALL' could not be changed
    public static HashMap<String, Layer> byName = new HashMap<>();
    public static Layer ALL = new Layer("ALL");
    public String name;

    public Layer(String name) {
        this.name = name;
        if (!name.equals("ALL")) {ALL.add(this);}
        byName.put(name, this);
    }

    @Override
    public void show(Graphics g) {
        System.out.println("LS: " + name + size()); // layer show
        for (I.Show item : this) {item.show(g);}
    }

    public static void nuke() { // nuke layers before undo
        for (I.Show layer : ALL) {((Layer) layer).clear();} // the first (Layer) is doing a cast
    }

    public static void creatAll(String[] a) {
        for(String s: a) {new Layer(s);}
    }
}
