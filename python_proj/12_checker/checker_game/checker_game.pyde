from checker_game_controller import GameController
from constants import BOARD_SIZE, SQUARE_SIZE


gc = GameController(BOARD_SIZE, SQUARE_SIZE)


def setup():
    size(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE)
    gc.board.draw_board()


def draw():
    gc.board.draw_board()
    gc.display()


def mouseDragged():
    gc.grab_checker(mouseX, mouseY)


def mouseReleased():
    pass
