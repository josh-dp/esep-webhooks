import java.util.Scanner;

        public class ConnectFour {

            //Prints the current board
            public static void printBoard(char[][] array)
            {
                //Flips board, row 0 is 'bottom' row
                for (int i = array.length - 1; i >= 0; i--)
                {
                    for (int j = 0; j < array[0].length; j++)
                    {
                        //Checks if element is '-' or a chip, prints it
                        if (array[i][j] == '-')
                        {
                            System.out.print("-");
                        }
                        else
                        {
                            System.out.print(array[i][j]);
                        }
                    }
                    System.out.println();
                }
            }

            //Sets user-chosen element to chip char, returns row in which chip is in
            public static int insertChip(char[][] array, int col, char chipType)
            {
                int row = 0;
                for (int i = 0; i < array.length; i++)
                {
                    //Ensures chip is placed above previous chip or at bottom of board
                    if (array[i][col] == '-')
                    {
                        row = i;
                        break;
                    }
                }
                array[row][col] = chipType;
                return row;
            }

            //Initializes initial board
            public static void initializeBoard(char[][] array)
            {
                for (int i = 0; i < array.length; i++)
                {
                    for (int j = 0; j < array[0].length; j++)
                    {
                        array[i][j] = '-';
                    }
                }
            }

            //Checks if one of the users hit four in a row
            public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
            {
                int numInRowHorizontal = 1;
                int numInRowVertical = 1;
                boolean isWinner = false;
                //Checks for horizontal four in a row
                for (int i = 0; i < array[0].length - 1; i++)
                {
                    if (array[row][i] == chipType && array[row][i+1] == chipType)
                    {
                        numInRowHorizontal++;
                    }
                    else
                    {
                        numInRowHorizontal = 1;
                    }
                    if (numInRowHorizontal == 4)
                    {
                        isWinner = true;
                        break;
                    }
                }
                //Checks for vertical four in a row
                for (int j = 0; j < array.length - 1; j++)
                {
                    if (array[j][col] == chipType && array[j+1][col] == chipType)
                    {
                        numInRowVertical++;
                    }
                    else
                    {
                        numInRowVertical = 1;
                    }
                    if (numInRowHorizontal == 4 || numInRowVertical == 4)
                    {
                        isWinner = true;
                        break;
                    }
                }
                return isWinner;
            }

            public static void main(String[] args)
            {
                //Calls for user to create initial board, displays chip types for players
                Scanner input = new Scanner(System.in);
                System.out.print("What would you like the height of the board to be? ");
                int boardHeight = input.nextInt();
                System.out.print("What would you like the length of the board to be? ");
                int boardLength = input.nextInt();
                char[][] board = new char[boardHeight][boardLength];
                initializeBoard(board);
                printBoard(board);
                System.out.println();
                System.out.print("Player 1: x\n");
                System.out.println("Player 2: o");
                System.out.println();

                boolean isWinner = false;
                char chip;
                int column;
                int row;
                //spacesUsed helps keep track for tie
                int spacesUsed = 0;
                while (!isWinner)
                {
                    System.out.print("Player 1: Which column would you like to choose? ");
                    chip = 'x';
                    column = input.nextInt();
                    row = insertChip(board, column, chip);
                    printBoard(board);
                    System.out.println();
                    //Checks if user one has won
                    isWinner = checkIfWinner(board, column, row, chip);
                    if (isWinner)
                    {
                        System.out.println("Player 1 won the game!");
                        System.exit(0);
                    }
                    //Checks if user one's move results in a full board and tie
                    spacesUsed++;
                    if (spacesUsed == boardLength * boardHeight)
                    {
                        System.out.println("Draw. Nobody wins.");
                        System.exit(0);
                    }
                    System.out.print("Player 2: Which column would you like to choose? ");
                    chip = 'o';
                    column = input.nextInt();
                    row = insertChip(board, column, chip);
                    printBoard(board);
                    System.out.println();
                    //Checks if user two has won
                    isWinner = checkIfWinner(board, column, row, chip);
                    if (isWinner)
                    {
                        System.out.println("Player 2 won the game!");
                        System.exit(0);
                    }
                    spacesUsed++;
                    //Checks if user two's move results in a full board or tie
                    if (spacesUsed == boardLength * boardHeight)
                    {
                        System.out.println("Draw. Nobody wins.");
                        System.exit(0);
                    }
                }
            }
        }
    
