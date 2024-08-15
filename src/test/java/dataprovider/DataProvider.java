package dataprovider;

import models.Appointment;

public class DataProvider {

  public static Appointment getAppointmentData(String date, String fecility,
      String healthProgram, String isReadmission, String comment) {
    Appointment appointment = new Appointment();
    appointment.setVisitDate(date);
    appointment.setComment(comment);
    appointment.setFecilityName(fecility);
    appointment.setReadmission(Boolean.getBoolean(isReadmission));
    appointment.setHealthProgram(healthProgram);
    return appointment;
  }

}
