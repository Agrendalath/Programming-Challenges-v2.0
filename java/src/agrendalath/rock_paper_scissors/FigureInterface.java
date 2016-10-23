package agrendalath.rock_paper_scissors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Interface needed for extending RPS to RPSLS
 */
interface FigureInterface {
    @SafeVarargs
    static FigureInterface getEnum(String name, Class<? extends FigureInterface>... figureTypes) {
        for (Class<? extends FigureInterface> figureClass : Arrays.asList(figureTypes)) {
            for (FigureInterface figure : figureClass.getEnumConstants()) {
                if (name.toUpperCase().equals(figure.toString().toUpperCase()))
                    return figure;
            }
        }
        return null;
    }

    @SafeVarargs
    static FigureInterface[] getAllEnums(Class<? extends FigureInterface>... figureTypes) {
        List<FigureInterface> figureList = new ArrayList<>();

        for (Class<? extends FigureInterface> figureClass : Arrays.asList(figureTypes)) {
            Collections.addAll(figureList, figureClass.getEnumConstants());
        }
        return figureList.toArray(new FigureInterface[figureList.size()]);
    }
}
