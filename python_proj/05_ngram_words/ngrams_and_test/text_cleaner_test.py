from text_cleaner import TextCleaner


def test_constructor():
    '''Test the constructor'''
    textcleaner = TextCleaner()
    assert textcleaner.sentences == []


def test_modify_people_title():
    '''Test the clean_text function'''
    textcleaner = TextCleaner()
    ori_text1 = "Mr. Burton"
    ori_text2 = "Mr"
    assert textcleaner.modify_people_title(ori_text1) == "Mr Burton"
    assert textcleaner.modify_people_title(ori_text2) == "Mr"


def test_remove_newline():
    '''Test the remove_newline function'''
    textcleaner = TextCleaner()
    ori_text1 = "end.\n"
    ori_text2 = "en\nd."
    assert textcleaner.remove_newline(ori_text1) == "end.."
    assert textcleaner.remove_newline(ori_text2) == "en.d."


def test_remove_punctuations():
    '''Test the remove_punctuations function'''
    textcleaner = TextCleaner()
    ori_text1 = '(New-York" Times)'
    ori_text2 = 'New-(York" Times)'
    assert textcleaner.remove_punctuations(ori_text1) == 'NewYork Times'
    assert textcleaner.remove_punctuations(ori_text2) == 'NewYork Times'


def test_replace_comma():
    '''Test the replace_comma function'''
    testcleaner = TextCleaner()
    ori_text1 = "hello,"
    ori_text2 = ",,"
    assert testcleaner.replace_comma(ori_text1) == "hello COMMA"
    assert testcleaner.replace_comma(ori_text2) == " COMMA COMMA"


def test_add_sentences():
    '''Test the add_sentences function'''
    testcleaner = TextCleaner()
    ori_text = "Hello world. Hello Python."
    count = 2
    testcleaner.add_sentences(ori_text)
    assert len(testcleaner.sentences) == count


def test_count_sentences_property():
    '''Test the count_sentences property'''
    textcleaner = TextCleaner()
    count = 0
    assert len(textcleaner.sentences) == count


def test_clean_text():
    '''Test the clean text function'''
    testcleaner = TextCleaner()
    ori_text = 'Mr. Burton end.\n\n(New-York" Times) hello, Hello world.'
    testcleaner.clean_text(ori_text)
    count = 2
    assert len(testcleaner.sentences) == 2
