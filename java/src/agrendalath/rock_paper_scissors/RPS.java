package agrendalath.rock_paper_scissors;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

class RPS {
    private static final Map<FigureInterface, EnumSet<? extends FigureInterface>> relations = new HashMap<>();

    enum Figures implements FigureInterface {Rock, Paper, Scissors}

    RPS() {
        initialize();
    }

    Map<FigureInterface, EnumSet<? extends FigureInterface>> getRelations() {
        return relations;
    }

    void initialize() {
        relations.put(Figures.Rock, EnumSet.of(Figures.Scissors));
        relations.put(Figures.Paper, EnumSet.of(Figures.Rock));
        relations.put(Figures.Scissors, EnumSet.of(Figures.Paper));
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
