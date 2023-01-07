package pl.esovisco.lab1.club;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.esovisco.lab1.player.Player;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "clubs")
public class Club {
    @Id
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
    @ToString.Exclude
    private List<Player> players;


}
