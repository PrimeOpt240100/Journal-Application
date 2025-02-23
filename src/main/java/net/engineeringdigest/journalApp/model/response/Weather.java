package net.engineeringdigest.journalApp.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private String id;
    private String main;
    private String description;
    private String icon;
}
