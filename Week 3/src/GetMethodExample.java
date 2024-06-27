import java.net.*;
import java.io.*;

public class GetMethodExample {
    public static void main(String[] args) throws IOException {

        URL url = new URL("https://httpbin.org/get?id=1234");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Accept", "application/json");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        bufferedReader.close();
    }
}
