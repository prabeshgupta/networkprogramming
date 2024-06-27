import java.net.*;
import java.util.List;

public class SetCookie {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        URL url = new URL("https://example.com");

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        // Set cookies
        HttpCookie cookie1 = new HttpCookie("name", "Prabesh");
        HttpCookie cookie2 = new HttpCookie("address", "Kathmandu");
        cookieManager.getCookieStore().add(url.toURI(), cookie1);
        cookieManager.getCookieStore().add(url.toURI(), cookie2);

        // Display cookies
        List<HttpCookie> cookies = cookieManager.getCookieStore().get(url.toURI());

        for (HttpCookie cookie : cookies) {
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }

        // Remove cookie
        cookieManager.getCookieStore().remove(url.toURI(), cookie2);

        cookies = cookieManager.getCookieStore().get(url.toURI());

        for (HttpCookie cookie : cookies) {
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }
    }
}
