package agrendalath.ip_calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IPCalculatorTest {
    @Test
    public void testGettingNetworkAddress() {
        IPCalculator calculator = new IPCalculator("128.42.5.4", "255.255.248.0");
        assertEquals("128.42.0.0", calculator.getNetworkAddress());
    }

    @Test
    public void testGettingBroadcastAddress() {
        IPCalculator calculator = new IPCalculator("128.42.5.4", "255.255.248.0");
        assertEquals("128.42.7.255", calculator.getBroadcastAddress());
    }

    @Test
    public void testGettingFirstAddress() {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "255.255.192.0");
        assertEquals("192.168.0.1", calculator.getFirstAddress());
    }

    @Test
    public void testGettingLastAddress() {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "255.255.192.0");
        assertEquals("192.168.63.254", calculator.getLastAddress());
    }

    @Test
    public void testGettingNumberOfAvailableAddresses() {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "255.255.192.0");
        assertEquals(16382, calculator.getNumberOfAvailableAddresses());
    }

    @Test
    public void testGettingNumberOfAvailableAddressesForMaxSubnet() {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "255.255.255.255");
        assertEquals(1, calculator.getNumberOfAvailableAddresses());
    }

    @Test
    public void testGettingNumberOfAvailableAddressesForWholeAddressPool() {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "128.0.0.0");
        assertEquals(2147483646, calculator.getNumberOfAvailableAddresses());
    }
}
