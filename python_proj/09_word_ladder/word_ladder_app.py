from word_ladder_extra import WordLadder


def main():
    """Run an interactive command line to let the user
    input word pairs and generate word ladders"""
    # store all words with key-val is length of the word with words
    english_words = load_words()
    union_set = set()
    for v in english_words.values():
        union_set.update(v)
    while True:
        w1, w2 = input("> ").split()
        # Create a WordLadder object
        wl = WordLadder(w1, w2, union_set)
        # Generate the word ladder
        word_ladder = wl.make_ladder()
        print("Ladder: ", word_ladder)


def load_words():
    """Load words from complete wordlist file"""
    # We're creating a dictionary keyed on word
    # length, so that we can quickly get to a set of
    # words of a given length.
    valid_words = {}
    # replace with txt local path when you run it
    with open('words_alpha.txt') as word_file:
        for w in word_file.read().split():
            if len(w) in valid_words.keys():
                # key is length of word and
                # val is a set of words with same length.
                # Add to an existing set
                valid_words[len(w)].add(w)
            else:
                # Initialize a set with one element
                valid_words[len(w)] = {w}
    return valid_words


if __name__ == "__main__":
    main()
