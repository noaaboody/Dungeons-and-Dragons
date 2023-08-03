package com.company;

import com.company.Input.InputProvider;
import com.company.Tiles.Player;

import java.util.List;

import static java.nio.file.Files.readAllLines;

public class GameManager {
    private TileFactory tileFactory;
    private InputProvider inputProvider;
    private Player chosenPlayer;

    public GameManager(){
        this.tileFactory = new TileFactory();
        this.inputProvider = new InputProvider();
    }

    //the first func of the game
    public void start(List<char[][]> listOfLevels){
        int playerNumber = inputProvider.choosePlayer(tileFactory); //the func present the list of the players and the user pick one.
        Player chosen = tileFactory.producePlayer(playerNumber);
        this.chosenPlayer = chosen;
        LevelManager LevelManager = new LevelManager(listOfLevels, chosenPlayer);

        }
    }

