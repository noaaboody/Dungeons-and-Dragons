package com.company.Test;
import com.company.Tiles.Position;
import com.company.Tiles.Empty;
import com.company.Tiles.Tile;
import com.company.Tiles.Unit;
import com.company.Tiles.Wall;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UnitTests {
    public Unit unit;
    public Tile empty;
    public Tile wall;
    public Tile monster;
    public Tile trap;
    public Position p1;
    public Position p2;
    public Position p3;


    @Before
    public void beforeAny(){
        //unit = new Warrior();
        p1 = unit.getPosition();
        p2 = empty.getPosition();
        p3 = empty.getPosition();
        empty = new Empty(p2);
        wall = new Wall(p3);
        //monster = new Monster();
        //trap = new Trap();
    }


    @Test
    public void interactPlayerEmpty (){
        unit.interact(empty);
        assertEquals(unit.getPosition(),p1);
    }

    @Test
    public void interactPlayerWall (){
        unit.interact(wall);
        assertEquals(unit.getPosition(),p1);
    }

    @Test
    public void interactPlayerTrap (){

    }

    @Test
    public void interactPlayerMonster (){

    }

    @Test
    public void battlePlayerMonster (){

    }

    @Test
    public void battlePlayerTarp (){

    }



}
