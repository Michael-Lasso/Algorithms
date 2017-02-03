#!/usr/bin/python3
import sys, math, Queue

#---------------------------------------Data Structures---------------------------------------#
class Stack():
    
    def __init__(self):
        self.items = []
        
    def empty(self):
        return self.items == []
            
    def put(self, item):
        return self.items.append(item)
                        
    def get(self):
        return self.items.pop()
                                
    def getElements(self):
        return self.items

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


#-------------------------------------End Data Structures-------------------------------------#

#------------------------------------------Variables------------------------------------------#
root = Board(sys.argv[2].split(","))
algo = sys.argv[1]

up = Move("Up", -Board.dimension)
down = Move("Down", Board.dimension)
left = Move("Left", -1)
right = Move("Right", 1)
moves = [up, down, left, right]
#structures
q = Queue.Queue(0)
s = Stack()
explored = set()


#---------------------------------------End of Variables---------------------------------------#

#------------------------------------------Algorithms-----------------------------------------#

def basic(root, structure):
    l = root.getNeighbors(moves)
    for x in l:
        if not (x in explored):
            explored.add(tuple(x.blocks))
            structure.put(x)
    while not structure.empty():
        item = structure.get()
        print("board: ", item.blocks)
    print("solve for basic")
    return;

def ast(root, structure):
    return;

def ida(root, structure):
    return;

#--------------------------------------End ofAlgorithms---------------------------------------#

#--------------------------------------------Solver-------------------------------------------#

def solver(root, algorithm):
    if algorithm == "bfs":
        basic(root, s)
    elif algorithm == "dfs":
        basic(root, q)
    elif algorithm == "ast":
        print("solve for ast")
    elif algorithm == "ida":
        print("solve for ida")
    else:
        print("wrong algorithm")

def writeResultToFile(solver):
    return;

def isGoal():
    n = (Board.dimension * Board.dimension)
    list = []
    for x in range(n):
        list.append(x)
    return list;

#----------------------------------------End of Solver----------------------------------------#

#------------------------------------------Execution------------------------------------------#


#This would create first object of Employee class"
solver(root, algo)

print(sys.getsizeof(root))

goal = isGoal()
print("Goal: ", goal)
