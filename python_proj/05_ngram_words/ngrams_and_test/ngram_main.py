from text_cleaner import TextCleaner
from ngram_frequencies import NgramFrequencies
import sys


def main(file_name):
    with open(file_name) as f:
        read_text = f.read()
        # initialize text cleaner object
        tc = TextCleaner()
        # pre-processing for the text
        tc.clean_text(read_text)
        # initialize and pass cleaned text to NgramFrequencies
        ngf = NgramFrequencies()
        ngf.add_item(tc.sentences)
        # here show the results of top ngrams
        print("Top 10 unigrams:")
        for pair in ngf.top_n_freqs(ngf.unigram_freqs, 10):
            print("\t", pair)
        print("Top 10 bigrams:")
        for pair in ngf.top_n_freqs(ngf.bigram_freqs, 10):
            print("\t", pair)
        print("Top 10 trigrams:")
        for pair in ngf.top_n_freqs(ngf.trigram_freqs, 10):
            print("\t", pair)


main(sys.argv[1])
