package pl.esovisco.lab1.player;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.esovisco.lab1.player.dto.GetPlayerResponse;
import pl.esovisco.lab1.player.dto.GetPlayersResponse;
import pl.esovisco.lab1.club.Club;
import pl.esovisco.lab1.club.ClubService;

import java.util.Optional;

@RestController
@RequestMapping("/clubs")
public class ClubPlayerController {

    private final PlayerService playerService;

    private final ClubService clubService;

    public ClubPlayerController(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @GetMapping("{id}/players")
    public ResponseEntity<GetPlayersResponse> getClubPlayers(@PathVariable long id){
        Optional<Club> club = clubService.find(id);

        return club.map(value -> ResponseEntity.ok(
                GetPlayersResponse.toPlayersResponse(value.getPlayers())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
