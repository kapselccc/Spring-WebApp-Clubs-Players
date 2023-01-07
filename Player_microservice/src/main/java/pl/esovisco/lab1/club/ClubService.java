package pl.esovisco.lab1.club;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
    private final ClubRepository repository;

    @Autowired
    public ClubService(ClubRepository repository) {
        this.repository = repository;
    }

    public Club create(Club club){
        return repository.save(club);
    }

    public Optional<Club> find(long id){
        return repository.findById(id);
    }


    public List<Club> findAll(){
        return repository.findAll();
    }

    public void delete(Club club){
            repository.delete(club);
    }

    public void update(Club club){
        repository.save(club);
    }
}
