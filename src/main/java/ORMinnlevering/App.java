package ORMinnlevering;

import ORMinnlevering.Database.Connection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ORMinnlevering.dto.City;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    Scanner sc = new Scanner(System.in);

    public void main() throws Exception {
        System.out.println();
        System.out.println("Welcome to the world database controller!");
        printInstructions();
        boolean quit = false;

        while(!quit){
            quit = runApp();
        }
        System.out.println("Quiting program");
    }

    public boolean runApp(){
        System.out.println("What command do you want to execute: ");

        String command = sc.nextLine();
        return runCommand(command);
    }

    public boolean runCommand(String command){

        switch(command.toLowerCase().replace(" ", "")){
            case "info":
                printInstructions();
                break;
            case "add":
                break;
            case "remove":
                break;
            case "print":
                break;
            case "search":
                break;
            case "quit":
                return true;
            default:
                System.out.println("Illegal command");
        }

        return false;
    }

    public void printInstructions(){
        String line = String.format("%-25S", "------------------------------------------");
        System.out.println(line);
        System.out.println(String.format("%-25S", "Option commands"));
        System.out.println(line);
        System.out.println(String.format("%-10s %-15s", "info", "Prints the command page"));
        System.out.println(String.format("%-10s %-15s", "add", "Adds a entru to a give table"));
        System.out.println(String.format("%-10s %-15s", "remove", "Removes a entry from a table"));
        System.out.println(String.format("%-10s %-15s", "print", "Prints content from a table"));
        System.out.println(String.format("%-10s %-15s", "search", "Search for content in program"));
        System.out.println(String.format("%-10s %-15s", "quit", "Quits the program"));
        System.out.println(line);
    }

    public static void main( String[] args ) throws Exception {
        new App().main();
    }
}
