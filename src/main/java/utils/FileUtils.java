package utils;

import java.io.File;

public class FileUtils {
  public static void deleteAllFilesInTempDirectory() {
    File directory = new File(WebDriverFactory.downloadFilepath);
    if (directory.exists() && directory.isDirectory()) {
      File[] files = directory.listFiles();
      if (files != null) {
        for (File file : files) {
          if (file.isFile()) {
            if (file.delete()) {
              System.out.println("Deleted file: " + file.getName());
            } else {
              System.out.println("Failed to delete file: " + file.getName());
            }
          }
        }
      } else {
        System.out.println("The directory is empty or an I/O error occurred.");
      }
    } else {
      System.out.println("Directory does not exist or is not a directory.");
    }
  }
}
