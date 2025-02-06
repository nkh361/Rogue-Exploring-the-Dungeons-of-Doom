package edu.depaul.rogue.floor;

public abstract class Floor {
    protected int width, height;
    protected Tile[][] grid;

    public Floor(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Tile[height][width];
    }

    /**
     * Abstract method will be implemented by a sublclass, it'll define how the floor layout is generated.
     */
    protected abstract void generateFloor();

    /**
     * Returns the tile at a specified position.
     * @param x     X-coordinate of the tile
     * @param y     Y-coordinate of the tile
     * @return      The tile at the given coordinate
     */
    public Tile getTile(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[y][x];

        } else{
            return new Tile(TileType.WALL);

        }
    }

    /**
     * Prints the floor layout to the console.
     * This method iterates through the grid and displays the graphical representation
     * of each tile based on its type:
     * - FLOOR: represented by a dot (.)
     * - WALL: represented by a hash (#)
     * - START: represented by the letter 'S'
     * - FINISH: represented by the letter 'F'
     *
     * TODO: replace with rendering in JavaFX
     */
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
