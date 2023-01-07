package pl.esovisco.lab1.player;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.esovisco.lab1.club.Club;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club")
    @ToString.Exclude
    private Club club;

    private int age;



}
