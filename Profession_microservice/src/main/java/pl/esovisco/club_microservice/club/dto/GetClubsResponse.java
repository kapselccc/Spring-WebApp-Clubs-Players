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
    private List<String> clubs;

    public static GetClubsResponse toClubsResponse(List<Club> clubs){
       return GetClubsResponse.builder()
               .clubs(clubs.stream()
                       .map(Club::getName)
                       .collect(Collectors.toList()))
               .build();
    }
}
