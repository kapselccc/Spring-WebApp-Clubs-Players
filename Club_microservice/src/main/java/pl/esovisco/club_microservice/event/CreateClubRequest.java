package pl.esovisco.club_microservice.event;

import lombok.*;
import pl.esovisco.club_microservice.club.Club;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateClubRequest {

    private Long id;

    public static CreateClubRequest toDto(Club club){
        return CreateClubRequest.builder().id(club.getId()).build();
    }

}
