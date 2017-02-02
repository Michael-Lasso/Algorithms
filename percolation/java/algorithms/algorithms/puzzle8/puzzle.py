#!/usr/bin/python3
import sys, math, Queue

class Move:
    'Common base class for moves'
    
    def __init__(self, move, n):
        self.move = move
        self.n = n
    
    def displayMove(self):
        print (self.move,  ", pos: ", self.n)

class Board:
    'Represents the state of a board'
    dimension = 0

    def __init__(self, blocks):
        self.blocks = blocks
        Board.dimension = int(math.sqrt(len(self.blocks)))

    def displayBoard(self):
            print ("matrix",self.blocks)

    def swap(self, x, y):
        tmp = self.blocks[x]
        self.blocks[x] = self.blocks[y+x]
        self.blocks[y+x] = tmp

    def findBlank(self):
        for x in range(len(self.blocks)):
            if self.blocks[x] == '0':
                return x;

    def isValidVerticalSwap(self, blank, move):
        if blank + move >= len(self.blocks) or blank + move <0:
            return False
        else:
            return True

    def isValidHorizontalSwap(self, blank, move):
        if move > 0:
            return (blank + move) % Board.dimension != 0;
        else:
            return blank % Board.dimension != 0;

    def move(self, move):
        neighbor = Board(list(self.blocks))
        blank = neighbor.findBlank()
        if move.move == "Up" or move.move == "Down":
            if neighbor.isValidVerticalSwap(blank, move.n):
                neighbor.swap(blank, move.n)
        else:
            if neighbor.isValidHorizontalSwap(blank, move.n):
                neighbor.swap(blank, move.n)
        return neighbor;

    def getNeighbors(self, moves):
        list = []
        for x in moves:
            board = self.move(x)
            if(board.blocks!=self.blocks):
                list.append(board)
        return list;

#Instantiates root board
root = Board(sys.argv[2].split(","))

#algorithm
algo = sys.argv[1]

#This would create first object of Employee class"
n = 3
up = Move("Up", -Board.dimension)
down = Move("Down", Board.dimension)
left = Move("Left", -1)
right = Move("Right", 1)

moves = [up, down, left, right]

q = Queue.Queue(0)

for x in moves:
    q.put(x)

while not q.empty():
    item = q.get()
#print("item: ", item.move, item.n)

#This would create first object of Employee class"
#root.move(up)
#root.displayBoard()
#root.move(down)
#root.displayBoard()
#root.move(right)
#root.displayBoard()
#root.move(left)
#root.displayBoard()

l = root.getNeighbors(moves)

for x in l:
    print x.displayBoard()




