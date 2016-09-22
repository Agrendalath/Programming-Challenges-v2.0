package agrendalath.ip_calculator;

/* Stores an IPv4 address as long number. */

class IP {
    private long address;

    IP(String ip) {
        String octets[] = ip.split("\\.");
        if (octets.length != 4)
            throw new IllegalArgumentException("IP address must consist of exactly 4 octets.");
        address = validate(octets, false);
    }

    <T extends Number> IP(T[] ip) {
        address = validate(ip, true);
    }

    private static <T> long validate(T[] ip, boolean isArrayOfNumbers) {
        long result = 0;
        int multiplier = 32;
        for (T anIp : ip) {
            multiplier -= 8;
            long value;
            if (isArrayOfNumbers)
                value = ((Number) anIp).longValue();
            else
                value = Short.parseShort((String) anIp);
            if (value < 0)
                throw new IllegalArgumentException("Octet cannot have negative value.");
            if (value > 255)
                throw new IllegalArgumentException("One octet cannot have more than 1 byte.");
            result += (value << multiplier);
        }
        return result;
    }

    long getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return addressToIP(address);
    }

    static String addressToIP(Long address) {
        StringBuilder sb = new StringBuilder();
        for (int i = 24; i >= 0; i -= 8) {
            sb.append((address >> i) & 255);
            sb.append(".");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
