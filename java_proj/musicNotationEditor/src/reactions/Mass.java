package reactions;

import global.I;

// also an ancestor class ('abstract')
public abstract class Mass extends Reaction.List implements I.Show {
    public Layer layer;

    public Mass(String layerName) {
        layer = Layer.byName.get(layerName);
        if (layer != null) {layer.add(this);}
        else {System.out.println("bad layer name: " + layerName);}
    }

    public void delete() {
        clearAll(); // clear all reaction from this list and byShape
        layer.remove(this); // remove self away
    }
}
