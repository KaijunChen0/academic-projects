from asteroid import Asteroid


def test_constructor():
    SPACE = {'w': 100, 'h': 200}

    # Test minimal required constructor args
    a = Asteroid(SPACE)
    assert a.SPACE['w'] == 100 and \
        a.SPACE['h'] == 200 and \
        hasattr(a, "x") and \
        hasattr(a, "y") and \
        hasattr(a, "x_vel") and \
        hasattr(a, "y_vel") and \
        hasattr(a, "rotation") and \
        hasattr(a, "rot_vel") and \
        a.asize == 'Large'

    # Test with optional size value
    b = Asteroid(SPACE, 'Small')
    assert b.asize == 'Small'
    # Add test cases
    c = Asteroid(SPACE, 'Med')
    assert c.asize == 'Med'
    d = Asteroid(SPACE, 'Large')
    assert d.asize == 'Large'

    # Test with insufficient arguments
    try:
        c = Asteroid()
    except TypeError:
        failedWithTypeError = True
    assert failedWithTypeError
