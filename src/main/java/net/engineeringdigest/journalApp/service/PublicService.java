package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.response.JournalApplicationApiResponse;

public interface PublicService {

    JournalApplicationApiResponse getWeatherReport(String city);

}
