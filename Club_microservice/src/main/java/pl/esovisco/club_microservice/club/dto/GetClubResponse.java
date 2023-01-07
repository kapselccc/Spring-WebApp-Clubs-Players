package pl.esovisco.club_microservice.club.dto;

import lombok.*;
import pl.esovisco.club_microservice.club.Club;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetClubResponse {
    private String name;

    private double money;

    private int league;

    public static GetClubResponse toClubResponse(Club club){
        return GetClubResponse.builder()
                .name(club.getName())
                .league(club.getLeague())
                .money(club.getMoney())
                .build();
    }
}
