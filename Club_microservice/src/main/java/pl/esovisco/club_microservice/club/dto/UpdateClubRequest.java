package pl.esovisco.club_microservice.club.dto;

import lombok.*;
import pl.esovisco.club_microservice.club.Club;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateClubRequest {
    private String name;

    private double money;

    private int league;

    public static Club updateClub(Club club, UpdateClubRequest request){
        club.setName(request.getName());
        club.setLeague(request.getLeague());
        club.setMoney(request.money);
        return club;
    }
}
