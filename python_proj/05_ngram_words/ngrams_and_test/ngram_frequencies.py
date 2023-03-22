PERCISION = 3


class NgramFrequencies:
    '''
    Handle counts and frequency calculations of n-grams
    '''
    def __init__(self):
        '''Constructor for NgramFrequencies'''
        self.unigrams_count = {}
        self.bigrams_count = {}
        self.trigrams_count = {}
        self.unigrams_total = 0
        self.bigrams_total = 0
        self.trigrams_total = 0

    def add_item(self, clean_text):
        '''Analyze clean text from text_cleaner
        list(more than one elements)-> None
        '''
        for sentence in clean_text:
            words_list = sentence.split(" ")
            # e.g ["it's", 'a', 'dead', 'scene', 'COMMA', 'but', "that's",
            # 'a', 'good', 'thing']
            # clear "" in words list
            if "" in words_list:
                words_list.remove("")
            # count unigrams
            for unigram in words_list:
                # calculate the total of unigrams
                self.unigrams_total += 1
                if unigram in self.unigrams_count.keys():
                    self.unigrams_count[unigram] += 1
                else:
                    self.unigrams_count[unigram] = 1
            # count bigrams
            for i in range(len(words_list)-1):
                bigram = "_".join([words_list[i],
                                   words_list[i+1]])
                # calculate the total of bigrams
                self.bigrams_total += 1
                if bigram in self.bigrams_count.keys():
                    self.bigrams_count[bigram] += 1
                else:
                    self.bigrams_count[bigram] = 1
            # count trigrams
            for i in range(len(words_list)-2):
                trigram = "_".join([words_list[i],
                                    words_list[i+1],
                                    words_list[i+2]])
                # calculate the total of trigrams
                self.trigrams_total += 1
                if trigram in self.trigrams_count.keys():
                    self.trigrams_count[trigram] += 1
                else:
                    self.trigrams_count[trigram] = 1

    def top_n_counts(self, counts, n):
        '''Sort count and show top n performance
        (dictionary, integer) -> list
        '''
        sorted_counts = sorted(counts.items(),
                               key=lambda x: x[1],
                               reverse=True)
        return sorted_counts[:n]

    def top_n_freqs(self, gram_freqs, n):
        '''Sort frequency and show top n performance
        (dictionary, integer) -> list
        '''
        sorted_freqs = sorted(gram_freqs.items(),
                              key=lambda x: x[1],
                              reverse=True)
        return sorted_freqs[:n]

    def frequency(self, total, count):
        '''Calculate frequency
        (integer, integer) -> float
        '''
        try:
            freq = round(count/total, PERCISION)
            return freq
        except ZeroDivisionError as e:
            print("Sorry, you are dividing by zero")

    @property
    def unigram_freqs(self):
        '''Return a dictionary of unigram and frequency
        dictionary -> dictionary
        '''
        unigram_freqs = {}
        for key, val in self.unigrams_count.items():
            unigram_freqs[key] = self.frequency(self.unigrams_total, val)
        return unigram_freqs

    @property
    def bigram_freqs(self):
        '''Return a dictionary of unigram and frequency
        dictionary -> dictionary
        '''
        bigram_freqs = {}
        for key, val in self.bigrams_count.items():
            bigram_freqs[key] = self.frequency(self.bigrams_total, val)
        return bigram_freqs

    @property
    def trigram_freqs(self):
        '''Return a dictionary of unigram and frequency
        dictionary -> dictionary
        '''
        trigram_freqs = {}
        for gram, val in self.trigrams_count.items():
            trigram_freqs[gram] = self.frequency(self.trigrams_total, val)
        return trigram_freqs
