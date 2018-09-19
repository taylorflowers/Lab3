import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class WebScraper {

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }
    public static void main(String[] unused){
        System.out.println(urlToString("https://www.bls.gov/tus/charts/chart9.txt"));
        System.out.println(uniqueWordCount("https://www.bls.gov/tus/charts/chart9.txt"));
    }
    public static int wordCount(final String url) {
        int count = 0;
        String words = urlToString(url);
        for (int i = 0; i < words.length(); i++) {
            if (words.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }
    public static int wordCount(final String url, String wordToSearch) {
        int count = 0;
        wordToSearch.toLowerCase();
        String words = urlToString(url).toLowerCase();
        String[] arrWords = words.split(" ");
        for (String test : arrWords) {
            if (test.equals(wordToSearch)) {
                count++;
            }
        }
        return count;
    }
    public static int uniqueWordCount(final String url) {
        int count = 0;
        String words = urlToString(url).toLowerCase();
        String[] arrWords = words.split(" ");
        String prevWords = "";
        for (String test : arrWords) {
            test = test.toLowerCase();
            if (!(prevWords.contains(" " + test + " "))) {
                System.out.println(test);
                count++;
                prevWords = prevWords + " " + test + " ";
            }
        }
        return count;
    }
}
