package com.company.Test;

import com.company.GameBoard;
import com.company.GameManager;
import com.company.Tiles.Empty;
import com.company.Tiles.Player;
import com.company.Tiles.Players.Warrior;
import com.company.Tiles.Position;
import com.company.Tiles.Wall;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import com.company.*;
import com.company.Input.InputProvider;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Scanner;


public class PlayerTest {
    private String path = "C:\\limud\\sim_B\\OOP\\HW3";
    //private File file = new file (path);
    //private String absolutePath = file.getAbsolutePath();
    //private Reader reader = new Reader(path);
    private GameBoard board;
    private Player warrior = new Warrior("The Hound", 400, 20, 6, 5);
    private Empty empty;
    private Wall wall;

    public void main(String[] args) throws IOException {
        String path = args[0];
        File f = new File(path);
        File[] matchingFile = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("txt");
            }
        });
        List<File> levelFiles = Arrays.asList(matchingFile);
        List <char[][]> gameBoardList = new ArrayList<>();
        for (File a:levelFiles) {
            List <String> arr = new ArrayList<>();
            Scanner reader = new Scanner(a);
            while (reader.hasNextLine())
                arr.add(reader.nextLine());
            gameBoardList.add(listToChar (arr));
        }
        board = new GameBoard(gameBoardList.get(1), warrior);
    }

    private static char[][] listToChar(List<String> lines) {
        int rowCounter = 0;
        char[][] charArr = new char[lines.size()][lines.get(0).length()];
        for (String s : lines) {
            for (int i = 0; i < s.length(); i++) {
                charArr[rowCounter][i] = s.charAt(i);
            }
            rowCounter++;
        }
        return charArr;
    }

    @BeforeEach
    void setUp(){
        Position p = new Position(4,7);
        warrior.initialize(p);
        Position p1 = new Position(3,3);
        empty.setPosition(p1);
        Position p2 = new Position(5,7);
        wall.setPosition(p2);
    }






}
