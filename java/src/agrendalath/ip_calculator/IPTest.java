package agrendalath.ip_calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class IPTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testSetAddressWithRandomString() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("IP address must consist of exactly 4 octets.");

        new IP("random");
    }

    @Test
    public void testSetAddressWithIllegalIP() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("One octet cannot have more than 1 byte.");

        new IP("192.168.256.1");
    }

    @Test
    public void testWithAnotherIllegalIP() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Octet cannot have negative value.");

        new IP("192.168.255.-11");
    }

    @Test
    public void testReturnedIP() {
        String address = "192.168.1.1";

        IP ip = new IP(address);
        assertEquals(address, ip.toString());
    }

    @Test
    public void testInvalidIPFromArray() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("One octet cannot have more than 1 byte.");
        Short[] address = {192, 168, 256, 1};

        new IP(address);
    }

    @Test
    public void testAnotherInvalidIPFromArray() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Octet cannot have negative value.");
        Long[] address = {192L, 168L, 254L, -1L};

        new IP(address);
    }

    @Test
    public void testReturnedIPFromArray() {
        Integer[] address = {10, 0, 0, 1};

        IP ip = new IP(address);
        assertEquals("10.0.0.1", ip.toString());
    }
}
