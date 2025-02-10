package edu.depaul.rogue.floor;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import edu.depaul.rogue.EventManager;

public class DungeonFloor extends Floor {
	public Tile start;
	public Stairs finish;
	private EventManager eventManager;

    public DungeonFloor(int width, int height, EventManager eventManager) {
        super(width, height);
        this.eventManager = eventManager;
        generatePassableFloor();
    }

    /**
     * Creates a passable floor where there is a valid path connecting
     * the START and FINISH tiles. This method iteratively generates floors and
     * verifies their passability until a valid path is found or the
     * maximum number of attempts is reached.
     *
     * FIXME: sometimes even after generating the max attempts, there are unwalkable paths from S to F.
     *        Need to play around with the chance of floor in {@code generateFloor()} method.
     *
     * The passability of a floor is determined using the {@code isPathPossible()}
     * method. If a passable floor is not found after the defined maximum number
     * of attempts, the last generated layout remains.
     *
     * Implementation notes:
     * - The floor layout is generated using the {@code generateFloor()} method.
     * - The method applies a loop with a maximum number of attempts set to avoid
     * infinite retries in case of failed layouts.
     */
    public void generatePassableFloor() {
        boolean isPassable = false;
        int attempts = 0;
        int maxAttempts = 150;

        while (!isPassable && attempts++ < maxAttempts) {
            generateFloor();
            isPassable = isPathPossible();
//            if (!isPassable) {
//                System.out.println("failed to generate a passable floor after " + attempts + " attempts.");
//            }
        }
        

    }

    /**
     * Places the START and FINISH tiles on random walkable positions in the grid.
     * The START and FINISH tiles will not overlap, and both will be placed on tiles
     * of type FLOOR.
     *
     * @param random the {@code Random} instance used to generate random coordinates
     *               for the start and finish tiles
     */
    private void placeStartAndFinish(Random random) {
        int startX, startY, finishX, finishY;

        // place start on a random walkable tile
        do {
            startX = random.nextInt(width);
            startY = random.nextInt(height);
        } while (!grid[startY][startX].isWalkable());

        // place the finish on a different walkable tile
        do {
            finishX = random.nextInt(width);
            finishY = random.nextInt(height);
        } while ((finishX == startX && finishY == startY) || !grid[finishY][finishX].isWalkable());

        // set the start/finish tiles
        grid[startY][startX] = new Tile(startX, startY);
        this.start = grid[startY][startX];
        start.setType(TileType.START);
        grid[finishY][finishX] = new Stairs(finishX, finishY, eventManager);
        this.finish = (Stairs) grid[finishY][finishX];
        

    }

    int[] findTilePosition(TileType type) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x].getType() == type) {
                    return new int[] {x, y};
                }
            }
        }
        // if the tile is not found
        return null;
    }

    boolean isPathPossible() {
        int[] startPos = findTilePosition(TileType.START);
        int[] finishPos = findTilePosition(TileType.FINISH); 

        if (startPos == null || finishPos == null) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        return dfs(startPos[0], startPos[1], finishPos[0], finishPos[1], visited); 
    }

    /**
     * Performs a depth-first search (DFS) to determine if there is a valid path
     * from the current position `(x, y)` to the target position `(finishX, finishY)`
     * on the grid, avoiding revisiting already explored positions stored in the
     * `visited` set.
     *
     * @param x                 The current x-coordinate on the grid.
     * @param y                 The current y-coordinate on the grid.
     * @param finishX           The x-coordinate of the target position.
     * @param finishY           The y-coordinate of the target position.
     * @param visited           A set of visited positions represented as strings in the format "x,y".
     * @return                  True if a valid path exists from the current position to the target position,
     *                          false otherwise.
     */
    private boolean dfs(int x, int y, int finishX, int finishY, Set<String> visited) {
        if (x == finishX && y == finishY) { 
            return true;
        }

        // add the current position to visited set
        visited.add(x + "," + y);

        // explore all four directions
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            // if the new position is within bounds and walkable, return true
            if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                Tile tile = grid[newY][newX];
                if (tile.isWalkable() && !visited.contains(newX + "," + newY)) {
                    if (dfs(newX, newY, finishX, finishY, visited)) {
                        return true;
                    }
                }
            }
        }
        // if no path is found return false
        return false;
    }

    /**
     * Generates a random floor layout for the dungeon. The floor is initialized
     * with tiles that are predominantly walkable FLOOR tiles (80%)
     * and a smaller proportion of unwalkable WALL tiles (approximately 20%).
     * The START and FINISH tiles are placed on random walkable positions in the
     * grid.
     *
     * Implementation Details:
     * - The grid is iteratively populated based on random probabilities,
     *   determining whether a tile is FLOOR or WALL.
     * - A helper method `placeStartAndFinish` is called to place the START
     *   and FINISH tiles.
     */
    @Override
    protected void generateFloor() {
        Random random = new Random();
        // fill grid with WALL tiles by default
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // 80% chance of floor
                if (random.nextDouble() < 0.8) {
                    grid[y][x] = new Tile(TileType.FLOOR);
                }
                else {
                    grid[y][x] = new Tile(TileType.WALL);
                }
            }
        }
        placeStartAndFinish(random);

        /**
        int startY = height / 2;  // middle row for the path

        // make sure all tiles in the row (except walls) are FLOOR
        for (int x = 1; x < width - 1; x++) {
            grid[startY][x] = new Tile(TileType.FLOOR);  // fill row with FLOOR
        }

        // set START and FINISH points
        grid[startY][1] = new Tile(TileType.START);
        grid[startY][width - 2] = new Tile(TileType.FINISH);
         */
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
