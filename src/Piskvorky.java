public class Piskvorky {

    // Size of playing field
    private static final int SIZE = 10;

    // Player symbols
    private static final char PLAYER_ONE_SYMBOL = 'X';
    private static final char PLAYER_TWO_SYMBOL = 'O';

    // Playing field
    private static char[][] board;

    // Current player
    static char currentPlayer;

    // Last move row coordinate
    private static int lastmoverow;

    // Last move col coordinate
    private static int lastmovecol;

    // Number of some symbols in row, column, diagonal
    private static int kolikznakuzasebou = 0;

    /**

     Metod founds if four symbols are in order for current player.
     @param prohledavanejradek Row cordinate of searchd field.
     @param prohledavanejsloupec Col cordinate of searchd field.
     @return True if four symbols are in order for current player otherwise false.
     */
    private static boolean ctyriznakyZasebou(int prohledavanejradek, int prohledavanejsloupec) {
        if (kolikznakuzasebou == 0) {
            if (board[prohledavanejradek][prohledavanejsloupec] == currentPlayer) {
                kolikznakuzasebou = 1;
                return false;
            }
        }
        if (kolikznakuzasebou == 1) {
            if (board[prohledavanejradek][prohledavanejsloupec] == currentPlayer) {
                kolikznakuzasebou = 2;
                return false;
            } else {
                kolikznakuzasebou = 0;
            }
        }
        if (kolikznakuzasebou == 2) {
            if (board[prohledavanejradek][prohledavanejsloupec] == currentPlayer) {
                kolikznakuzasebou = 3;
                return false;
            } else {
                kolikznakuzasebou = 0;
            }
        }
        if (kolikznakuzasebou == 3) {
            if (board[prohledavanejradek][prohledavanejsloupec] == currentPlayer) {
                kolikznakuzasebou = 4;
                return true;
            } else {
                kolikznakuzasebou = 0;
            }
        }
        return false;
    }

    /**

     The method verifies whether a player has won.

     @return True if there was a win, otherwise false.
     */
    public static boolean evaluateGame() {
        boolean vyhra = false;

        // checking the entire row
        kolikznakuzasebou = 0;
        for (int i = 0; i < SIZE; i++) {
            if (ctyriznakyZasebou(lastmoverow, i)) {
                vyhra = true;
                return true;
            }
        }

        // checking the entire column
        kolikznakuzasebou = 0;
        for (int i = 0; i < SIZE; i++) {
            if (ctyriznakyZasebou(i, lastmovecol)) {
                vyhra = true;
                return true;
            }
        }

        // check diagonal 1
        int zdradek = lastmoverow;
        int zdsloupec = lastmovecol;
        int kdradek = lastmoverow;
        int kdsloupec = lastmovecol;
        if ((lastmovecol == 0 && lastmoverow == 0) || lastmovecol == 9 && lastmoverow == 9) {
        } else {
            boolean zacatekdiagonaly = true;

            while (zacatekdiagonaly) {
                zdradek = zdradek - 1;
                zdsloupec = zdsloupec + 1;
                if (zdradek < 0) {
                    zacatekdiagonaly = false;
                    zdradek = zdradek + 1;
                    zdsloupec = zdsloupec - 1;
                }
                if (zdsloupec > 9) {
                    zacatekdiagonaly = false;
                    zdsloupec = zdsloupec - 1;
                    zdradek = zdradek + 1;
                }
            }
            zacatekdiagonaly = true;
            while (zacatekdiagonaly) {
                kdradek = kdradek + 1;
                kdsloupec = kdsloupec - 1;
                if (kdradek > 9) {
                    zacatekdiagonaly = false;
                    kdradek = kdradek - 1;
                    kdsloupec = kdsloupec + 1;
                }
                if (kdsloupec < 0) {
                    zacatekdiagonaly = false;
                    kdsloupec = kdsloupec + 1;
                    kdradek = kdradek - 1;
                }
            }
        }

        // step
        int sradek = zdradek;
        int ssloupec = zdsloupec;
        kolikznakuzasebou = 0;
        for (int i = 0; i < (kdradek - zdradek) + 1; i++) {

            if (i != 0) {
                sradek = sradek + 1;
                ssloupec = ssloupec - 1;
            }

            if (ctyriznakyZasebou(sradek, ssloupec)) {
                vyhra = true;
                return true;
            }
        }
        zdradek = lastmoverow;
        zdsloupec = lastmovecol;
        kdradek = lastmoverow;
        kdsloupec = lastmovecol;

        // chech diagonal 2
        if ((lastmovecol == 0 && lastmoverow == 9) || lastmovecol == 9 && lastmoverow == 0) {
        } else {
            boolean zacatekdiagonaly = true;

            while (zacatekdiagonaly) {
                zdradek = zdradek - 1;
                zdsloupec = zdsloupec - 1;
                if (zdradek < 0) {
                    zacatekdiagonaly = false;
                    zdradek = zdradek + 1;
                    zdsloupec = zdsloupec + 1;
                }
                if (zdsloupec < 0) {
                    zacatekdiagonaly = false;
                    zdsloupec = zdsloupec + 1;
                    zdradek = zdradek + 1;
                }
            }
            zacatekdiagonaly = true;
            while (zacatekdiagonaly) {
                kdradek = kdradek + 1;
                kdsloupec = kdsloupec + 1;
                if (kdradek > 9) {
                    zacatekdiagonaly = false;
                    kdradek = kdradek - 1;
                    kdsloupec = kdsloupec - 1;
                }
                if (kdsloupec > 9) {
                    zacatekdiagonaly = false;
                    kdsloupec = kdsloupec - 1;
                    kdradek = kdradek - 1;
                }
            }
        }

        // step
        sradek = zdradek;
        ssloupec = zdsloupec;
        kolikznakuzasebou = 0;
        for (int i = 0; i < (kdradek - zdradek) + 1; i++) {

            if (i != 0) {
                sradek = sradek + 1;
                ssloupec = ssloupec + 1;
            }
            if (ctyriznakyZasebou(sradek, ssloupec)) {
                vyhra = true;
                return true;
            }
        }
        return vyhra;
    }

    /**

     Initializes game Piskvorky.

     Sets the playing field to empty, the current player is player number 1.
     */
    public static void initGame() {
        board = new char[SIZE][SIZE];
        currentPlayer = PLAYER_ONE_SYMBOL;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }
    /**

     Draws the playing field on the console.
     */
    public static void drawBoard() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    /**

     Changes the current player to another player.
     */
    public static void changePlayer() {
        if (currentPlayer == PLAYER_ONE_SYMBOL) {
            currentPlayer = PLAYER_TWO_SYMBOL;
        } else currentPlayer = PLAYER_ONE_SYMBOL;
    }
    /**

     Gets a turn from the console player.

     @return An array get the row and column of the specified move.
     */
    public static int[] getMove() {
        int row = Vstupnioperace.nacticelekladneCislo("Hráč " + currentPlayer + " zadej řádek (0-" + (SIZE - 1) + "): ", SIZE -1);
        int col = Vstupnioperace.nacticelekladneCislo("Hráč " + currentPlayer + " zadej sloupec (0-" + (SIZE - 1) + "): ", SIZE -1);
        return new int[]{row, col};
    }
    /**

     Validates the entered move.
     @param row A row of moves.
     @param col A col of moves.
     @return True if the move is valid, otherwise False.
     */
    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '-';
    }
    /**

     Makes a move on the playing field.
     @param row A row of moves.
     @param col A col of moves.
     */
    public static void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
        lastmoverow = row;
        lastmovecol = col;

    }
    //test
    //test2
    //test3
    //test
    //test2
}
