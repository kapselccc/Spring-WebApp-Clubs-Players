package pl.esovisco.club_microservice.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.esovisco.club_microservice.club.Club;

@Repository
public class ClubEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public ClubEventRepository(@Value("${lab1.characters.url}") String baseUrl) {
        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void create(Club club){
        restTemplate.postForLocation("/clubs", CreateClubRequest.toDto(club));}

    public void delete(Club club){restTemplate.delete("/clubs/{id}",club.getId());}
}
