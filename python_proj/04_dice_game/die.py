import random as rd


class Die:
    '''
    Playing a die
    '''
    def __init__(self):
        '''
        Construct a die object
        '''
        self.current_value = self.roll()

    def __str__(self):
        '''
        Provides a string representation of a die object
        None -> None
        '''
        return f"Die's face-up value is {self.current_value}"

    def roll(self):
        '''
        Roll a die once
        None -> Integer
        '''
        return rd.randint(1, 6)
