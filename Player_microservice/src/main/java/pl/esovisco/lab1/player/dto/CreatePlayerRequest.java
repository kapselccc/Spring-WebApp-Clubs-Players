package pl.esovisco.lab1.player.dto;

import lombok.*;
import pl.esovisco.lab1.club.Club;
import pl.esovisco.lab1.player.Player;

import java.util.function.Function;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreatePlayerRequest {

    private String name;

    private Long club;

    private int age;

    /**
     *
     * @param request player from JSON
     * @param clubFunction function that takes club id and returns Club object
     * @return
     */
    public static Player toPlayer(CreatePlayerRequest request,Function<Long,Club> clubFunction){
        return Player.builder()
                .name(request.getName())
                .age(request.getAge())
                .club(clubFunction.apply(request.getClub()))
                .build();
    }
}
