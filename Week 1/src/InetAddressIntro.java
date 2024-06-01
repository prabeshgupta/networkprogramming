import java.io.IOException;
import java.net.*;

public class InetAddressIntro {

    public static void main(String[] args) {
        try {
            // Local Host
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address.toString());
            System.out.println(address.getCanonicalHostName());
            System.out.println(address.getHostAddress());
            System.out.println(address.getClass());
            System.out.println(address.isReachable(100));

            // Global Host
            InetAddress address1 = InetAddress.getByName("facebook.com");
            System.out.println(address1.toString());
            System.out.println(address1.getCanonicalHostName());
            System.out.println(address1.getHostAddress());
            System.out.println(address1.getClass());
            System.out.println(address1.isReachable(10000));

            // Multiple IP address
            InetAddress address2[] = InetAddress.getAllByName("www.google.com");

            for (int i = 0; i < address2.length; i++) {
                System.out.println("Address " + (i + 1) + ": " + address2[i].toString());
            }

            // Ip to Hostname
            byte[] ip = new byte[] { (byte) 142, (byte) 250, (byte) 77, (byte) 228 };
            InetAddress fqdn = InetAddress.getByAddress(ip);
            System.out.println(fqdn.getHostName());

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}