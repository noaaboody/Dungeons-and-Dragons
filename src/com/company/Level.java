package com.company;

import com.company.Callbacks.EnemyDeathCallback;
import com.company.Callbacks.LevelUpCallback;
import com.company.Tiles.Enemy;
import com.company.Tiles.Player;

import java.util.List;

public class Level {
    protected Printer printer;
    protected List<Enemy> listOfEnemies;
    protected GameBoard board;
    private LevelUpCallback levelUpCallback;
    private EnemyDeathCallback enemyDeathCallback;

    public Level(char [][] c,List<Enemy> listOfEnemies, GameBoard board){
        this.listOfEnemies = listOfEnemies;
        this.board = board;
        this.printer = new Printer();
    }


    public void LevelStart() {
        Player p = board.getPlayer();
        while(p.alive() && board.getEnemyList().size()>0)
        {
            printLevel();
            gameTick();
        }
    }

    public void gameTick(){
        board.getPlayer().Tick(board.getPlayer(), board);

        Enemy enemyToRemove = null;
        for (Enemy e:listOfEnemies) {
            if (!e.alive()){
                enemyToRemove = e;
            }
                //e.setDeathCallback(this.enemyDeathCallback);
//                listOfEnemies.remove(e);
//                if (board.getEnemyList().contains(e)){
//                    board.getEnemyList().remove(e);
            else {
                e.Tick(board.getPlayer(), board);
            }
        }
        listOfEnemies.remove(enemyToRemove);
        if (board.getEnemyList().contains(enemyToRemove)) {
            board.getEnemyList().remove(enemyToRemove);
        }

    }

    public void printLevel() {
        printer.print(board.toString());//print the level
        printer.print(board.getPlayer().describe());//print the description of the player
    }
}
