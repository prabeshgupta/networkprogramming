import java.io.*;
import java.net.*;
import java.util.*;

public class URLClassExample {

    // Custom Authenticator class to handle authentication
    static class CustomAuthenticator extends Authenticator {
        String username, password;

        CustomAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            // Return the username and password for authentication
            return new PasswordAuthentication(username, password.toCharArray());
        }
    }

    // Custom ProxySelector class to handle proxy settings
    static class CustomProxySelector extends ProxySelector {

        @Override
        public List<Proxy> select(URI uri) {
            // Define a proxy with type HTTP and address proxy.example.com:80
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.example.com", 80));
            List<Proxy> proxies = new ArrayList<>();
            proxies.add(proxy);
            // Return the list of proxies
            return proxies;
        }

        @Override
        public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
            // Print a message if the proxy connection fails
            System.out.println("Proxy not found: " + ioe.toString());
        }
    }

    public static void main(String[] args) {
        String encoding = "UTF-8"; // Define the encoding type
        try {
            URI uri = new URI("https://www.example.com/"); // Initialize the base URI

            // Define the name and email parameters
            String name = "Ram Shrestha";
            String email = "ram.shrestha@example.com";

            // Encode the parameters to be URL-safe
            name = URLEncoder.encode(name, encoding);
            email = URLEncoder.encode(email, encoding);

            // Create the query path with the encoded parameters
            String path = "search?name=" + name + "&email=" + email;
            uri = uri.resolve(path); // Resolve the complete URI

            // Uncomment the line below to use the resolved URI as URL
            // URL url = uri.toURL();
            URL url = new URL("https://www.example.com/"); // Initialize URL object

            // Set the default Authenticator
            Authenticator.setDefault(new CustomAuthenticator("username", "password"));
            // Set the default ProxySelector
            ProxySelector.setDefault(new CustomProxySelector());

            // Open a connection to the URL and read the response
            BufferedReader buff = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = buff.readLine()) != null) {
                // Print each line of the response
                System.out.println(line);
            }

            // Close the BufferedReader
            buff.close();
        } catch (Exception e) {
            // Print any exceptions that occur
            System.out.println("Exception: " + e.toString());
        }
    }
}
