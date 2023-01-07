package pl.esovisco.lab1.commandLineRunner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.esovisco.lab1.player.Player;
import pl.esovisco.lab1.player.PlayerRepository;
import pl.esovisco.lab1.player.PlayerService;
import pl.esovisco.lab1.club.Club;
import pl.esovisco.lab1.club.ClubService;

import java.util.*;

public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final PlayerService playerService;
    private final ClubService clubService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public CommandLineRunner(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }

    private final String[] commands = new String[]{"commands","quit","print_players","print_clubs","print_all",
            "add_player", "add_club", "delete_player", "delete_club"};

    private void print_commands(){
        System.out.println("Available commands:");
        for(String c : commands){
            System.out.println(c);
        }
    }

    private void add_player(){
        System.out.println("Type name:");
        String name = scanner.nextLine();
        System.out.println("Type age (int):");
        int age,id;
        Club club;
        try {
            age = Integer.parseInt(scanner.nextLine());
            System.out.println("Type club ID (long):");
            id = Integer.parseInt(scanner.nextLine());
            club = clubService.find(id).orElseThrow();

        }catch (Exception e){
            if(e instanceof NumberFormatException){
                System.out.println("Input is not an integer!");
            }
            else{
                System.out.println("There's no Club with that ID");
            }
            return;
        }
        Player ch = Player.builder().name(name).age(age).club(club).build();
        playerService.create(ch);
        System.out.println("Added successfully");
    }

    private void add_club(){
        System.out.println("Type ID:");
        Long id;
        try{
            id =  Long.parseLong(scanner.nextLine());
        }
        catch (NumberFormatException e){
                System.out.println("Input is not a long");
                return;
        }

        Club p = Club.builder().id(id).build();
        clubService.create(p);
        System.out.println("Added successfully");
    }

    private void delete_player(){
        System.out.println("Type Player ID:");
        long id;
        Player player;
        try{
            id = Integer.parseInt(scanner.nextLine());
            player = playerService.find(id).orElseThrow();

        }
        catch (Exception e){
            if(e instanceof NumberFormatException){
                System.out.println("Input is not a long");
            }
            if(e instanceof NoSuchElementException){
                System.out.println("No player with such ID");
            }
            return;
        }
        playerService.delete(player);
        System.out.println("Deleted successfully");
    }

    private void delete_club() {

        System.out.println("Type club ID:");
        long id;
        Club club;

        try{
            id = Integer.parseInt(scanner.nextLine());
            club = clubService.find(id).orElseThrow();
        }
        catch (Exception e){
            if(e instanceof NumberFormatException){
                System.out.println("Input is not a long");
            }
            if(e instanceof NoSuchElementException){
                System.out.println("No Club with such ID");
            }
            return;
        }

        clubService.delete(club);
        System.out.println("Deleted successfully");
    }

    @Override
    public void run(String... args){
        String command;
        print_commands();
        while( true ){

            command = scanner.nextLine();

            if(command.equals("quit")){
                break;
            }

            if(command.equals("commands")){
                print_commands();
            }

            if(command.equals("print_players")){
                List<Player> players = playerService.findAll();
                for(Player player : players){
                    System.out.println(player);
                }
            }

            if(command.equals("print_clubs")){
                List<Club> clubs = clubService.findAll();
                for(Club club : clubs){
                    System.out.println(club);
                }
            }

            if(command.equals("print_all")){
                List<Club> clubs = clubService.findAll();
                for(Club club : clubs){
                    System.out.println(club);
                }
                List<Player> players = playerService.findAll();
                for(Player player : players){
                    System.out.println(player);
                }
            }

            if(command.equals("add_player")){
                add_player();
                continue;
            }

            if(command.equals("add_club")){
                add_club();
                continue;
            }

            if(command.equals("delete_player")){
                delete_player();
                continue;
            }

            if(command.equals("delete_club")){
                delete_club();
            }
        }

    }


}
