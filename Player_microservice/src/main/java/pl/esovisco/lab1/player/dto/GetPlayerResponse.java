package pl.esovisco.lab1.player.dto;

import lombok.*;
import pl.esovisco.lab1.player.Player;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetPlayerResponse {

    private String name;

    private Long club;

    private int age;

    public static GetPlayerResponse toPlayerResponse(Player player){
        return GetPlayerResponse.builder().name(player.getName())
                .club(player.getClub().getId())
                .age(player.getAge())
                .build();
    }
}
