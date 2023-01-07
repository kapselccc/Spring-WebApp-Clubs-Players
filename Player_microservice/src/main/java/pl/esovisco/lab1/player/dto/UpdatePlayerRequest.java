package pl.esovisco.lab1.player.dto;

import lombok.*;
import pl.esovisco.lab1.player.Player;
import pl.esovisco.lab1.club.Club;

import java.util.function.Function;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdatePlayerRequest {

    private String name;

    private int age;

    public static Player toPlayerUpdate(Player player, UpdatePlayerRequest request){
        player.setAge(request.getAge());
        player.setName(request.getName());
        return player;
    }
}
