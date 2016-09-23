package agrendalath.bmi_calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BMICalculatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testBMIException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Weight nor height cannot have negative value.");

        BMICalculator.countBMI(100, -1);
    }

    @Test
    public void testBMI() {
        assertEquals(23.529411764705884, BMICalculator.countBMI(68, 1.70), 0.0001);
    }

    @Test
    public void testClassify() {
        assertEquals("Normal (healthy weight)", BMICalculator.classify(BMICalculator.countBMI(68, 1.70)));
    }

}
