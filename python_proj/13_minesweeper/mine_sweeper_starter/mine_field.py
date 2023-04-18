from mine_field_cell import MineFieldCell


class MineField():

    def __init__(self, CELL_SIZE, FIELD_SIZE, numFont):
        self.CELL_SIZE = CELL_SIZE
        self.FIELD_SIZE = FIELD_SIZE
        self.cells = {}
        # 3*3 matrix
        self.neighbors = [-1, 0, 1]
        # FIELD_SIZE is 15
        # cells{}: key=row, val={}
        # in val{}: key=col, val=MineFieldCell()
        # like {row: {col:cell} }
        for x in range(self.FIELD_SIZE):
            self.cells[x] = {}
            for y in range(self.FIELD_SIZE):
                self.cells[x][y] = MineFieldCell(
                                        x*self.CELL_SIZE,
                                        y*self.CELL_SIZE,
                                        self.CELL_SIZE,
                                        numFont
                                        )

    def reveal(self, x, y):
        """
        Reveal cells and carry out related action
        """
        self.cells[x][y].covered = False
        if self.cells[x][y].bomb:
            self.cells[x][y].explode = True
            self.gc.end_game('lose')
        else:
            self.check_completion_status()
        if self.cells[x][y].near_bombs == 0:
            # TODO: handle the case where an empty
            # cell is revealed. Use a recursive function
            # to do this
            self.clear_all_empty_neighbors(x, y)

    def clear_all_empty_neighbors(self, x, y):
        #  TODO: Define a recursive function to handle
        #  clearing out all empty/numbered neighbors of an empty
        #  cleared cell. Rewrite this dummy function with
        #  a suitable name and arguments.
        for xneighbor in self.neighbors:
            for yneighbor in self.neighbors:
                if not ((xneighbor == 0 and yneighbor == 0)  # the top left
                        or (x + xneighbor < 0)  # the first row
                        or (y + yneighbor < 0)  # the first column
                        or (x + xneighbor >= self.FIELD_SIZE)  # the last row
                        or (y + yneighbor >= self.FIELD_SIZE)  # the last col
                        or not self.cells[x+xneighbor][y+yneighbor].covered):
                    self.cells[x+xneighbor][y+yneighbor].covered = False
                    if self.cells[x+xneighbor][y+yneighbor].near_bombs == 0:
                        self.clear_all_empty_neighbors(x+xneighbor,
                                                       y+yneighbor)

    def check_completion_status(self):
        """
        Check to see if all non-bomb cells have
        been uncovered
        """
        for x in range(self.FIELD_SIZE):
            for y in range(self.FIELD_SIZE):
                if (self.cells[x][y].covered and
                   not self.cells[x][y].bomb):
                    return
        #
        self.gc.end_game('win')

    def neighbor_bomb_count(self, x, y):
        """
        Get the count of adjacentn bombs for
        a cell
        """
        neighbor_bombs = 0
        for xneighbor in self.neighbors:
            for yneighbor in self.neighbors:
                if not ((xneighbor == 0 and yneighbor == 0)
                        or (x + xneighbor < 0)
                        or (y + yneighbor < 0)
                        or (x + xneighbor >= self.FIELD_SIZE)
                        or (y + yneighbor >= self.FIELD_SIZE)
                        ):
                    if self.cells[x+xneighbor][y+yneighbor].bomb:
                        neighbor_bombs += 1
        return neighbor_bombs

    def loop_through_field(self, fn):
        """
        loops through mine field and carries
        out passed function with x and y arguments
        """
        # This will save some reduplication of code in the
        # following methods
        for x in range(self.FIELD_SIZE):
            for y in range(self.FIELD_SIZE):
                # see update(): fn is a lambda function
                fn(x, y)

    def update(self):
        """
        Update all cells and display
        """
        self.loop_through_field(
            # see MineFieldCell update() funct
            lambda x, y: self.cells[x][y].update()
            )

    def reveal_all(self):
        """
        Reveal all cells
        """
        self.loop_through_field(
            lambda x, y: self.cells[x][y].reveal()
            )

    def plant_mines(self, mines):
        """
        Add mines to appropriate cells
        """
        for mine in mines:
            self.cells[mine[0]][mine[1]].bomb = True
        self.loop_through_field(
                self.set_count
            )

    def set_count(self, x, y):
        """
        Sets the neighboring bomb count for a position
        """
        if not self.cells[x][y].bomb:
            self.cells[x][y].near_bombs =\
                self.neighbor_bomb_count(x, y)
