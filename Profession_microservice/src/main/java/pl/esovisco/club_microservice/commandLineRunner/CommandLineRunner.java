package pl.esovisco.club_microservice.commandLineRunner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.esovisco.club_microservice.club.Club;
import pl.esovisco.club_microservice.club.ClubService;
import pl.esovisco.club_microservice.club.ClubService;

import java.util.*;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final ClubService clubService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public CommandLineRunner(ClubService clubService) {
        this.clubService = clubService;
    }

    private final String[] commands = new String[]{"commands","quit","print_clubs","print_all"
            , "add_club", "delete_club"};

    private void print_commands(){
        System.out.println("Available commands:");
        for(String c : commands){
            System.out.println(c);
        }
    }


    private void add_club(){
        System.out.println("Type name:");
        String name = scanner.nextLine();

        System.out.println("Type money (double):");
        double money;
        try{
            money =  Double.parseDouble(scanner.nextLine());
        }
        catch (NumberFormatException e){
                System.out.println("Input is not a double");
                return;
        }

        System.out.println("Type league (int):");
        int league;
        try{
            league =Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("Input is not an integer");
            return;
        }

        Club p = Club.builder().name(name).money(money).league(league).build();
        clubService.create(p);
        System.out.println("Added successfully");
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
            }

            if(command.equals("add_club")){
                add_club();
                continue;
            }

            if(command.equals("delete_club")){
                delete_club();
            }
        }

    }


}
