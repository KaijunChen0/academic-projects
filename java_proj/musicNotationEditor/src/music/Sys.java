package music;

import reactions.Mass;

import global.UC;
import java.awt.Graphics;
import java.util.ArrayList;

import static music.MusicEd.PAGE;

public class Sys extends Mass {
    public ArrayList<Staff> staffs = new ArrayList<>(); // 'staff' + 's': emphasize plural in code
    public Page page = PAGE;
    public int iSys;
    public Sys.Fmt fmt;

    public Sys(int iSys, Sys.Fmt fmt) {
        super("BACK");
        this.iSys = iSys;
        this.fmt = fmt;
        for (int i = 0; i < fmt.size(); i++) {
            addStaff(new Staff(i, fmt.get(i)));
        }

    }

    public int yTop() {return page.sysTop(iSys);}

    public int yBot() {return staffs.get(staffs.size()-1).yBot();}

    public void addStaff(Staff s) {staffs.add(s); s.sys = this;}

    @Override
    public void show(Graphics g) {
        int y = yTop(), x = page.margin.left;
        g.drawLine(x, y, x, y + fmt.height());
    }

    // ---------------------------Sys.Fmt----------------------------------
    public static class Fmt extends ArrayList<Staff.Fmt> {
        public int maxH = UC.defaultStaffH;

        public ArrayList<Integer> staffOffset = new ArrayList<>();

        public int height() {
            int last = size() - 1;
            return staffOffset.get(last) + get(last).height();
        }

        public void showAt(Graphics g, int y) {
            for (int i = 0; i < size(); i++) {
                get(i).showAt(g, y + staffOffset.get(i));
            }
        }
    }
}
