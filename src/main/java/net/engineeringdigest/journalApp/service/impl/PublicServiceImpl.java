package net.engineeringdigest.journalApp.service.impl;

import net.engineeringdigest.journalApp.constants.JournalApplicationConstants;
import net.engineeringdigest.journalApp.constants.JournalApplicationUrlConstants;
import net.engineeringdigest.journalApp.model.response.weather_response.WeatherResponse;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PublicServiceImpl implements PublicService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JournalApplicationApiResponse getWeatherReport(String city) {

        WeatherResponse weatherReport = null;

        String finalUrl = JournalApplicationUrlConstants.WEATHER_URL;

        finalUrl = finalUrl.replace("CITY",city).replace("APPKEY",JournalApplicationUrlConstants.WEATHER_API_APP_KEY);

        try {
            ResponseEntity<WeatherResponse> response =
                    restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);

            weatherReport = response.getBody();
        } catch(HttpClientErrorException e){
            return JournalApplicationApiResponse.builder()
                    .code(JournalApplicationConstants.FAILURE)
                    .msg(e.getMessage())
                    .data(null)
                    .build();
        }

        return JournalApplicationApiResponse.builder()
                .code(JournalApplicationConstants.SUCCESS)
                .msg(JournalApplicationConstants.SUCCESS_MSG)
                .data(weatherReport)
                .build();
    }
}
