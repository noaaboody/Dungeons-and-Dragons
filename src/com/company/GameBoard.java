package com.company;


import com.company.Callbacks.EnemyDeathCallback;
import com.company.Callbacks.MessageCallback;
import com.company.Tiles.*;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<Tile> tiles;
    private Tile[][] tilesMat;
    private Player player;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Wall> wallList = new ArrayList<Wall>();
    private TileFactory tileFactory;
    private EnemyDeathCallback enemyDeathCallback;
    private MessageCallback messageCallback;
    private int rowsize;
    private int columnSize;
    private Printer printer;

    public GameBoard(char[][] c , Player player){
        this.player = player;
        this.columnSize=c.length;
        this.rowsize = c[0].length;
        this.tileFactory = new TileFactory();
        this.tilesMat = new Tile[columnSize][rowsize];
        this.printer = new Printer();
        boardInit(c);
    }

    public List<Tile> boardInit(char[][] c){
        tiles = new ArrayList<>();
        for (int i = 0 ; i < c.length ; i++ ) {
            for (int j = 0; j < c[i].length; j++) {
                Position p = new Position(j, i);
                if (c[i][j] == '#') {
                    Wall wall = tileFactory.produceWall(p);
                    tiles.add(wall);
                    tilesMat[i][j]=wall;
                    wallList.add(wall);
                }
                else if (c[i][j] == '.') {
                    tiles.add(tileFactory.produceEmpty(p));
                    tilesMat[i][j]=tileFactory.produceEmpty(p);
                }
                else if (c[i][j] == '@') {
                    if (player == null) {
                        player.setPosition(p);
                        player.setEnemyListCallBack(()->enemyList);
                    } else {
                        player.setPosition(p);
                        //messageCallback.send(player.describe());//todo how to init this message call back??
                        printer.print(player.describe());
                        tiles.add(player);
                        tilesMat[i][j]=player;
                    }
                }
                else {
                    Enemy enemy = tileFactory.produceEnemy(c[i][j], p);
                    enemyList.add(enemy);
                    //enemyDeathCallback.call(enemy);//todo how to init this message call back??
                    //messageCallback.send(enemy.describe());//todo how to init this message call back??
                    //printer.print(enemy.describe());//todo check in his game if this is there
                    tiles.add(enemy);
                    tilesMat[i][j]=enemy;
                }
            }
        }
        return tiles;
    }

    public void updateMat(){
        for (int i = 0; i<tilesMat.length; i++){
            for(int j = 0; j<tilesMat[0].length; j++){
                Position p = new Position(j,i);
                tilesMat[i][j] = tileFactory.produceEmpty(p);
            }
        }
        tilesMat[player.getPosition().getY()][player.getPosition().getX()] = player;
        for (Enemy e: enemyList) {
            tilesMat[e.getPosition().getY()][e.getPosition().getX()] = e;
        }
        for (Wall wall:wallList) {
            tilesMat[wall.getPosition().getY()][wall.getPosition().getX()] = wall;
        }
    }

    public Tile getTile (Position position){
        return tilesMat[position.getY()][position.getX()];
    }

    public List<Enemy> getEnemyList(){
        return enemyList;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }

    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public String toString() {
        updateMat();
        String row = "";
        String board = "";
        for (int i = 0; i<tilesMat.length ; i++){
            for (int j = 0 ; j<tilesMat[0].length ; j++){
                row += tilesMat[i][j].toString();
                if(j==rowsize-1)
                    row+="\n";
            }
            board += row;
            row = "";
        }
        return board;
    }
}