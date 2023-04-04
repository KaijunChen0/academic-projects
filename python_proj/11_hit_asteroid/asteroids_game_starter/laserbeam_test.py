from laserbeam import LaserBeam

params = {
    'SPACE': {'w': 100, 'h': 200},
    'x': 50,
    'y': 75,
    'x_vel': 5,
    'y_vel': 7.5,
    'start_count': 0,
    'lifespan': 100
}


def test_constructor():
    # Test minimal required constructor args
    # TODO: add the last param
    a = LaserBeam(params['SPACE'],
                  params['x'], params['y'],
                  params['x_vel'], params['y_vel'],
                  params['start_count'])
    assert a.SPACE['w'] == 100 and \
        a.SPACE['h'] == 200 and \
        a.x_vel == params['x_vel'] * a.LASER_SPEED_FACTOR and \
        a.y_vel == params['y_vel'] * a.LASER_SPEED_FACTOR and \
        a.x == params['x'] + params['x_vel'] and \
        a.y == params['y'] + params['y_vel'] and \
        hasattr(a, "radius") and \
        hasattr(a, "lifespan") and \
        hasattr(a, "diam") and \
        a.start_count == params['start_count'] and \
        a.lifespan == params['lifespan']
