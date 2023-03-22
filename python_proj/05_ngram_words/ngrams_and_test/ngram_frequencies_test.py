from ngram_frequencies import NgramFrequencies


def test_constructor():
    '''Test the constructor'''
    ngramfre = NgramFrequencies()
    empty_count = 0
    assert ngramfre.unigrams_count == {}
    assert ngramfre.bigrams_count == {}
    assert ngramfre.trigrams_count == {}
    assert ngramfre.unigrams_total == empty_count
    assert ngramfre.bigrams_total == empty_count
    assert ngramfre.trigrams_total == empty_count


def test_add_item():
    '''Test the add_item function'''
    ngramfre = NgramFrequencies()
    pre_text = ["it's a dead scene COMMA", "but that's"]
    ngramfre.add_item(pre_text)
    unigram_count = 7
    bigram_count = 5
    trigram_count = 3
    unigram_example = "it's"
    bigram_example = "COMMA_but"
    trigram_example = "that's_a_good"
    assert ngramfre.unigrams_total == unigram_count
    assert ngramfre.bigrams_total == bigram_count
    assert ngramfre.trigrams_total == trigram_count
    assert unigram_example in ngramfre.unigrams_count
    assert bigram_example not in ngramfre.bigrams_count
    assert trigram_example not in ngramfre.trigrams_count


def test_top_n_counts():
    '''Test the top_n_counts function'''
    ngramfre = NgramFrequencies()
    counts = {'f': 3,
              'aab': 2,
              'c': 1}
    n = 2
    assert ngramfre.top_n_counts(counts, n) == [('f', 3), ('aab', 2)]


def test_top_n_freqs():
    '''Test the top_n_freqs function'''
    ngramfre = NgramFrequencies()
    freqs = {'f': 0.5,
             'aab': 0.3333,
             'c': 0.1666}
    n = 2
    assert ngramfre.top_n_freqs(freqs, n) == [('f', 0.5), ('aab', 0.3333)]


def test_frequency():
    '''Test the frequency function'''
    ngramfre = NgramFrequencies()
    res1 = 0.013
    res2 = None
    assert ngramfre.frequency(80, 1) == res1
    assert ngramfre.frequency(0, 1) is None


def test_unigram_freqs_property():
    '''Test the property of unigram_freqs'''
    ngramfre = NgramFrequencies()
    empty_count = 0
    assert len(ngramfre.unigram_freqs) == empty_count


def test_bigram_freqs_property():
    '''Test the property of bigram_freqs'''
    ngramfre = NgramFrequencies()
    empty_count = 0
    assert len(ngramfre.bigram_freqs) == empty_count


def test_trigram_freqs_property():
    '''Test the property of trigram_freqs'''
    ngramfre = NgramFrequencies()
    empty_count = 0
    assert len(ngramfre.trigram_freqs) == empty_count
