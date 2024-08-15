package models;


public class Appointment {

  String fecilityName;
  boolean isReadmission;
  String healthProgram;
  String visitDate;
  String comment;

  public String getFecilityName() {
    return fecilityName;
  }

  public void setFecilityName(String fecilityName) {
    this.fecilityName = fecilityName;
  }

  public boolean isReadmission() {
    return isReadmission;
  }

  public void setReadmission(boolean readmission) {
    isReadmission = readmission;
  }

  public String getHealthProgram() {
    return healthProgram;
  }

  public void setHealthProgram(String healthProgram) {
    this.healthProgram = healthProgram;
  }

  public String getVisitDate() {
    return visitDate;
  }

  public void setVisitDate(String visitDate) {
    this.visitDate = visitDate;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
