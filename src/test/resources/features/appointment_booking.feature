Feature: Book appointment on CURA Healthcare Service

  Scenario Outline: Successfully book an appointment and verify it in history
    Given the user is on the CURA Healthcare Service homepage
    When the user books an appointment for a future date "<AppointmentDate>" "<HealthFecility>" "<healthProgram>" <isReadmission> "<Comment>"
    Then the appointment should be listed with correct details in the history
    When the user logs out from the application
    Examples:
      | AppointmentDate | HealthFecility                  | healthProgram | isReadmission | Comment |
      | 10/10/2024      | Hongkong CURA Healthcare Center | Medicaid      | false         | test    |
      | 10/11/2024      | Seoul CURA Healthcare Center    | Medicare      | true          | test    |
