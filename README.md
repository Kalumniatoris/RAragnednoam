# RAragnednoam

//TODO explain it better, or at least prettier. 

Turn based game on square board.


Each player starts on random square.

Goal is to obtain more power than opponents.
Only power that is in player's containers count for score.
Each player starts with container at their starting box, players may create additional containers during game.

Each player starts with (100) power on their bot. Bots have limited capacity (1000)

Players can spend power to build walls, generators and containers. 
Only power that is on bot can be spent to build, energy that is in containers is not available for this process.
Walls are (currently?) indestructable and impassable.
Generators (surprisingly) generates power. Energy from each generator is equal to 1 + amount of generators around it (up to 9 power units per turn for generator surrounded at each side)
Generators have limited capacity(100units) and need to be empty to avoid waste.
Generators are not protected from other players (anybody can extract from any generator they find).

Containers have unlimited capacity and are protected, only owner can extract power from one. 

BOTS:
bots need to extend class Player and override method run(...)

run(int id, int power, int x, int y, int[][] look )
parameters:
id: your id;
power: current power in bot;
x,y: current coordinates 
look[][] 2d array representing things detected by bot in radius of 5 with bot in middle, (look[x][y])
where:
0: Floor (aka nothing interesting there, you can move or build there)
1: Wall (bots are not allowed to enter that square)
2: ??????
3: Generator
4: Container

-1: NOTHING, AVOID AT ALL COST. (aka terrain outside map,)

Output:
One, and only one command in String, available commands:
movement, each move i box in given direction.
UP 
DOWN
LEFT
RIGHT

WAIT  (do nothing in this turn)
GENERATOR (places generator at current square, takes 10 power from bot)
WALL  (places wall at current square, takes 1 power from bot)
CONTAINER (places container at current square, takes 100 power from bot)
GET (transfer all available power from generator/container bot stands on, will not transfer more energy than bot have available space)
PUT (puts all energy from bot to container under bot)
