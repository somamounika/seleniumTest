package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestExtractor {

  public static String getExtractedTexr(String text, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    if (matcher.find()) {
      return matcher.group(1);
    } else {
      System.out.println("No match found.");
    }
    return null;
  }
}
