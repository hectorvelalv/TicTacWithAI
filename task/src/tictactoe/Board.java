package tictactoe;

import java.util.*;

public final class Board {
    private final char [][] board;
    private PlayerType playerX;
    private PlayerType playerO;
    public Board() {
        board = new char[][] {
                {' ',' ',' '},
                {' ',' ',' '},
                {' ',' ',' '}
        };
    }
    enum PlayerType {
        USER,
        EASY,
        MEDIUM,
        HARD
    }
    public boolean setPlayers(String p1, String p2) {
        switch (p1) {
            case "user" -> playerX = PlayerType.USER;
            case "easy" -> playerX = PlayerType.EASY;
            case "medium" -> playerX = PlayerType.MEDIUM;
            case "hard" -> playerX = PlayerType.HARD;
            default -> {
                System.out.println("Bad parameters!");
                return false;
            }
        }
        switch (p2) {
            case "user" -> playerO = PlayerType.USER;
            case "easy" -> playerO = PlayerType.EASY;
            case "medium" -> playerO = PlayerType.MEDIUM;
            case "hard" -> playerO = PlayerType.HARD;
            default -> {
                System.out.println("Bad parameters!");
                return false;
            }
        }
        return true;
    }
    public void nextMove() {
        char turn = getTurn();
        if(turn == 'X'){
            switch (this.playerX) {
                case USER -> userMove('X');
                case EASY -> easyMove('X');
                case MEDIUM -> mediumMove('X');
                case HARD -> bestMove('X');
            }
        }else {
            switch (this.playerO) {
                case USER -> userMove('O');
                case EASY -> easyMove('O');
                case MEDIUM -> mediumMove('O');
                case HARD -> bestMove('O');
            }
        }
    }
    private char getTurn() {
        int contX=0;
        int contO=0;
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    contX++;
                } else if (aChar == 'O') {
                    contO++;
                }
            }
        }
        if(contX > contO){
            return('O');
        }else {
            return ('X');
        }
    }
    private void userMove(char turn) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = true;
        int coord1;
        int coord2;
        do{
            System.out.print("Enter the coordinates: ");
            try{
                coord1 = scanner.nextInt();
                coord2 = scanner.nextInt();
                if (coord1 > 3 || coord2 > 3 || coord2 < 1 || coord1 < 1){
                    System.out.println("Coordinates should be from 1 to 3!");
                }else{
                    if(board[coord1-1][coord2-1] != ' '){
                        System.out.println("This cell is occupied! Choose another one!");
                    }else{
                        setTurn(coord1-1,coord2-1,turn);
                        validInput = false;
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }while (validInput);
    }
    private void easyMove(char turn) {
        Random random1 = new Random();
        int pos1 = random1.nextInt(3);
        int pos2 = random1.nextInt(3);

        while (board[pos1][pos2] != ' ' ){
            pos1 = random1.nextInt(3);
            pos2 = random1.nextInt(3);
        }
        System.out.println("Making move level "+'"'+"easy"+'"');
        setTurn(pos1,pos2,turn);
    }
    private void mediumMove(char turn) {
        System.out.println("Making move level "+'"'+"medium"+'"');
        if(!isMediumMove(turn)) {
            Random random1 = new Random();
            int pos1 = random1.nextInt(3);
            int pos2 = random1.nextInt(3);

            while (board[pos1][pos2] != ' ' ){
                pos1 = random1.nextInt(3);
                pos2 = random1.nextInt(3);
            }
            setTurn(pos1,pos2,turn);
        }
    }
    private boolean isMediumMove(char turn) {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][2] == ' '&& board[i][0] != ' '){
                setTurn(i,2,turn);
                return true;
            }
            if (board[i][1] == board[i][2] && board[i][0] == ' ' && board[i][1] != ' '){
                setTurn(i,0,turn);
                return true;
            }
            if (board[i][0] == board[i][2] && board[i][1] == ' ' && board[i][0] != ' '){
                setTurn(i,1,turn);
                return true;
            }
            if (board[0][i] == board[1][i] && board[2][i] == ' ' && board[0][i] != ' '){
                setTurn(2,i,turn);
                return true;
            }
            if (board[1][i] == board[2][i] && board[0][i] == ' ' && board[1][i] != ' '){
                setTurn(0,i,turn);
                return true;
            }
            if (board[0][i] == board[2][i] && board[1][i] == ' '&& board[0][i] != ' '){
                setTurn(1,i,turn);
                return true;
            }
            //diagonal izquierda -> derecha
            if (board[0][0] == board[1][1] && board[2][2] == ' '&& board[0][0] != ' '){
                setTurn(2,2,turn);
                return true;
            }
            if (board[0][0] == board[2][2] && board[1][1] == ' '&& board[0][0] != ' '){
                setTurn(1,1,turn);
                return true;
            }
            if (board[1][1] == board[2][2] && board[0][0] == ' '&& board[1][1] != ' '){
                setTurn(0,0,turn);
                return true;
            }
            //diagonal derecha -> izquierda
            if (board[0][2] == board[1][1] && board[2][0] == ' '&& board[0][2] != ' '){
                setTurn(2,0,turn);
                return true;
            }
            if (board[0][2] == board[2][0] && board[1][1] == ' '&& board[0][2] != ' '){
                setTurn(1,1,turn);
                return true;
            }
            if (board[1][1] == board[2][0] && board[0][2] == ' '&& board[1][1] != ' '){
                setTurn(0,2,turn);
                return true;
            }
        }
        return false;
    }
    public void printTable() {
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(j==0){
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
                if(j== board.length-1){
                    System.out.println("|");
                }
            }
        }
        System.out.println("---------");
    }
    public boolean isFinished() {
        char win = winner();
        if(win == 'O' || win=='X') {
            System.out.println(win+" wins");
            return true;
        } else if (boardIsFull(this.board)) {
            System.out.println("draw");
            return true;
        }
        return  false;
    }
    private char winner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2] &&
                    board[i][0] != '-') {
                return board[i][0];
            }
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] &&
                    board[1][j] == board[2][j] &&
                    board[0][j] != '-') {
                return board[0][j];
            }
        }
        if (board[0][0] == board[1][1] &&
                board[1][1] == board[2][2] &&
                board[0][0] != '-') {
            return board[0][0];
        }
        if (board[2][0] == board[1][1] &&
                board[1][1] == board[0][2] &&
                board[2][0] != '-') {
            return board[2][0];
        }
        return ' ';
    }
    private boolean boardIsFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    public void cleanTable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = ' ';
            }
        }
    }
    public void setTurn(int posx,int posy, char turn) {
        this.board[posx][posy] = turn;
    }

    public void bestMove(char turn){
        int bestScore = -123123;
        int[] move = {};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.board[i][j] == ' '){
                    this.board[i][j] = turn;
                    int score = minimax(this.board,0,false,turn);
                    this.board[i][j] = ' ';
                    if(score > bestScore){
                        bestScore = score;
                        move = new int[]{i, j};

                    }
                }
            }
        }
        System.out.println("Making move level   "+'"'+"hard"+'"');
        this.board[move[0]][move[1]] = turn;
    }

    public int scores(char turn){
        char res = winner();
        if(res == turn){
            return 1;
        }else if(res == ' '){
            return 0;
        }else {
            return -1;
        }
    }

    public int minimax(char[][] board, int depth, boolean isMax,char turn){
        boolean result = isMinimaxFinished();
        if(result){
            int score = scores(turn);
            return score;
        }
        if(isMax){
            int bestScore = -123123;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(this.board[i][j] == ' '){
                        board[i][j] = turn;
                        int score = minimax(board,depth+1,false,turn);
                        board[i][j] = ' ';
                        if(score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }else {
            int bestScore = 123123;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(this.board[i][j] == ' '){
                        board[i][j] = getTurn();
                        int score = minimax(board,depth+1,true,getTurn());
                        board[i][j] = ' ';
                        if(score < bestScore){
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public boolean isMinimaxFinished(){
        char win = winner();
        if(win == 'O' || win=='X') {
            return true;
        } else if (boardIsFull(this.board)) {
            return true;
        }
        return  false;
    }
    //hardMove
}




