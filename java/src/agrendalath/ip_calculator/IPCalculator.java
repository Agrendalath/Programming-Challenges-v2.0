package agrendalath.ip_calculator;

import java.util.Scanner;

/*
 * Given an IPv4 address and a subnet mask, compute the network, broadcast, first/last host address
 * and number of available addresses.
 */

public class IPCalculator {
    private IP ip, subnetMask;

    IPCalculator(String ip, String subnetMask) {
        this.ip = new IP(ip);
        this.subnetMask = new IP(subnetMask);
    }

    private static String getUserInput(String input) {
        System.out.print("Input your " + input + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private String getNetworkAddress() {
        return IP.addressToIP(ip.getAddress() & subnetMask.getAddress());
    }

    private String getBroadcastAddress() {
        return IP.addressToIP(ip.getAddress() | ~subnetMask.getAddress());
    }

    private String getFirstAddress() {
        return IP.addressToIP(new IP(getNetworkAddress()).getAddress() + 1);
    }

    private String getLastAddress() {
        return IP.addressToIP(new IP(getBroadcastAddress()).getAddress() - 1);
    }

    private long getNumberOfAvailableAddresses() {
        long result = (1L << Long.numberOfTrailingZeros(subnetMask.getAddress())) - 2L;
        return result > 0 ? result : 1;
    }

    public static void main(String[] args) {
        IPCalculator calculator = new IPCalculator(getUserInput("IP"), getUserInput("Subnet Mask"));
        System.out.println("Network address: " + calculator.getNetworkAddress() + '\n' +
                "Broadcast address: " + calculator.getBroadcastAddress() + '\n' +
                "First available address: " + calculator.getFirstAddress() + '\n' +
                "Last available address: " + calculator.getLastAddress() + '\n' +
                "Number of available addresses: " + calculator.getNumberOfAvailableAddresses() + '\n');
    }
}
