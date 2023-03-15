package music;

import global.UC;
import reactions.Gesture;
import reactions.Mass;
import reactions.Reaction;

import java.awt.Graphics;

import static music.MusicEd.PAGE;


public class Staff extends Mass {
    public Sys sys; // Staff lives in Sys
    public int iStaff; // index of Staff in Sys
    public Staff.Fmt fmt;

    public Staff(int iStaff, Staff.Fmt sf) {
        super("BACK");
        this.iStaff = iStaff;
        this.fmt = sf;

        addReaction(new Reaction("S-S") { // add reaction for bar lines
            @Override
            public int bid(Gesture g) {
                int x = g.vs.xM(), y1 = g.vs.yL(), y2 = g.vs.yM();
                if (x < PAGE.margin.left || x > PAGE.margin.right + UC.barToMarginSnap) {return UC.noBid;}
                int d = Math.abs(y1 - Staff.this.yTop()) + Math.abs(y2 - Staff.this.yBot());
                return (d < 30) ? d + UC.barToMarginSnap : UC.noBid;
            }

            @Override
            public void act(Gesture g) {
                new Bar(Staff.this.sys, g.vs.xM());
            }
        });

        addReaction(new Reaction("S-S") { // toggle barContinues
            @Override
            public int bid(Gesture g) {
                if (Staff.this.sys.iSys != 0) {return UC.noBid;}
                int y1 = g.vs.yL(), y2 = g.vs.yH();
                int iStaff = Staff.this.iStaff;
                if (iStaff == PAGE.sysFmt.size() - 1) {return UC.noBid;} // if it's last staff, then it cannot continue
                if (Math.abs(y1 - Staff.this.yBot()) > 30) {
                    return UC.noBid;
                } // get close to the bottom line of the staff

                Staff nextStaff = sys.staffs.get(iStaff + 1);
                if (Math.abs(y2 - nextStaff.yTop()) > 30) {return UC.noBid;} // y2

                return 10;
            }

            @Override
            public void act(Gesture g) {
                PAGE.sysFmt.get(Staff.this.iStaff).toggleBarContinues();
            }
        });
    }

    public int sysOff() {return sys.fmt.staffOffset.get(iStaff);}

    public int yTop() {return sys.yTop() + sysOff();}

    public int yBot() {return yTop() + fmt.height();}

    @Override
    public void show(Graphics g) {}

    // -------------------------------Staff.Fmt---------------------------------
    public static class Fmt {
        public int nLine = 5, H = UC.defaultStaffH;
        public boolean barContinues = false;

        public int height() {return 2 * H * (nLine - 1);}

        public void toggleBarContinues() {barContinues = !barContinues;}

        public void showAt(Graphics g, int y) {
            int left = PAGE.margin.left, right = PAGE.margin.right;
            for (int i = 0; i < nLine; i++) {g.drawLine(left, y + 2 * H * i, right, y + 2 * H * i);}
        }
    }


}
