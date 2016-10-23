package agrendalath.rock_paper_scissors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class RPSLS extends RPS {
    static FigureInterface<?> getFigure(String name) {
        return FigureInterface.getEnum(name, Figures.class, ExtendedFigures.class);
    }

    @Override
    void initialize() {
        Map<FigureInterface, Set<? extends FigureInterface>> relations = getRelations();
        relations.put(
                getFigure("Rock"),
                new HashSet<>(Arrays.asList(
                        getFigure("Scissors"),
                        getFigure("Lizard"))
                )
        );
        relations.put(
                getFigure("Paper"),
                new HashSet<>(Arrays.asList(
                        getFigure("Rock"),
                        getFigure("Spock"))
                )
        );
        relations.put(
                getFigure("Scissors"),
                new HashSet<>(Arrays.asList(
                        getFigure("Paper"),
                        getFigure("Lizard"))
                )
        );
        relations.put(
                getFigure("Lizard"),
                new HashSet<>(Arrays.asList(
                        getFigure("Paper"),
                        getFigure("Spock"))
                )
        );
        relations.put(
                getFigure("Spock"),
                new HashSet<>(Arrays.asList(
                        getFigure("Rock"),
                        getFigure("Scissors"))
                )
        );
    }
}
