from queue import Queue
from stack import Stack


class WordLadder:
    """A class providing functionality to create word ladders"""
    # TODO:
    # Implement whatever functionality is necessary to generate a
    # stack representing the word ladder based on the parameters
    # passed to the constructor.
    def __init__(self, w1, w2, words_set):
        '''Constructor for WordLadder
        string, string , dictionary -> None
        '''
        self.w1 = w1
        self.w2 = w2
        self.words_set = words_set
        self.queue = Queue()
        self.init_stack = Stack()
        self.init_stack.push(self.w1)
        self.queue.enqueue(self.init_stack)
        self.traveresd_words = set()

    def make_ladder(self):
        '''Add all ladder words in to self.queue
        None -> None
        '''
        while not self.queue.isEmpty():
            cur_stack = self.queue.dequeue()
            cur_word = cur_stack.peek()
            for word in self.prospective_ladders(cur_word):
                if (
                    word not in self.traveresd_words
                    and word in self.words_set
                ):
                    new_stack = cur_stack.copy()
                    new_stack.push(word)
                    self.traveresd_words.add(word)
                    if word == self.w2:
                        return new_stack
                    self.queue.enqueue(new_stack)
        return None

    def prospective_ladders(self, word):
        '''Find all words that have only one letter different with
        input word for making ladder
        string -> list
        '''
        alphabet = 'abcdefghijklmnopqrstuvwxyz'
        ladders = []
        for i in range(len(word)):
            for char in alphabet:
                new_word = word[:i] + char + word[(i+1):]
                ladders.append(new_word)
        # insert a letter at each position of the word to
        # get all possible words
        for i in range(len(word)+1):
            for char in alphabet:
                new_word = word[:i] + char + word[i:]
                ladders.append(new_word)
        # delete a letter at each position of the word to
        # get all possible words
        for i in range(len(word)):
            new_word = word[:i] + word[(i+1):]
            ladders.append(new_word)
        return ladders
