from debris import Debris

params = {
    'SPACE': {'w': 100, 'h': 200},
    'rotation': 10,
    'radius': 50,
    'endpoint1': (0, 100),
    'endpoint2': (50, 150),
    'x': 50,
    'y': 75,
    'x_vel': 5,
    'y_vel': 7.5,
    'fade': 150
}


def test_constructor():
    # Test minimal required constructor args
    a = Debris(params['SPACE'], params['rotation'],
               params['endpoint1'], params['endpoint2'],
               params['x'], params['y'], params['x_vel'], params['y_vel'],
               params['radius'], params['fade'])
    assert a.SPACE['w'] == 100 and \
        a.SPACE['h'] == 200 and \
        a.rotation == params['rotation'] and \
        a.endpoint1 == params['endpoint1'] and \
        a.endpoint2 == params['endpoint2'] and \
        a.x == params['x'] and a.y == params['y'] and \
        a.x_vel == params['x_vel'] and a.y_vel == params['y_vel'] and \
        a.lifespan == params['fade'] and \
        a.fadeout == params['fade'] and \
        hasattr(a, "radius")


def test_random_rot_vel():
    a = Debris(params['SPACE'], params['rotation'],
               params['endpoint1'], params['endpoint2'],
               params['x'], params['y'], params['x_vel'], params['y_vel'],
               params['radius'], params['fade'])
    n0 = -1
    # try 100 random numbers and make sure they are all
    # different and all within the expected range
    for _ in range(100):
        n1 = a.random_rot_vel()
        assert n1 <= 1.5
        assert n1 >= -0.5
        assert n0 != n1
        n0 = n1
