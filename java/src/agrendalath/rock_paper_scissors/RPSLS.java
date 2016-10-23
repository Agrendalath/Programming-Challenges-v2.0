package agrendalath.rock_paper_scissors;

import java.util.EnumSet;
import java.util.Map;

class RPSLS extends RPS {
    enum Figures implements FigureInterface {Rock, Paper, Scissors, Lizard, Spock}

    @Override
    void initialize() {
        Map<FigureInterface, EnumSet<? extends FigureInterface>> relations = getRelations();
        relations.put(Figures.Rock, EnumSet.of(Figures.Scissors, Figures.Lizard));
        relations.put(Figures.Paper, EnumSet.of(Figures.Rock, Figures.Spock));
        relations.put(Figures.Scissors, EnumSet.of(Figures.Paper, Figures.Lizard));
        relations.put(Figures.Lizard, EnumSet.of(Figures.Paper, Figures.Spock));
        relations.put(Figures.Spock, EnumSet.of(Figures.Rock, Figures.Scissors));
    }
}
