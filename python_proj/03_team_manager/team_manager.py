from team import Team
from bench import Bench


def main():
    '''Team manager system'''
    print("Welcome to the team manager.")
    the_team = Team()
    the_bench = Bench()

    while True:
        command = (input("What do you want to do?\n")).lower()

        if command == "done":
            print("Shutting down team manager\n")
            return  # this return statement exits main, ending the session.
        elif command == "set team name":
            do_set_team_name(the_team)
        elif command == "show roster":
            do_show_team_roster(the_team)
        elif command == "add player":
            do_add_player_to_team(the_team)
        elif command == "check position is filled":
            do_check_position_filled(the_team)
        elif command == "send player to bench":
            do_send_player_to_bench(the_team, the_bench)
        elif command == "get player from bench":
            do_get_player_from_bench(the_bench, the_team)
        elif command == "cut player":
            # Only allow to cut player on the team instead of on the bench
            # That is, if player is on the bench, manager will not
            # find him/her on the team and will be prompted to search bench
            # first and then get player back to team to cut.
            do_cut_player_from_team(the_team)
        elif command == "show bench":
            do_show_bench(the_bench)
        else:
            do_not_understand()


def do_set_team_name(team):
    '''Set team's name'''
    name = input("What do you want to name the team?\n")
    team.set_team_name(name)


def do_show_team_roster(team):
    '''Show team's members'''
    team.show_roster()


def do_check_position_filled(team):
    '''Check if a specific position is filled'''
    position = input("What position are you checking for?\n").lower()
    team.is_position_filled(position)


def do_add_player_to_team(team):
    '''Add player's info'''
    player_name = input("What's the player's name?\n")
    if team.check_player_name_validity(player_name):
        player_number = input("What's " + player_name + "'s number?\n")
        if team.check_player_number_validity(player_number):
            player_position = input("What's " + player_name + "'s position?\n")
            if team.check_player_position_validity(player_position):
                team.add_player(player_name, player_number, player_position)
                print("Added", player_name, "to", team.name)
            else:
                print("Invalid position. Failed to add player")
        else:
            print("Invalid number. Failed to add player")
    else:
        print("Invalid name. Failed to add player")


def do_send_player_to_bench(team, bench):
    '''Send player to bench'''
    # to check if team is empty
    if len(team.players) == 0:
        print("The team is empty. Failed to send")
    else:
        name = input("Who do you want to send to the bench?\n")
        team.send_player_to_bench(name, bench)


def do_get_player_from_bench(bench, team):
    '''Get the best-rested player by name from the bench'''
    # If the bench is empty, print to the screen that the
    # bench is empty.
    bench.get_from_bench(team)


def do_cut_player_from_team(team):
    '''Cut current player on the team'''
    player_name = input("Who do you want to cut?\n")
    team.cut_player(player_name)


def do_show_bench(bench):
    '''Show players on the bench'''
    bench.show_bench()


def do_not_understand():
    print("I didn't understand that command")


main()
