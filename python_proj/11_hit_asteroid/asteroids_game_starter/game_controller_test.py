from game_controller import GameController


def test_constructor():
    SPACE = {'w': 100, 'h': 200}

    # Test minimal required constructor args
    fadeout = 100
    gc = GameController(SPACE, fadeout)

    assert gc.SPACE['w'] == 100 and \
        gc.SPACE['h'] == 200 and \
        gc.fadeout == fadeout and \
        not gc.spaceship_hit and \
        not gc.asteroid_destroyed and \
        len(gc.laser_beams) == 0 and \
        len(gc.asteroids) == 1 and \
        gc.asteroids[0].__class__.__name__ == 'Asteroid' and \
        gc.spaceship.__class__.__name__ == 'Spaceship'
