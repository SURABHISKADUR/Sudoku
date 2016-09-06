package com.myproject;

/**
 * Created by Surabhi on 05/07/2016.
 */
import java.applet.Applet;
import java.awt.Graphics;
import java.math.*;
import java.util.Scanner;

public class Application  {

    public static void main(String args[ ]) {

        Sudoku sudoku = new Sudoku();
        sudoku.input();
        sudoku.initialize();
        sudoku.obviousValues();
        sudoku.probability();
        sudoku.sudokuFill(1, 1);
    }
}

