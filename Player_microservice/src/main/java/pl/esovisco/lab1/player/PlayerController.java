package pl.esovisco.lab1.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.esovisco.lab1.player.dto.CreatePlayerRequest;
import pl.esovisco.lab1.player.dto.GetPlayerResponse;
import pl.esovisco.lab1.player.dto.GetPlayersResponse;
import pl.esovisco.lab1.player.dto.UpdatePlayerRequest;
import pl.esovisco.lab1.club.ClubService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/players")
public class PlayerController {

    private final PlayerService playerService;

    private final ClubService clubService;

    @Autowired
    public PlayerController(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<GetPlayersResponse> getPlayers(){
        return ResponseEntity.ok(GetPlayersResponse.toPlayersResponse(playerService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPlayerResponse> getPlayer(@PathVariable long id){
        Optional<Player> player = playerService.find(id);
        return player.map(val -> ResponseEntity.ok(GetPlayerResponse.toPlayerResponse(val)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createPlayer(@RequestBody CreatePlayerRequest request){
        Player player = CreatePlayerRequest.toPlayer(request,(id) -> clubService.find(id).orElseThrow());
        player = playerService.create(player);
        return ResponseEntity.created(URI.create(String.format("players/%d",player.getId()))).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updatePlayer(@RequestBody UpdatePlayerRequest request, @PathVariable long id){
        Optional<Player> player = playerService.find(id);
        if(player.isPresent()){
            UpdatePlayerRequest.toPlayerUpdate(player.get(),request);
            playerService.update(player.get());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable long id){
        Optional<Player> player = playerService.find(id);
        if(player.isPresent()){
            playerService.delete(player.get());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
