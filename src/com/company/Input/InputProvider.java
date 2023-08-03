package com.company.Input;

import com.company.Callbacks.MessageCallback;
import com.company.Printer;
import com.company.TileFactory;
import com.company.Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class InputProvider implements InputQuery {
    private Printer printer;
    private MessageCallback messageCallback;

    public InputProvider (){
        this.printer = new Printer();
    }

    public Movement getInput (){
        Scanner first = new Scanner(System.in);
        String input1 = first.nextLine().toLowerCase(Locale.ROOT);
        List<String> b = new ArrayList<>();
        b.add("w");b.add("s");b.add("a");b.add("d");
        Movement m = null;
        if (stringTOMovement(input1) == null){
            while (!(b.contains(input1)) && (stringTOMovement(input1) == null)){
                Scanner input = new Scanner(System.in);
                input1 = input.nextLine().toLowerCase(Locale.ROOT);
                if (stringTOMovement(input1) != null)
                    return stringTOMovement(input1);
            }
        }
        return stringTOMovement(input1);
    }

    public Movement stringTOMovement (String input1){
        Movement m = null;
        switch (input1) {
            case "w":
                m = Movement.UP;
                break;
            case "a":
                m = Movement.LEFT;
                break;
            case "s":
                m = Movement.DOWN;
                break;
            case "d":
                m = Movement.RIGHT;
                break;
        }
        return m;
    }

    public int choosePlayer(TileFactory tileFactory){
        printer.print("Welcome to Dungeons and Dragons Game");
        tileFactory.showListPlayers();
        printer.print("please choose your player : ");
        Scanner input = new Scanner(System.in);
        int input1 = input.nextInt();
        while (!tileFactory.isValidChoice(input1)){//todo check if work ans not throe exception
            input = new Scanner(System.in);
            input1 = input.nextInt();
            printer.print("You chose illegal number please try again");

        }
        String playerMessage = String.format(("You chose %s"),tileFactory.listPlayers().get(input1).getName());
        printer.print(playerMessage);
        return input1;
    }
}
