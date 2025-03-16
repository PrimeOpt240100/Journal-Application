package net.engineeringdigest.journalApp.cron;

import lombok.Getter;
import lombok.Setter;
import net.engineeringdigest.journalApp.entity.JournalAppUrl;
import net.engineeringdigest.journalApp.repository.JournalAppUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class AppCache {

    @Autowired
    private JournalAppUrlRepository journalAppUrlRepository;

    private Map<String, String> appUrl;

    @PostConstruct
    public void init(){
        appUrl = new HashMap<>();
        List<JournalAppUrl> appUrlList = journalAppUrlRepository.findAll();

        if(!appUrlList.isEmpty()){
            for(JournalAppUrl journalAppUrl : appUrlList){
                appUrl.put(journalAppUrl.getKey(),journalAppUrl.getValue());
            }
        }
    }

    public String getUrlFromKey(String key){
        return appUrl.getOrDefault(key,null);
    }
}
