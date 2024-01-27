package fr.ancyracademy.esportclash.spring.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;

public class FormattableException {
  private String message;

  private LocalDateTime date;

  public FormattableException(String message, LocalDateTime date) {
    this.message = message;
    this.date = date;
  }

  public HashMap<String, String> toBody() {
    var map = new HashMap<String, String>();
    map.put("message", message);
    map.put("timestamp", date.toString());

    return map;
  }
}
