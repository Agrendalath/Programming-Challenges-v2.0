package agrendalath.rock_paper_scissors;

import java.util.Arrays;

/**
 * Interface needed for extending RPS to RPSLS
 */
interface FigureInterface<T extends Enum<T>> {
    @SafeVarargs
    static FigureInterface<?> getEnum(String name, Class<? extends FigureInterface<?>>... figureTypes) {
        for (Class<? extends FigureInterface<?>> figureClass : Arrays.asList(figureTypes)) {
            for (FigureInterface<?> figure : figureClass.getEnumConstants()) {
                if (name.toUpperCase().equals(figure.toString().toUpperCase()))
                    return figure;
            }
        }
        return null;
    }
}
