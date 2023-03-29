

class Queue:
    def __init__(self):
        """Initialize queue"""
        self.items = []

    def __repr__(self):
        """Provides string representation for queue
        Self -> String"""
        s = "head----------\n"
        for item in self.items:
            s += str(item) + "\n"
        s += "tail----------"
        return s

    def enqueue(self, item):
        """Enqueues an item
        Self Object -> None"""
        self.items.insert(0, item)

    def dequeue(self):
        """Dequeues an item
        Self -> Object"""
        return self.items.pop()

    def peek(self):
        """Checks the next item on the queue
        Self -> Object"""
        return self.items[-1]

    def size(self):
        """Gives the length of the queue
        Self -> Int"""
        return len(self.items)

    def isEmpty(self):
        """Tells whether the queue is empty
        Self -> Boolean"""
        return len(self.items) == 0
