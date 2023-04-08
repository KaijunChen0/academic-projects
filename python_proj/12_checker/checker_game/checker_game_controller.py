from checker import Checker
from board import Board
from constants import RED, BLACK


class GameController:
    def __init__(self, BOARD_SIZE, SQUARE_SIZE):
        self.board = Board()
        self.player_checkers = set([Checker(BLACK, 50, 150)])
        self.computer_checkers = set()

    def setup_board(self):
        '''Set up the board'''
        self.board.draw_board()

    def display(self):
        for checker in self.player_checkers:
            checker.display()

    def add_checker(self, color, x, y):
        '''Place each checker on the board'''
        self.player_checkers.add(Checker(color, x, y))

    def grab_checker(self, x, y):
        row, col = self.convert_to_grid(x, y)
        checker = self.board.grid[row][col]

    def convert_to_grid(self, x, y):
        row = y // self.board.square_size
        col = x // self.board.square_size
        return row, col
