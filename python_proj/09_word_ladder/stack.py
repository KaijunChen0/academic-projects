import copy


class Stack:
    def __init__(self):
        """Initializes stack"""
        self.items = []

    def __repr__(self):
        """Provides string representation for stack
        Self -> String"""
        s = ""
        for item in self.items:
            s += str(item) + "\t"
        return s

    def push(self, item):
        """Pushes an item onto the stack
        Self Object -> None"""
        self.items.append(item)

    def pop(self):
        """Pops an item from the stack
        Self -> Object"""
        return self.items.pop()

    def peek(self):
        """Checks the next item on the stack
        Self -> Object"""
        return self.items[-1]

    def size(self):
        """Gives the size of the stack
        Self -> Int"""
        return len(self.items)

    def isEmpty(self):
        """Tells whether the stack is empty
        Self -> Boolean"""
        return len(self.items) == 0

    def copy(self):
        """Provides a full copy of itself
        Self -> Stack"""
        return copy.deepcopy(self)
