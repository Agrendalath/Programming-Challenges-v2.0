package agrendalath.intersecting_rectangle;

import java.awt.*;
import java.util.Scanner;

/*
 * Check if two 2D rectangles overlap, and if they do, calculate the overlapping rectangle.
 */

public class IntersectingRectangle {

    private static long getIntersectingRectangle(Rectangle rectangle1, Rectangle rectangle2) {
        Rectangle intersecting = rectangle1.intersection(rectangle2);
        if (intersecting.width < 0 || intersecting.height < 0)
            return 0;

        return intersecting.width * intersecting.height;
    }

    private static int getUserInput(String valueToInput) {
        System.out.println("Input " + valueToInput + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        System.out.println(
                getIntersectingRectangle(
                        new Rectangle(
                                new Point(
                                        getUserInput("x for first rectangle top left corner"),
                                        getUserInput("y for first rectangle top left corner")
                                ),
                                new Dimension(
                                        getUserInput("first rectangle width"),
                                        getUserInput("first rectangle height")
                                )
                        ),
                        new Rectangle(
                                new Point(
                                        getUserInput("x for second rectangle top left corner"),
                                        getUserInput("y for second rectangle top left corner")),
                                new Dimension(
                                        getUserInput("second rectangle width"),
                                        getUserInput("second rectangle height")
                                )
                        )
                )
        );

    }
}
