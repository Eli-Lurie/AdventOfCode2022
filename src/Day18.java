import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day18 {
    static final int MAX= 25;

    public static void main(String[] args) throws IOException {
        String file = Files.readString(Path.of("Inputs/Input18"));
        String[] lines = file.split("\n");

        Set<Cube> cubes = new HashSet<>();
        //int total = 0;

        for(String line : lines){
            String[] dimensions = line.split(",");
            int x = Integer.parseInt(dimensions[0]) + 1;
            int y = Integer.parseInt(dimensions[1]) + 1;
            int z = Integer.parseInt(dimensions[2]) + 1;
            /* Part 1
            total += 6;
            for(Cube cube : cubes){
                int[] difs = {Math.abs(x - cube.x), Math.abs(y - cube.y), Math.abs(z - cube.z)};
                Arrays.sort(difs);
                if(difs[0] == 0 && difs[1] == 0 && difs[2] == 1)
                    total -= 2;
            }
             */
            cubes.add(new Cube(x, y, z));
        }

        //System.out.println(total);

        System.out.println(bfs(cubes));
    }

    static int bfs(Set<Cube> cubes){
        int totalEdges = 0;
        Set<Cube> visited = new HashSet<>();
        Queue<Cube> queue = new LinkedList<>();
        queue.offer(new Cube(0, 0, 0));

        while(!queue.isEmpty()){
            Cube cube = queue.poll();
            if(!visited.contains(cube)) {
                visited.add(cube);
                List<Cube> neighbors = cube.getNeighbors();
                totalEdges += neighbors.stream().filter(cubes::contains).count();
                neighbors.stream().filter(c -> !cubes.contains(c)).forEach(queue::offer);
            }
        }

        return totalEdges;
    }


    record Cube(int x, int y, int z){
        List<Cube> getNeighbors(){
            List<Cube> neighbors = new ArrayList<>();
            neighbors.add(new Cube(x-1,y,z));
            neighbors.add(new Cube(x+1,y,z));
            neighbors.add(new Cube(x,y-1,z));
            neighbors.add(new Cube(x,y+1,z));
            neighbors.add(new Cube(x,y,z-1));
            neighbors.add(new Cube(x,y,z+1));
            neighbors.removeIf(cube -> cube.x < 0 || cube.x > MAX || cube.y < 0 || cube.y > MAX || cube.z < 0 || cube.z > MAX);
            return neighbors;
        }
    }
}
