package agrendalath.rock_paper_scissors;

import java.util.*;

class RPS {
    private static final Map<FigureInterface, Set<? extends FigureInterface>> relations = new HashMap<>();

    RPS() {
        initialize();
    }

    Map<FigureInterface, Set<? extends FigureInterface>> getRelations() {
        return relations;
    }

    static FigureInterface<?> getFigure(String name) {
        return FigureInterface.getEnum(name, Figures.class);
    }

    void initialize() {
        relations.put(
                getFigure("Rock"),
                new HashSet<>(Collections.singletonList(getFigure("Scissors")))
        );
        relations.put(
                getFigure("Paper"),
                new HashSet<>(Collections.singletonList(getFigure("Rock")))
        );
        relations.put(
                getFigure("Scissors"),
                new HashSet<>(Collections.singletonList(getFigure("Paper")))
        );
    }

    /**
     * @param first  The first figure.
     * @param second The second figure.
     * @return Returns if the first figure beats the second one.
     */
    boolean fight(FigureInterface first, FigureInterface second) {
        return relations.get(first).contains(second);
    }
}
