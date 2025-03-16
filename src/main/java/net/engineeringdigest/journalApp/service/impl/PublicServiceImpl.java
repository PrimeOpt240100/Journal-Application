package net.engineeringdigest.journalApp.service.impl;

import net.engineeringdigest.journalApp.constants.JournalApplicationConstants;
import net.engineeringdigest.journalApp.cron.AppCache;
import net.engineeringdigest.journalApp.model.response.weather_response.WeatherResponse;
import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;
import net.engineeringdigest.journalApp.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PublicServiceImpl implements PublicService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private AppCache appCache;

    @Value("${weather.api.key}")
    private String appKey;

    @Override
    public JournalApplicationApiResponse getWeatherReport(String city) {

        WeatherResponse weatherReport = null;

        String finalUrl = appCache.getUrlFromKey("WEATHER_API");

        finalUrl = finalUrl.replace("<city>",city).replace("<appkey>",appKey);

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
