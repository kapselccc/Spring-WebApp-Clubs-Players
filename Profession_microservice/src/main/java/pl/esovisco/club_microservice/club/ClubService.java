package pl.esovisco.club_microservice.club;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.esovisco.club_microservice.event.ClubEventRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
    private final ClubRepository repository;

    private final ClubEventRepository eventRepository;

    public ClubService(ClubRepository repository, ClubEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Club create(Club club){
        club = repository.save(club);
        eventRepository.create(club);
        return club;
    }

    public Optional<Club> find(long id){
        return repository.findById(id);
    }

    public List<Club> findAll(){
        return repository.findAll();
    }

    public void delete(Club club){
            repository.delete(club);
            eventRepository.delete(club);
    }

    public void update(Club club){
        repository.save(club);
    }
}
