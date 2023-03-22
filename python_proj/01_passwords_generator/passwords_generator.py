import random


LENGTH_OF_NAME = 7


def main():
    '''The function can generate one user name and three password options for
    users who need to enter their first name, last name and favorite
    word separately.'''
    # input all info from users
    print("Welcome to the username and password generator!")
    first_name = remove_space(input("Please enter your first name: "))
    last_name = remove_space(input("Please enter your last name: "))
    favorite_word = remove_space(input("Please enter your favorite word: "))

    # generate a user name with total nine of letters, asterisks and number.
    # firstly, generate the first part, which is a letter
    # randomly from user's first name.
    length_first_name = len(first_name)
    user_name_p1 = first_name.lower()[random.randint(0, length_first_name - 1)]

    # Then generate the following seven letters and
    # asterisks, which is from last name.
    length_last_name = len(last_name)
    if length_last_name < LENGTH_OF_NAME:
        cnt = LENGTH_OF_NAME - length_last_name
        user_name_p2 = last_name.lower() + "*" * cnt

    # finally, pick a random number from 0 to 99
    user_name_p3 = str(random.randint(0, 99))

    # concatenate three parts to generate a user name
    user_name = user_name_p1 + user_name_p2 + user_name_p3

    # password1 consists of three parts in sequence, which is
    # first name changed by some rules, a random number from 0
    # to 99, and last name changed by some rules.
    # rules: replace a by @, o by 0, l by 1, s by $.
    password1_p1 = replace_letter(first_name)
    password1_p2 = str(random.randint(0, 99))
    password1_p3 = replace_letter(last_name)
    password1 = password1_p1 + password1_p2 + password1_p3

    # password2 consists of six letters separately from the first and
    # last letters of first name, last name and favorite word. In each pair,
    # the first letter is lower case and the last one is upper case.
    password2_p1 = pick_pair_letters(first_name)
    password2_p2 = pick_pair_letters(last_name)
    password2_p3 = pick_pair_letters(favorite_word)
    password2 = password2_p1 + password2_p2 + password2_p3

    # password3 consists of a random-length portion of the first name,
    # combined with random-length portions of the favorite word
    # and last name (in any order).
    length_fav_word = len(favorite_word)
    password3_p1 = first_name[:random.randint(1, length_first_name)]
    password3_p2 = last_name[:random.randint(1, length_last_name)]
    password3_p3 = favorite_word[:random.randint(1, length_fav_word)]
    mylist = []
    mylist.append(password3_p1)
    mylist.append(password3_p2)
    mylist.append(password3_p3)
    random.shuffle(mylist)
    password3 = mylist[0] + mylist[1] + mylist[2]

    print(f"Thanks {first_name}, your user name is {user_name}\n")
    print("Here are three suggested passwords for you to consider: \n")
    print(f"Password 1: {password1}")
    print(f"Password 2: {password2}")
    print(f"Password 3: {password3}")


def remove_space(word):
    '''The function can remove whitespace in a string'''
    return "".join(word.split())


def replace_letter(word):
    '''The function can replace some letters by following rules
    that all 'a' should be replaced by @, o by 0, l by 1, s by $.'''
    word1 = word.replace("a", "@")
    word2 = word1.replace("o", "0")
    word3 = word2.replace("l", "1")
    word4 = word3.replace("s", "$")
    return word4


def pick_pair_letters(word):
    '''The function can pick the first and last letters from
    the argument, and return the paired letters with first lower
    case and second upper case'''
    length = len(word)
    pair = word.lower()[0] + word.upper()[length - 1]
    return pair


main()
