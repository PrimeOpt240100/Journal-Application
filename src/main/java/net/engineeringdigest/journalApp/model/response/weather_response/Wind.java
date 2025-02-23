package net.engineeringdigest.journalApp.model.response.weather_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {
    private String speed;
    private String deg;
    private String gust;
}
