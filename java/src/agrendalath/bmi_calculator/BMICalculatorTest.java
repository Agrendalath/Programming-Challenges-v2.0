package agrendalath.bmi_calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BMICalculatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void BMIException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Weight nor height cannot have negative value.");

        BMICalculator.BMI(100, -1);
    }

    @Test
    public void BMI() {
        assertEquals(23.529411764705884, BMICalculator.BMI(68, 1.70), 0.0001);
    }

    @Test
    public void classify() {
        assertEquals("Normal (healthy weight)", BMICalculator.classify(BMICalculator.BMI(68, 1.70)));
    }

}
