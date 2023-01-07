package pl.esovisco.lab1.datastore;

import org.springframework.stereotype.Component;
import pl.esovisco.lab1.club.Club;
import pl.esovisco.lab1.player.Player;

import java.util.*;


@Component
public class DataStore {

    private Set<Club> clubs = new HashSet<>();

    private Set<Player> players = new HashSet<>();


    public List<Club> findAllClubs(){return new ArrayList<>(clubs);}

    public List<Player> findAllPlayers(){return new ArrayList<>(players);}


    public void createClub(Club club) {
        club.setId(findAllClubs().stream().mapToLong(Club::getId).max().orElse(0) + 1);
        clubs.add(club);
    }

    public void createPlayer(Player player) {
        player.setId(findAllPlayers().stream().mapToLong(Player::getId).max().orElse(0) + 1);
        players.add(player);
    }

    public Optional<Club> findClubById(long id){
        return clubs.stream().filter(club -> club.getId() == id ).findFirst();
    }

    public Optional<Player> findPlayerById(long id){
        return players.stream().filter(player -> player.getId() == id ).findFirst();
    }

    public void deletePlayer(long id)throws IllegalArgumentException{
        findPlayerById(id).ifPresentOrElse(
                original -> players.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The player with id \"%d\" does not exist", id));
                });
    }

    public void deleteClub(long id)throws IllegalArgumentException{
        findClubById(id).ifPresentOrElse(
                original -> clubs.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The club with id \"%d\" does not exist", id));
                });
    }
}
