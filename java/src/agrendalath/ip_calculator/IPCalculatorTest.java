package agrendalath.ip_calculator;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class IPCalculatorTest {
    @Test
    public void testGettingNetworkAddress() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IPCalculator calculator = new IPCalculator("128.42.5.4", "255.255.248.0");
        Method getNetworkAddress = IPCalculator.class.getDeclaredMethod("getNetworkAddress");
        getNetworkAddress.setAccessible(true);
        assertEquals("128.42.0.0", getNetworkAddress.invoke(calculator));
    }

    @Test
    public void testGettingBroadcastAddress() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IPCalculator calculator = new IPCalculator("128.42.5.4", "255.255.248.0");
        Method getBroadcastAddress = IPCalculator.class.getDeclaredMethod("getBroadcastAddress");
        getBroadcastAddress.setAccessible(true);
        assertEquals("128.42.7.255", getBroadcastAddress.invoke(calculator));
    }

    @Test
    public void testGettingFirstAddress() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "255.255.192.0");
        Method getFirstAddress = IPCalculator.class.getDeclaredMethod("getFirstAddress");
        getFirstAddress.setAccessible(true);
        assertEquals("192.168.0.1", getFirstAddress.invoke(calculator));
    }

    @Test
    public void testGettingLastAddress() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "255.255.192.0");
        Method getLastAddress = IPCalculator.class.getDeclaredMethod("getLastAddress");
        getLastAddress.setAccessible(true);
        assertEquals("192.168.63.254", getLastAddress.invoke(calculator));
    }

    @Test
    public void testGettingNumberOfAvailableAddresses() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "255.255.192.0");
        Method getNumberOfAvailableAddresses = IPCalculator.class.getDeclaredMethod("getNumberOfAvailableAddresses");
        getNumberOfAvailableAddresses.setAccessible(true);
        assertEquals(16382L, getNumberOfAvailableAddresses.invoke(calculator));
    }

    @Test
    public void testGettingNumberOfAvailableAddressesForMaxSubnet() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "255.255.255.255");
        Method getNumberOfAvailableAddresses = IPCalculator.class.getDeclaredMethod("getNumberOfAvailableAddresses");
        getNumberOfAvailableAddresses.setAccessible(true);
        assertEquals(1L, getNumberOfAvailableAddresses.invoke(calculator));
    }

    @Test
    public void testGettingNumberOfAvailableAddressesForWholeAddressPool() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IPCalculator calculator = new IPCalculator("192.168.1.123", "128.0.0.0");
        Method getNumberOfAvailableAddresses = IPCalculator.class.getDeclaredMethod("getNumberOfAvailableAddresses");
        getNumberOfAvailableAddresses.setAccessible(true);
        assertEquals(2147483646L, getNumberOfAvailableAddresses.invoke(calculator));
    }
}
