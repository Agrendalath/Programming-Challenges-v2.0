package agrendalath.bmi_calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class BMICalculatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testBMIException() throws Throwable {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Weight nor height cannot have negative value.");

        Method countBMI = BMICalculator.class.getDeclaredMethod("countBMI", double.class, double.class);
        countBMI.setAccessible(true);
        try {
            countBMI.invoke(BMICalculator.class, 100, -1);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    @Test
    public void testBMI() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method countBMI = BMICalculator.class.getDeclaredMethod("countBMI", double.class, double.class);
        countBMI.setAccessible(true);
        assertEquals(23.529411764705884, (double) countBMI.invoke(BMICalculator.class, 68, 1.70), 0.0001);
    }

    @Test
    public void testClassify() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method countBMI = BMICalculator.class.getDeclaredMethod("countBMI", double.class, double.class);
        Method classify = BMICalculator.class.getDeclaredMethod("classify", double.class);
        countBMI.setAccessible(true);
        classify.setAccessible(true);

        assertEquals("Normal (healthy weight)", classify.invoke(BMICalculator.class, countBMI.invoke(BMICalculator.class, 68, 1.70)));
    }

}
