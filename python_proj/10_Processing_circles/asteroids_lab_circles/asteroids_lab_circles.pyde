SIZE_X = 600
SIZE_Y = 600
CIRCLE_RAD = 75  # radius
CIRCLE_X_INCREMENT = 1
CIRCLE_Y_INCREMENT = 1
GRAY_COLOR = (0.5, 0.5, 0.5)
WHITE_COLOR = (1.0, 1.0, 1.0)
LIGHT_BLUE_COLOR = (0.8, 0.9, 1.0)
STROKE_WEIGHT = 3

circle_x = 300
circle_1_y = 100
circle_2_y = 300
circle_3_y = 500
# set the blue circle x-axle
circle_blue_x = 300
circle_blue_y = 300

SPACESHIP_POINTS = (-16, 10,  0, -30, 16, 10)
THRUST_INCREMENT = 0.5
ROTATION_INCREMENT = 3
thrust_factor = 0
spaceship_x = 300
spaceship_y = 300
x_vel = 0
y_vel = 0
rotation = 0


def setup():
    '''Set up the background'''
    size(SIZE_X, SIZE_Y)
    strokeWeight(STROKE_WEIGHT)
    colorMode(RGB, 1)


def draw():
    '''Drawing process'''
    background(0)

    # set the movement of circle blue from the center
    # to bottom and from up to down.
    # draw blue circle
    # to keep blue circle move vertically
    global circle_blue_y
    circle_blue_y += CIRCLE_Y_INCREMENT
    if circle_blue_y > SIZE_Y + CIRCLE_RAD:
        circle_blue_y = circle_blue_y - SIZE_Y
    elif circle_blue_y > SIZE_Y - CIRCLE_RAD:
        draw_circle_2(circle_blue_y - SIZE_Y)
    draw_circle_2(circle_blue_y)
    # draw spaceship
    global rotation
    draw_spaceship()
    # draw two gray circles
    # to keep gray circles move horizontally
    global circle_x
    circle_x = circle_x + CIRCLE_X_INCREMENT
    if circle_x > SIZE_X + CIRCLE_RAD:
        circle_x = circle_x - SIZE_X
    elif circle_x > SIZE_X - CIRCLE_RAD:
        draw_circle_1(circle_x - SIZE_X)
        draw_circle_3(circle_x - SIZE_X)
    draw_circle_1(circle_x)
    draw_circle_3(circle_x)


def draw_circle_1(x):
    '''Draw the bottom circle'''
    fill(*GRAY_COLOR)
    stroke(*WHITE_COLOR)
    ellipse(x, circle_1_y, CIRCLE_RAD*2, CIRCLE_RAD*2)


def draw_circle_2(y):
    '''Draw the middle circle'''
    fill(*LIGHT_BLUE_COLOR)
    stroke(*WHITE_COLOR)
    # replace the x,y coordinate
    # start with the zero point
    ellipse(circle_blue_x, y, CIRCLE_RAD*2, CIRCLE_RAD*2)


def draw_circle_3(x):
    '''Draw the top circle'''
    fill(*GRAY_COLOR)
    stroke(*WHITE_COLOR)
    ellipse(x, circle_3_y, CIRCLE_RAD*2, CIRCLE_RAD*2)


def draw_spaceship():
    '''Draw the spaceship'''
    global spaceship_x
    global spaceship_y
    global x_vel
    global y_vel
    global thrust_factor
    x_vel = (x_vel + sin(radians(rotation))) * thrust_factor
    y_vel = (y_vel - cos(radians(rotation))) * thrust_factor

    spaceship_x = spaceship_x + x_vel
    spaceship_y = spaceship_y + y_vel
    translate(spaceship_x, spaceship_y)
    rotate(radians(rotation))
    fill(0)
    stroke(1)
    strokeWeight(STROKE_WEIGHT)
    triangle(*SPACESHIP_POINTS)
    # adjust the canvas relative position.
    rotate(radians(-rotation))
    translate(-spaceship_x, -spaceship_y)


def keyPressed():
    global rotation
    global thrust_factor
    if (key == CODED):
        if keyCode == UP:
            thrust_factor = THRUST_INCREMENT
        if keyCode == RIGHT:
            rotation += ROTATION_INCREMENT
        if keyCode == LEFT:
            rotation -= ROTATION_INCREMENT
