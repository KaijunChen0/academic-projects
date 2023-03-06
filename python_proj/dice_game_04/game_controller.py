from pair_of_dice import PairOfDice


FIRST_ROLL_LOSE = [2, 3, 12]
FIRST_ROLL_WIN = [7, 11]
SECOND_ROLL_LOSE = 7


class GameController:
    '''
    A controller for the Street Craps game
    '''
    def __init__(self):
        '''
        Constructor for game controller
        '''
        self.player_dice = PairOfDice()

    def start_play(self):
        '''
        start the game
        '''
        print("Press enter to roll the dice...")
        action = input()
        self.play_roll()

    def play_roll(self):
        '''
        Implement each roll play
        '''
        # start first roll
        value1, value2 = self.player_dice.roll_dice()
        first_point = self.player_dice.current_value(value1, value2)
        if first_point in FIRST_ROLL_WIN:
            print(f"You rolled {first_point}. You win!")
        elif first_point in FIRST_ROLL_LOSE:
            print(f"You rolled {first_point}. You lose.")
        else:
            print(f"Your point is {first_point}"),
            # mark down the point of first roll
            lucky_point = first_point
            # start next roll
            second_point = self.play_next()
            if second_point == lucky_point:
                print(f"You rolled {second_point}. You win!")
            elif second_point == SECOND_ROLL_LOSE:
                print(f"You rolled {second_point}. You lose.")
            else:
                while (second_point != lucky_point) and\
                      (second_point != SECOND_ROLL_LOSE):
                    print(f"You rolled {second_point}.")
                    second_point = self.play_next()
                    if second_point == lucky_point:
                        print(f"You rolled {second_point}. You win!")
                    if second_point == SECOND_ROLL_LOSE:
                        print(f"You rolled {second_point}.You lose.")

    def play_next(self):
        '''
        Prompt player to continue game and keep track of new point
        '''
        print('Press enter to roll the dice...')
        action = input()
        value1, value2 = self.player_dice.roll_dice()
        next_point = self.player_dice.current_value(value1, value2)
        return next_point
