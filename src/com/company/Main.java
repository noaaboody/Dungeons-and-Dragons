package com.company;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import com.company.*;
import com.company.Input.InputProvider;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws IOException {
            String path = args[0];
            File f = new File(path);
            File[] matchingFile = f.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith("txt");
                }
            });
            List <File>levelFiles = Arrays.asList(matchingFile);
            List <char[][]> gameBoardList = new ArrayList<>();
            for (File a:levelFiles) {
                List <String> arr = new ArrayList<>();
                Scanner reader = new Scanner(a);
                while (reader.hasNextLine())
                    arr.add(reader.nextLine());
                gameBoardList.add(listToChar(arr));
            }
            GameManager gameManager = new GameManager();
            gameManager.start(gameBoardList);
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
}







