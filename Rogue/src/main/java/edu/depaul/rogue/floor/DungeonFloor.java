package edu.depaul.rogue.floor;

import java.util.Random;

public class DungeonFloor extends Floor {
    private static final Random random = new Random();

    public DungeonFloor(int width, int height) {
        super(width, height);
        generateFloor();
    }

    @Override
    protected void generateFloor() {
        // Fill grid with WALL tiles by default
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Tile(TileType.WALL);
            }
        }

        int startY = height / 2;  // middle row for the path

        // make sure all tiles in the row (except walls) are FLOOR
        for (int x = 1; x < width - 1; x++) {
            grid[startY][x] = new Tile(TileType.FLOOR);  // fill row with FLOOR
        }

        // set START and FINISH points
        grid[startY][1] = new Tile(TileType.START);
        grid[startY][width - 2] = new Tile(TileType.FINISH);
    }

    // debugging
    // NOTE: use print to avoid adding new line at the end, otherwise next output wont appear
    // on the same line
    public void printFloor() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (grid[y][x].getType()) {
                    case FLOOR -> System.out.print(".");
                    case WALL -> System.out.print("#");
                    case START -> System.out.print("S");
                    case FINISH -> System.out.print("F");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
