package net.engineeringdigest.journalApp.model.response.weather_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {
    private String type;
    private String id;
    private String country;
    private String sunrise;
    private String sunset;
}
