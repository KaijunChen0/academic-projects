from die import Die


class PairOfDice:
    '''
    Contruct two dice
    '''
    def __init__(self):
        self.die1 = Die()
        self.die2 = Die()

    def roll_dice(self):
        '''
        Return two random numbers
        None -> (integer, integer)
        '''
        return self.die1.roll(), self.die2.roll()

    def current_value(self, value1, value2):
        '''
        Return the sum of pair of dice
        None -> integer
        '''
        sum_value = value1 + value2
        return sum_value
