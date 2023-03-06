import random


# G and Y represent text formatting codes for green and
# yellow text output. Variable names are brief so as to
# be unobtrusive when interspersed with text
G = '\x1b[0;30;42m'  # green text
Y = '\x1b[0;30;43m'  # yellow text
N = '\x1b[0m'        # normal text/no highlighting

ALLOWED_GUESSES = 6
WORD_LENGTH = 5


def main():
    '''
    Play Wordle Game
    '''
    computer_word = get_a_random_word().upper()
    # test case
    # computer_word = 'tests'.upper()
    player_word = ""
    print(f"Welcome to {Y}PY{G}WOR{Y}D{G}LE{N}!")

    # generate a word list to keep track of each try.
    guessed_words = []
    guess_times = 0
    # while loop for usage limit of player
    while guess_times < ALLOWED_GUESSES:
        player_word = input("Enter your guess (5 letters):\n").upper()
        # while loop to ensure valid input
        while (len(player_word) != WORD_LENGTH) or (not is_word(player_word)):
            player_word = input("Enter your guess (5 letters):\n").upper()
        guess_times += 1
        guessed_words.append(player_word)
        # print out each try in the game
        for i in range(len(guessed_words)):
            print("\t\t" + format_guess(computer_word, guessed_words[i]) + N)
        # tell player whether lose or win the game
        if is_right(computer_word, player_word):
            print(f"Congrats! You got it in {guess_times} tries")
            break
        if guess_times == ALLOWED_GUESSES:
            print(f"Sorry, the word was {computer_word}")


def format_guess(answer, guess_word):
    '''
    return a word with highlighted color symbols
    (string, string) -> string
    '''
    # Implement this function so that it returns
    # the guess string highlighted with green and yellow based
    # on comparing letters with the original word.
    # set the default color of each letter is normal.
    color_list = [N, N, N, N, N]
    answer_copy = [char.upper() for char in answer]
    # first round to select and tag green letter.
    for i in range(len(guess_word)):
        if guess_word[i] == answer_copy[i]:
            color_list[i] = G
            answer_copy[i] = "_"
    # second round to tag yellow letter and avoid repetition.
    for i in range(len(guess_word)):
        if guess_word[i] in answer_copy and color_list[i] != G:
            color_list[i] = Y
            answer_copy[i] = "_"
    # combine letters with color into a word
    tag_guess_word = ""
    for i in range(len(guess_word)):
        tag_guess_word += (color_list[i] + guess_word[i])
    return tag_guess_word


def get_valid_words():
    '''
    return a list that each element's length is equal to five.
    None -> list
    '''
    # Implement this function so that it returns
    # a list of words consisting of only words of the
    # correct length from the Scrabble word list
    words_file = open("/Users/kc/Documents/GitHub/cs5001/hw06/"
                      + "Collins Scrabble Words (2019).txt", "r")
    five_letter_words = []
    for each_line in words_file:
        if len(each_line.rstrip()) == WORD_LENGTH:
            five_letter_words.append(each_line.rstrip())
    return five_letter_words


def get_a_random_word():
    '''
    return a random five-letter word from the assigned list
    None -> string
    '''
    random_index = random.randint(0, len(get_valid_words()) - 1)
    return get_valid_words()[random_index]


def is_right(answer, guess_word):
    '''
    return True if guess_word matches answer
    (string, string) -> boolean
    '''
    is_right = False
    if answer == guess_word:
        is_right = True
    return is_right


def is_word(guess_word):
    '''
    Return True if input is a string of letters
    string -> boolean
    '''
    letters_set = {"A", "B", "C", "D", "E", "F", "G",
                   "H", "I", "J", "K", "L", "M", "N",
                   "O", "P", "Q", "R", "S", "T", "U",
                   "V", "W", "X", "Y", "Z", "a", "b",
                   "c", "d", "e", "f", "g", "h", "i",
                   "j", "k", "l", "m", "n", "o", "p",
                   "q", "r", "s", "t", "u", "v", "w",
                   "x", "y", "z"
                   }
    is_word = True
    for letter in guess_word:
        if not (letter in letters_set):
            is_word = False
    return is_word


main()
