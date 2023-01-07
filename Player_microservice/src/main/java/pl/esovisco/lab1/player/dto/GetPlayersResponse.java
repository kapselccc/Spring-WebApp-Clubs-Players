package pl.esovisco.lab1.player.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import pl.esovisco.lab1.player.Player;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetPlayersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class SimplePlayer{
        Long playerId;
        String name;
    }

    @Singular
    private List<SimplePlayer> players;

    public static GetPlayersResponse toPlayersResponse(List<Player> players){
        return GetPlayersResponse.builder()
                .players(players.stream()
                            .map(player -> SimplePlayer.builder()
                                            .playerId(player.getId())
                                            .name(player.getName())
                                            .build())
                            .collect(Collectors.toList()))
                            .build();
    }
}
