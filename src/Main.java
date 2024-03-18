public class Main {
    public static void main(String[] args) {

        /**
         * Initialize game.
         */
        Piskvorky.initGame();

        /**
         * The variable determines whether the game runs or not.
         */
        boolean hrajeme = true;

        System.out.println("Hra se ukonci vyherni sekvenci 4 stejnych znaku v rade nebo se ukonci CTRL-C ");

        /**
         * Main program loop.
         */
        while (hrajeme) {

            /**
             * Draw playing field.
             */
            Piskvorky.drawBoard();

            /**
             * Get move from currentplayer.
             */
            int[] move = Piskvorky.getMove();

            /**
             * Move validation and if move is valid its execution.
             */
            if (!Piskvorky.isValidMove(move[0], move[1])) {
                System.out.println("Tento tah neni platny!");
                continue;
            }
            Piskvorky.makeMove(move[0], move[1]);

            /**
             * Validation if last move leads to winning sequence.
             *
             * @return True if winning sequence otherwise false.
             */
            if (Piskvorky.evaluateGame()) {
                hrajeme = false;
                Piskvorky.drawBoard();
                System.out.println(" Vyhral hrac " + Piskvorky.currentPlayer);
            } else {
                /**
                 * Change currentplayer.
                 */
                Piskvorky.changePlayer();
            }
        }
    }
}