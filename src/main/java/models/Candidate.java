package models;

public class Candidate {
  private String firstName;
  private String lastName;
  private String gender;
  private int age;
  private String occupation;
  private int experience;


  // Getters and Setters
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  @Override
  public String toString() {
    return "Candidate{"
        + "firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", gender='"
        + gender
        + '\''
        + ", age="
        + age
        + ", occupation='"
        + occupation
        + '\''
        + ", experience="
        + experience
        + '}';
  }
}
