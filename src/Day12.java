import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
    static final int ROWS = 41;
    static final int COLS = 179;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Inputs/Input12"));

        char[][] grid = new char[ROWS][COLS];
        Coordinates start;
        Coordinates end = new Coordinates(-1, -1);
        for(int i=0; i<ROWS; i++){
            String line = scanner.nextLine();
            for(int j=0; j<COLS; j++){
                grid[i][j] = line.charAt(j);
                if(grid[i][j] == 'S') {
                    grid[i][j] = 'a';
                }
                else if(grid[i][j] == 'E'){
                    end = new Coordinates(i, j);
                    grid[i][j] = 'z';
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == 'a') {
                    start = new Coordinates(i, j);
                    Map<Coordinates, Integer> visited = new HashMap<>();
                    Queue<Coordinates> queue = new LinkedList<>();
                    queue.add(start);
                    visited.put(start, 0);
                    while (!queue.isEmpty()) {
                        Coordinates current = queue.poll();
                        if (current.equals(end)) {
                            if(visited.get(end) < min){
                                min = visited.get(end);
                            }
                            break;
                        }
                        List<Coordinates> neighbors = new ArrayList<>();
                        Coordinates c1 = new Coordinates(current.row - 1, current.col);
                        Coordinates c2 = new Coordinates(current.row + 1, current.col);
                        Coordinates c3 = new Coordinates(current.row, current.col - 1);
                        Coordinates c4 = new Coordinates(current.row, current.col + 1);
                        neighbors.add(c1);
                        neighbors.add(c2);
                        neighbors.add(c3);
                        neighbors.add(c4);

                        for (Coordinates c : neighbors) {
                            if (!visited.containsKey(c) && valid(grid, c, current)) {
                                visited.put(c, visited.get(current) + 1);
                                queue.add(c);
                            }
                        }
                    }
                }
            }
        }

        //System.out.println(visited.get(end));
        System.out.println(min);
    }

    public static boolean valid(char[][] grid, Coordinates c, Coordinates current){
        return c.row >= 0 && c.row < ROWS && c.col >= 0 && c.col < COLS &&
                grid[c.row][c.col] <= grid[current.row][current.col] + 1;
    }

    public record Coordinates(int row, int col){ }
}
