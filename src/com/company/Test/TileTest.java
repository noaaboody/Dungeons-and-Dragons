package com.company.Test;
import com.company.Tiles.Position;
import com.company.Tiles.Tile;
import com.company.Tiles.Players.Warrior;
import org.junit.Before;
import org.junit.Test;

public class TileTest {

    public Tile t1;
    public Tile t2;
    public Position p1;
    public Position p2;
    public Position p3;


    @Before
    public void beforeAny() {
        p1 = new Position(0, 0);
        t1 = new Warrior("10", 10, 10, 10, 10);
        t1.setPosition(p1);
    }


    @Test
    public void Move() {

    }


    @Test
    public void interactPlayerWall() {

    }

    @Test
    public void interactPlayerTrap() {

    }
}