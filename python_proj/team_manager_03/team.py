from player import Player


VALID_CHARACTERS = ["A", "B", "C", "D", "E", "F", "G",
                    "H", "I", "J", "K", "L", "M", "N",
                    "O", "P", "Q", "R", "S", "T", "U",
                    "V", "W", "X", "Y", "Z", "a", "b",
                    "c", "d", "e", "f", "g", "h", "i",
                    "j", "k", "l", "m", "n", "o", "p",
                    "q", "r", "s", "t", "u", "v", "w",
                    "x", "y", "z", " ", "0", "1", "2",
                    "3", "4", "5", "6", "7", "8", "9"]


class Team:
    """A class representing a dodgeball team"""
    def __init__(self):
        '''Constructor for Team object'''
        self.name = "Anonymous Team"
        self.players = []

    def set_team_name(self, name):
        '''Setter for team name'''
        # check name's validity
        if self.check_team_name_validity(name):
            self.name = name
        else:
            print("Invalid characters in name. Failed to set name.")

    def add_player(self, player_name, player_number, player_position):
        '''Add player object into team'''
        new_player = Player(player_name, player_number, player_position)
        self.players.append(new_player)

    def cut_player(self, player_name):
        '''Remove player with player_name'''
        index = 0.1
        for i in range(len(self.players)):
            if player_name.lower() == self.players[i].name.lower():
                index = i
        if index != 0.1:
            del self.players[index]
        else:
            print("The player isn't on the team. Failed to cut."
                  " (Prompt: check bench first or input valid name)")

    def is_position_filled(self, position):
        '''Check if the position is filled by one or more players'''
        if position in self.position_count_dic():
            if not self.position_count_dic()[position]:
                print(f"No, the {position} position is not filled")
            else:
                print(f"Yes, the {position} position is filled")
        else:
            print(f"Cannot find {position} position")

    def show_roster(self):
        '''Show player list'''
        if not len(self.players):
            print(f"The lineup for {self.name} is:\n"
                  "The team currently has no players")
        else:
            print(f"The lineup for {self.name} is:")
            for index in range(len(self.players)):
                print(self.players[index].__str__())

    def position_count_dic(self):
        '''
        Record player number of each position
        None -> dictionary
        '''
        pos_cnt = {"catcher": 0, "corner": 0, "sniper": 0, "thrower": 0}
        for i in range(len(self.players)):
            the_pos = self.players[i].position
            if the_pos in pos_cnt.keys():
                pos_cnt[the_pos] += 1
            else:
                pos_cnt[the_pos] = 1
        return pos_cnt

    def send_player_to_bench(self, player_name, bench):
        '''
        Send existing player in team to bench
        (string, string) -> None
        '''
        # to check if player_name in players
        index = 0.1
        for i in range(len(self.players)):
            if player_name.lower() == self.players[i].name.lower():
                index = i
        # find target player
        if index != 0.1:
            # remove player from team and send him/her to bench
            target = self.players.pop(index)
            # add player into bench
            bench.send_to_bench(target)
            print(f"Sent {player_name} to bench")
        else:
            print(f"{player_name} isn't on the team. Failed to send")

    def check_team_name_validity(self, name):
        '''
        Check if team name is made up of alphanumeric characters and spaces
        string -> boolean
        '''
        is_valid_name = True
        for char in name:
            if char not in VALID_CHARACTERS:
                is_valid_name = False
        return is_valid_name

    def check_player_name_validity(self, name):
        '''
        Check if player name is made up of alphabetic characters and spaces
        '''
        is_valid_name = True
        for char in name:
            if char not in VALID_CHARACTERS[:53]:
                is_valid_name = False
        return is_valid_name

    def check_player_number_validity(self, player_number):
        '''
        Check if player's number value is an actual numerical value
        string -> boolean
        '''
        # convert parameter into string as we cannot
        # predict which type input will be, and then implement
        if str(player_number).isnumeric():
            return True
        else:
            return False

    def check_player_position_validity(self, play_pos):
        '''
        Check if player position is valid
        string -> boolean
        '''
        formal_pos = self.position_count_dic().keys()
        if play_pos.lower() in formal_pos:
            return True
        else:
            return False
