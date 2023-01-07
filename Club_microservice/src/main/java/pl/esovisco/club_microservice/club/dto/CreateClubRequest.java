package pl.esovisco.club_microservice.club.dto;

import lombok.*;
import pl.esovisco.club_microservice.club.Club;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateClubRequest {
    private String name;

    private double money;

    private int league;

    public static Club toClub(CreateClubRequest request){
        return Club.builder()
                .name(request.getName())
                .league(request.getLeague())
                .money(request.getMoney())
                .build();
    }
}
