package pl.esovisco.club_microservice.club.dto;

import lombok.*;
import pl.esovisco.club_microservice.club.Club;
import pl.esovisco.club_microservice.club.Club;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetClubsResponse {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class SimpleClub{
        Long clubId;
        String name;
    }

    private List<SimpleClub> clubs;

    public static GetClubsResponse toClubsResponse(List<Club> clubs){
       return GetClubsResponse.builder()
               .clubs(clubs.stream()
                       .map(club -> SimpleClub.builder()
                               .clubId(club.getId())
                               .name(club.getName())
                               .build())
                       .collect(Collectors.toList()))
               .build();
    }
}
