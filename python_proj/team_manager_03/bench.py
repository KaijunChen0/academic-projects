from team import Team
from collections import deque


class Bench:
    """A class representing a sidelines bench"""
    def __init__(self):
        self.bench_players = deque([])

    def send_to_bench(self, player):
        '''Add player into bench'''
        self.bench_players.append(player)

    def get_from_bench(self, team):
        '''Get player from bench to team'''
        # Return the name of the player who has
        # been on the bench longest.
        # assign player back to team
        if len(self.bench_players) != 0:
            left_player = self.bench_players.popleft()
            team.add_player(left_player.name,
                            left_player.number,
                            left_player.position)
            print(f"Got {left_player.name} from bench")
        else:
            print("The bench is empty")

    def show_bench(self):
        '''Show players on the bench'''
        if len(self.bench_players) == 0:
            print("The bench currently has no players")
        else:
            print("The bench currently includes:", end=" ")
            for player in self.bench_players:
                print(f"{player.name}", end=" ")
            print()
