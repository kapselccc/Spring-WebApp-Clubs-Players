package pl.esovisco.club_microservice.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pl.esovisco.club_microservice.club.dto.CreateClubRequest;
import pl.esovisco.club_microservice.club.dto.GetClubResponse;
import pl.esovisco.club_microservice.club.dto.GetClubsResponse;
import pl.esovisco.club_microservice.club.dto.UpdateClubRequest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clubs")
public class ClubController {
    private final ClubService clubService;



    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;

    }

    @PostMapping
    public ResponseEntity<Void> createClub(@RequestBody CreateClubRequest request){
        Club club = CreateClubRequest.toClub(request);
        club = clubService.create(club);
        return ResponseEntity.created(URI.create(String.format("clubs/%d",club.getId()))).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<GetClubResponse> getClub(@PathVariable long id){
        Optional<Club> club = clubService.find(id);
        return club.map(value -> ResponseEntity.ok(GetClubResponse.toClubResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<GetClubsResponse> getClubs(){
        return ResponseEntity.ok(GetClubsResponse.toClubsResponse(clubService.findAll()));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateClub(@RequestBody UpdateClubRequest request, @PathVariable long id){
        Optional<Club> club = clubService.find(id);
        if(club.isPresent()){
            UpdateClubRequest.updateClub(club.get(),request);
            clubService.update(club.get());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable long id){
        Optional<Club> club = clubService.find(id);
        if(club.isPresent()){

            clubService.delete(club.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
