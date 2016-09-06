package com.myproject;

import java.applet.Applet;
        import java.awt.Graphics;
        import java.math.*;
        import java.util.Scanner;

public class Sudoku {

    int grid[][] = new int[17][17];
    int rowRef[][] = new int[17][17];
    int colRef[][] = new int[17][17];
    int probCount[][] = new int[17][17];
    int probRef[][][] = new int[17][17][17];
    int boxRef[][][] = new int[5][5][17];
    int order;
    int sqrtOrder;

    public void input() {

        // input order and grid for sudoku
        int row;
        int col;
        Scanner myVar = new Scanner(System.in);

        System.out.println("enter the order of sudoku");
        order = myVar.nextInt();

        System.out.println("enter the order sudoku grid,0 for blank spaces");
        for (row = 1; row <= order; row++) {

            for (col = 1; col <= order; col++) {

                grid[row][col] = myVar.nextInt();
            }
        }
    }

    public void initialize() {

        int row;
        int col;

        sqrtOrder = (int) Math.sqrt(order);

        for (row = 1; row <= order; row++) {

            for (col = 1; col <= order; col++) {

                if (grid[row][col] != 0) {

                    rowRef[grid[row][col]][row] = 1;
                    colRef[grid[row][col]][col] = 1;
                    boxRef[(row - 1) / sqrtOrder][(col - 1) / sqrtOrder][grid[row][col]] = 1;

                }
            }
        }
    }

    public void obviousValues() {

        int row;
        int col;
        int value;
        int count;
        int temps;

        temps = 0;

        for (row = 1; row <= order; row++) {

            for (col = 1; col <= order; col++) {

                if (grid[row][col] == 0) {

                    count = 0;
                    for (value = 1; value <= order; value++) {

                        if ((rowRef[value][row] == 0) && (colRef[value][col] == 0)
                                && (boxRef[(row - 1) / sqrtOrder][(col - 1) / sqrtOrder][value] == 0)) {

                            count++;
                            temps = value;
                        }

                    }

                    if (count == 1) {
                        grid[row][col] = temps;
                        rowRef[value][row] = 1;
                        colRef[value][col] = 1;
                        boxRef[(row - 1) / sqrtOrder][(col - 1) / sqrtOrder][temps] = 1;
                        obviousValues();
                    }

                }

            }
        }
    }

    public void print() {

        int row;
        int col;
        for (row = 1; row <= order; row++) {

            for (col = 1; col <= order; col++) {

                if(col%sqrtOrder==0) {
                    System.out.print(grid[row][col] + "   ");
                }
                else {
                    System.out.print(grid[row][col] + " ");
                }



            }
            if(row%sqrtOrder==0) {
                System.out.println("\n");
            }
            else {
                System.out.println();
            }

        }
        System.out.println();

    }

    public void probability() {
        int row;
        int col;
        int value;
        int count;
        int temps;

        temps = 0;

        for (row = 1; row <= order; row++) {

            for (col = 1; col <= order; col++) {

                if (grid[row][col] == 0) {

                    count = 0;
                    for (value = 1; value <= order; value++) {

                        if ((rowRef[value][row] == 0) && (colRef[value][col] == 0)
                                && (boxRef[(row - 1) / sqrtOrder][(col - 1) / sqrtOrder][value] == 0)) {

                            count++;
                            probRef[row][col][count] = value;

                        }
                    }

                    probCount[row][col] = count;
                }
            }
        }
    }

    public void sudokuFill(int row, int col) {

        int value;
        int i;

        if (grid[row][col] != 0) {

            if (row == order && col == order) {
                print();
                System.exit(0);
            } else if (col == order) {
                sudokuFill(row + 1, 1);
            } else {
                sudokuFill(row, col + 1);
            }
        }

        else {
            for (i = 1; i <= probCount[row][col]; i++) {

                value = probRef[row][col][i];

                if ((rowRef[value][row] == 0) && (colRef[value][col] == 0)
                        && (boxRef[(row - 1) / sqrtOrder][(col - 1) / sqrtOrder][value] == 0)) {

                    grid[row][col] = value;
                    rowRef[value][row] = 1;
                    colRef[value][col] = 1;
                    boxRef[(row - 1) / sqrtOrder][(col - 1) / sqrtOrder][probRef[row][col][i]] = 1;

                    if (row == order && col == order) {
                        print();
                        System.exit(0);
                    }

                    else if (col == order) {
                        sudokuFill(row + 1, 1);
                    }

                    else {
                        sudokuFill(row, col + 1);
                    }

                    grid[row][col] = 0;
                    rowRef[value][row] = 0;
                    colRef[value][col] = 0;
                    boxRef[(row - 1) / sqrtOrder][(col - 1) / sqrtOrder][value] = 0;

                }

            }
        }
    }
}