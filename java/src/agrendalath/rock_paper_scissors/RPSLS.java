package agrendalath.rock_paper_scissors;

import java.util.Arrays;
import java.util.HashSet;

class RPSLS extends RPS {
    @Override
    FigureInterface getFigure(String name) {
        return FigureInterface.getEnum(name, Figures.class, ExtendedFigures.class);
    }

    @Override
    FigureInterface[] getAllFigures() {
        return FigureInterface.getAllEnums(Figures.class, ExtendedFigures.class);
    }

    @Override
    void initialize() {
        addRelation(
                getFigure("Rock"),
                new HashSet<>(Arrays.asList(
                        getFigure("Scissors"),
                        getFigure("Lizard"))
                )
        );
        addRelation(
                getFigure("Paper"),
                new HashSet<>(Arrays.asList(
                        getFigure("Rock"),
                        getFigure("Spock"))
                )
        );
        addRelation(
                getFigure("Scissors"),
                new HashSet<>(Arrays.asList(
                        getFigure("Paper"),
                        getFigure("Lizard"))
                )
        );
        addRelation(
                getFigure("Lizard"),
                new HashSet<>(Arrays.asList(
                        getFigure("Paper"),
                        getFigure("Spock"))
                )
        );
        addRelation(
                getFigure("Spock"),
                new HashSet<>(Arrays.asList(
                        getFigure("Rock"),
                        getFigure("Scissors"))
                )
        );
    }
}
