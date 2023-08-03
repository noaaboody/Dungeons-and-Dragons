package com.company;

import com.company.Callbacks.LevelUpCallback;
import com.company.Callbacks.MessageCallback;
import com.company.Callbacks.PlayerDeathCallback;
import com.company.Tiles.Enemy;
import com.company.Tiles.Player;

import java.util.List;

public class LevelManager {
    private GameBoard board;
    private MessageCallback messageCallback;
    private List<Enemy> listOfEnemies;
    protected Printer printer;
    private int CurrentLevel;
    private LevelUpCallback levelUpCallback;
    private PlayerDeathCallback playerDeathCallback;
    private List<char[][]> listOfLevels;

    public LevelManager(List<char[][]> listOfLevels, Player player){
        this.CurrentLevel = 1;
        this.printer = new Printer();
        this.listOfLevels = listOfLevels;
        newLevel(player);
    }

    public void newLevel(Player player) {
        for (char[][] c : listOfLevels) {
            this.board = new GameBoard(c,player);
            this.listOfEnemies = board.getEnemyList();
            printer.print("\n");
            printer.print("You are in level :  " + CurrentLevel);
            printer.print("\n");
            Level level = new Level(c, listOfEnemies,board);
            level.LevelStart();
            if (player.alive()){
                levelUp();
            }
            else {
                playerDeathCallback.call(player);
            }
        }

        if (board.getPlayer().alive()){
            printer.print("You Won!!!");
        }
    }

    public void levelUp(){
        CurrentLevel++;
    }

}
