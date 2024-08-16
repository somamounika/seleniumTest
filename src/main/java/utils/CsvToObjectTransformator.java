package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Candidate;

public class CsvToObjectTransformator {

  public static List<Candidate> getCandidatesFromCSV() {
    File directory = new File(WebDriverFactory.downloadFilepath);
    File[] files = directory.listFiles((dir, name) -> name.endsWith(".csv"));
    if (files == null || files.length == 0) {
      System.out.println("No CSV files found in the directory.");
      return new ArrayList<>();
    }
    File file = files[0]; // Get the first CSV file
    String fileName = file.getPath();
    String line;
    List<Candidate> candidates = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        Candidate candidate = new Candidate();
        candidate.setFirstName(data[0].replace("\"", ""));
        candidate.setLastName(data[1].replace("\"", ""));
        candidate.setGender(data[2].replace("\"", ""));
        candidate.setAge(Integer.parseInt(data[3].replace("\"", "")));
        candidate.setOccupation(data[4].replace("\"", ""));
        candidate.setExperience(Integer.parseInt(data[5].replace("\"", "")));
        candidates.add(candidate);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(candidates);
    return candidates;
  }
}
