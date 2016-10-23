package agrendalath.rock_paper_scissors;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RPS game;
        System.out.print("Would you like to play extended version ('Rock, Paper, Scissors, Lizard, Spock')? (y/N): ");
        char chosenGame = scanner.next().toUpperCase().charAt(0);
        if (chosenGame == 'Y')
            game = new RPSLS();
        else
            game = new RPS();

        System.out.println("Choose your destiny:");
        for (int i = 0; i < game.getAllFigures().length; ++i) {
            System.out.println(i + ". " + game.getAllFigures()[i]);
        }
        int chosenFigure = scanner.nextInt();
        if (chosenFigure < 0 || chosenFigure >= game.getAllFigures().length)
            throw new IllegalArgumentException("Wrong choice");

        FigureInterface player = game.getAllFigures()[chosenFigure];
        Random random = new Random();
        int opponentsChoice = random.nextInt(game.getAllFigures().length);
        FigureInterface opponent = game.getAllFigures()[opponentsChoice];
        System.out.println(player + " vs " + opponent);
        System.out.print("You ");
        switch (game.fight(player, opponent)) {
            case -1:
                System.out.println("lose.");
                break;
            case 0:
                System.out.println("draw.");
                break;
            case 1:
                System.out.println("win.");
                break;
            default:
                throw new IllegalStateException();
        }
    }
}
