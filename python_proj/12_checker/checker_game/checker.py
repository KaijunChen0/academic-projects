from constants import SHADOW


class Checker:
    def __init__(self, color, x, y, int_diameter=70, ext_diameter=90):
        '''Constructor for a checker'''
        self.color = color
        self.x = x
        self.y = y
        self.int_diameter = int_diameter
        self.ext_diameter = ext_diameter
        self.sha_diameter = ext_diameter
        self.sha_color = SHADOW
        self.is_king = False

    def display(self):
        '''Draw a checker'''
        # draw shadow
        noStroke()
        fill(*self.sha_color)
        ellipse(self.x + 10, self.y + 10, self.sha_diameter, self.sha_diameter)
        # draw circles
        stroke(255)
        strokeWeight(2)
        fill(0, 0, 0)  # need to change color
        ellipse(self.x, self.y, self.ext_diameter, self.ext_diameter)
        strokeWeight(2)
        ellipse(self.x, self.y, self.int_diameter, self.int_diameter)
        # if reach the opposite border, change to king's graph
        if self.is_king:
            global img
            img = loadImage("crown.png")
            imageMode(CENTER)
            image(img, self.x, self.y,
                  self.int_diameter - 15, self.int_diameter - 15)
