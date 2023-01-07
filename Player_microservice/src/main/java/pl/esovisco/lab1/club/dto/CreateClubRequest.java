package pl.esovisco.lab1.club.dto;

import lombok.*;
import pl.esovisco.lab1.player.dto.CreatePlayerRequest;
import pl.esovisco.lab1.club.Club;

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

    public static Club toClub(CreateClubRequest request){
        return Club.builder()
                .id(request.getId())
                .build();
    }


}
