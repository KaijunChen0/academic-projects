from constants import *


class Board:
    '''The board of checker game'''

    def __init__(self):
        self.board_size = BOARD_SIZE
        self.square_size = SQUARE_SIZE
        self.grid = [[None for i in range(2)] for i in range(2)]

    def draw_board(self):
        '''Draw a board'''
        noStroke()
        rectMode(CORNER)
        for row in range(ROWS):
            for col in range(COLS):
                if not (row + col) % 2:
                    fill(*BURLYWOOD)
                else:
                    fill(*UPSBROWN)
                rect(col * self.square_size, row * self.square_size,
                     self.square_size, self.square_size)
