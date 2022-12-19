package tictactoe;
import java.util.*;
class Main {
    public static void main(String[] args) {
        Board board = new Board();

        boolean active= true;
        final Scanner scanner = new Scanner(System.in);
        while (active){
            System.out.print("Input command: ");
            final String commands = scanner.nextLine();
            final String[] command = commands.split(" ", 3);
            if(command.length != 3){
                if(command[0].equals("exit")){
                    active = false;
                }else{
                    System.out.println("Bad parameters!");
                }
            } else if(command[0].equals("start")){
                if(board.setPlayers(command[1],command[2])){
                    board.printTable();
                    while (true){
                        board.nextMove();
                        board.printTable();
                        if(board.isFinished()){
                            board.cleanTable();
                            break;
                        }
                    }
                }
            }else{
                System.out.println("Bad parameters!");
            }
        }

    }
}