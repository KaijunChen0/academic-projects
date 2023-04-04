from laserbeam import LaserBeam
from asteroid import Asteroid
from spaceship import Spaceship


class GameController:
    """
    Maintains the state of the game
    and manages interactions of game elements.
    """

    def __init__(self, SPACE, fadeout):
        """Initialize the game controller"""
        self.SPACE = SPACE
        self.fadeout = fadeout

        self.spaceship_hit = False
        self.asteroid_destroyed = False
        self.asteroids = [Asteroid(self.SPACE)]
        self.laser_beams = []
        self.spaceship = Spaceship(self.SPACE)

    def update(self):
        """Updates game state on every frame"""
        self.do_intersections()

        for asteroid in self.asteroids:
            asteroid.display()

        # ======================================================
        # TODO: Problem 3, Part 2: Laser beam handler
        # Your code will replace (or augment) the next several
        # lines. Laser beam objects should remain in the scene
        # as many frames as their lifespan allows.
        # Begin problem 3 code changes

        for lb in self.laser_beams:
            # evaluate the lifespan with frameCount
            if frameCount - lb.start_count > lb.lifespan:
                self.laser_beams.remove(lb)

        for lb in range(len(self.laser_beams)):
            self.laser_beams[lb].display()

        # End problem 3, part 2 code changes
        # =======================================================

        self.spaceship.display()

        # Carries out necessary actions if game over
        if self.spaceship_hit:
            if self.fadeout <= 0:
                fill(1)
                textSize(30)
                text("YOU HIT AN ASTEROID",
                     self.SPACE['w']/2 - 165, self.SPACE['h']/2)
            else:
                self.fadeout -= 1

        if self.asteroid_destroyed:
            fill(1)
            textSize(30)
            text("YOU DESTROYED THE ASTEROIDS!!!",
                 self.SPACE['w']/2 - 250, self.SPACE['h']/2)

    def fire_laser(self, x, y, rot):
        """Add a laser beam to the game"""
        x_vel = sin(radians(rot))
        y_vel = -cos(radians(rot))
        # add the start frameCount in laser beam
        self.laser_beams.append(
            LaserBeam(self.SPACE, x, y, x_vel, y_vel, frameCount)
            )

    def handle_keypress(self, key, keycode=None):
        if (key == ' '):
            if self.spaceship.intact:
                self.spaceship.control(' ', self)
        if (keycode):
            if self.spaceship.intact:
                self.spaceship.control(keycode)

    def handle_keyup(self):
        # TODO: Problem2: remove not to let thruster disappear when keyup
        if self.spaceship.intact:
            self.spaceship.control('keyup')

    def do_intersections(self):
        # ======================================================
        # TODO: Problem 4, Part 1: Intersections
        # Here's where you'll probably want to check for intersections
        # between asteroids and laser beams. Laser beams should be removed
        # from the scene if they hit an asteroid, and the asteroid should
        # blow up (the blow_up_asteroid method also must be written. It's
        # been started for you below).
        #
        # The intersection logic below between the spaceship
        # and asteroids should give a hint as to how this will work.
        # Begin code changes for Problem 4, Part 1: Intersections

        # to check if laser beams hit an asteroid
        if self.spaceship.intact:
            for lb_idx, lb in enumerate(self.laser_beams):
                for ast_idx, ast in enumerate(self.asteroids):
                    # evaluate the danger distance between them
                    if (
                        abs(lb.x - ast.x) < max(ast.radius, lb.radius)
                        and abs(lb.y - ast.y) < max(ast.radius, lb.radius)
                    ):
                        # get the index of laser beam and the index of asteroid
                        self.blow_up_asteroid(lb_idx, ast_idx)

        # End of code changes for Problem 4, Part 1: Intersections
        # ======================================================

        # If the space ship still hasn't been blown up
        if self.spaceship.intact:
            # Check each asteroid for intersection
            for i in range(len(self.asteroids)):
                if (
                      abs(self.spaceship.x - self.asteroids[i].x)
                      < max(self.asteroids[i].radius, self.spaceship.radius)
                      and
                      abs(self.spaceship.y - self.asteroids[i].y)
                      < max(self.asteroids[i].radius, self.spaceship.radius)):
                    # We've intersected an asteroid
                    self.spaceship.blow_up(self.fadeout)
                    self.spaceship_hit = True

    def blow_up_asteroid(self, lb_idx, ast_idx):
        # ======================================================
        # TODO: Problem 4, Part 2: Asteroid blow-up

        # Here you'll write the code to blow up an asteroid.
        # The parameters represent the indexes for the list of
        # asteroids and the list of laser beams, indicating
        # which asteroid is hit by which laser beam.

        # You'll need to:
        # A) Remove the hit asteroid from the scene
        # B) Add appropriate smaller asteroids to the scene
        # C) Make sure that the smaller asteroids are positioned
        #    correctly and flying off in the correct directions

        # Specifically. If the large asteroid is hit, it should
        # break into two medium asteroids, which should fly off
        # perpendicularly to the direction of the laser beam.

        # If a medium asteroid is hit, it should break into three
        # small asteroids, two of which should fly off perpendicularly
        # to the direction of the laser beam, and the third
        # should fly off in the same direction that the laser
        # beam had been traveling.

        # If a small asteroid is hit, it disappears.

        # Begin code changes for Problem 4, Part 2: Asteroid blow-up

        # Remove the hit asteroid from the scene
        blown_up_ast = self.asteroids.pop(ast_idx)
        # Remove the hit laser beam
        laser_beam_hit = self.laser_beams.pop(lb_idx)
        VEL_ADJUESTMENT = 0.75
        # Add appropriate smaller asteroids to the scene
        # Make sure that the smaller asteroids are positioned
        #    correctly and flying off in the correct directions
        if (blown_up_ast.asize == 'Large'):
            # adjust the vel of asteroid1 to make it
            # flying off perpendicularly
            new_med_ast1 = Asteroid(self.SPACE, 'Med',
                                    blown_up_ast.x, blown_up_ast.y,
                                    -laser_beam_hit.y_vel*VEL_ADJUESTMENT,
                                    laser_beam_hit.x_vel*VEL_ADJUESTMENT)
            # adjust the vel of asteroid2 to make it
            # flying off in the opposite direction of med asteroid1
            new_med_ast2 = Asteroid(self.SPACE, 'Med',
                                    blown_up_ast.x, blown_up_ast.y,
                                    laser_beam_hit.y_vel*VEL_ADJUESTMENT,
                                    -laser_beam_hit.x_vel*VEL_ADJUESTMENT)
            self.asteroids.extend([new_med_ast1, new_med_ast2])

        elif (blown_up_ast.asize == 'Med'):
            # flying off perpendicularly
            new_small_ast1 = Asteroid(self.SPACE, 'Small',
                                      blown_up_ast.x, blown_up_ast.y,
                                      -laser_beam_hit.y_vel*VEL_ADJUESTMENT,
                                      laser_beam_hit.x_vel*VEL_ADJUESTMENT)
            # flying off in the opposite direction of small asteroid1
            new_small_ast2 = Asteroid(self.SPACE, 'Small',
                                      blown_up_ast.x, blown_up_ast.y,
                                      laser_beam_hit.y_vel*VEL_ADJUESTMENT,
                                      -laser_beam_hit.x_vel*VEL_ADJUESTMENT)
            # flying off in the direction of laser beam hit
            new_small_ast3 = Asteroid(self.SPACE, 'Small',
                                      blown_up_ast.x, blown_up_ast.y,
                                      laser_beam_hit.x_vel*VEL_ADJUESTMENT,
                                      laser_beam_hit.y_vel*VEL_ADJUESTMENT)
            self.asteroids.extend([new_small_ast1,
                                  new_small_ast2,
                                  new_small_ast3])

        if not self.asteroids:
            self.asteroid_destroyed = True

        # End of code changes for Problem 4, Part 2: Asteroid blow-up
        # ======================================================
