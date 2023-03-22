import re


class TextCleaner:
    '''Carry out the text pre-processing'''
    def __init__(self) -> None:
        '''Constructor for cleaned text in a list of sentences'''
        self.sentences = []

    def clean_text(self, text):
        '''Clean text by following essential steps
        string -> None
        '''
        # step1 To remove dot from 'Mr.'; step2 To remove newline;
        # step3 Get rid of punctuations that required;
        # step4 Replace ',' with word
        text_clean1 = self.modify_people_title(text)
        text_clean2 = self.remove_newline(text_clean1)
        text_clean3 = self.remove_punctuations(text_clean2)
        text_clean4 = self.replace_comma(text_clean3)
        self.add_sentences(text_clean4)

    def modify_people_title(self, text):
        '''
        Remove dot from <Mr.>
        string -> string
        '''
        text_after = re.sub("Mr.", "Mr", text)
        return text_after

    def remove_newline(self, text):
        '''
        Remove newline
        string -> string
        '''
        text_after = re.sub("[\n]+", ".", text)
        return text_after

    def remove_punctuations(self, text):
        '''Remove punctuations like quotes, parentheses, dashes, etc.
        string -> string
        '''
        text_after = re.sub('\(|\)|"|-', "", text)
        return text_after

    def replace_comma(self, text):
        '''Replace ',' with one whitespace and word COMMA
        string -> string
        '''
        # render text all lowercase before changing
        text_after = re.sub(",", " COMMA", text.lower())
        return text_after

    def add_sentences(self, clean_text):
        '''
        Get rid of empty elements and add to attributes
        string -> None
        '''
        # get rid of empty string for double check
        sentences_list = clean_text.split(".")
        for sen in sentences_list:
            if len(sen) == 0:
                sentences_list.remove(sen)
        for sen in sentences_list:
            self.sentences.append(sen)

    @property
    def count_sentences(self):
        '''Count the number of sentences in the self.sentences
        None -> None
        '''
        return len(self.sentences)
