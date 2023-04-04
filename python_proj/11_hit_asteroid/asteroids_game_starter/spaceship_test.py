from spaceship import Spaceship


params = {
    'SPACE': {'w': 100, 'h': 200},
}


def test_constructor():
    # Test minimal required constructor args
    a = Spaceship(params['SPACE'])
    assert a.SPACE['w'] == 100 and \
        a.SPACE['h'] == 200 and \
        hasattr(a, "intact") and \
        hasattr(a, "x") and hasattr(a, "y") and\
        hasattr(a, "x_vel") and hasattr(a, "y_vel")


def test_blow_up():
    a = Spaceship(params['SPACE'])
    assert not hasattr(a, 'debris')
    assert a.intact
    a.blow_up(100)
    assert len(a.debris) == 3
    assert not a.intact
