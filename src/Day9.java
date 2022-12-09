import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Inputs/Input9"));

        Set<Position> visited = new HashSet<>();

        Position[] knots = new Position[10];
        for(int i=0; i<10; i++){
            knots[i] = new Position(0, 0);
        }
        visited.add(knots[9]);
        /* Part 1
        Position head = new Position(0,0);
        Position tail = new Position(0,0);
        visited.add(tail);
         */

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            char direction = line.charAt(0);
            int num = Integer.parseInt(line.substring(2));

            for(int i=0; i<num; i++){
                switch (direction) {
                    case 'U' -> knots[0] = new Position(knots[0].x, knots[0].y-1);
                    case 'D' -> knots[0] = new Position(knots[0].x, knots[0].y+1);
                    case 'L' -> knots[0] = new Position(knots[0].x-1, knots[0].y);
                    case 'R' -> knots[0] = new Position(knots[0].x+1, knots[0].y);
                }

                for(int n=1; n<10; n++) {
                    if (knots[n-1].y == knots[n].y && knots[n-1].x - 2 == knots[n].x) {
                        knots[n] = new Position(knots[n].x + 1, knots[n].y);
                    } else if (knots[n-1].y == knots[n].y && knots[n-1].x + 2 == knots[n].x) {
                        knots[n] = new Position(knots[n].x - 1, knots[n].y);
                    } else if (knots[n-1].x == knots[n].x && knots[n-1].y - 2 == knots[n].y) {
                        knots[n] = new Position(knots[n].x, knots[n].y + 1);
                    } else if (knots[n-1].x == knots[n].x && knots[n-1].y + 2 == knots[n].y) {
                        knots[n] = new Position(knots[n].x, knots[n].y - 1);
                    } else if (knots[n-1].x - 1 == knots[n].x && knots[n-1].y - 2 == knots[n].y) {
                        knots[n] = new Position(knots[n].x + 1, knots[n].y + 1);
                    } else if (knots[n-1].x - 1 == knots[n].x && knots[n-1].y + 2 == knots[n].y) {
                        knots[n] = new Position(knots[n].x + 1, knots[n].y - 1);
                    } else if (knots[n-1].x + 1 == knots[n].x && knots[n-1].y - 2 == knots[n].y) {
                        knots[n] = new Position(knots[n].x - 1, knots[n].y + 1);
                    } else if (knots[n-1].x + 1 == knots[n].x && knots[n-1].y + 2 == knots[n].y) {
                        knots[n] = new Position(knots[n].x - 1, knots[n].y - 1);
                    } else if (knots[n-1].x - 2 == knots[n].x && knots[n-1].y - 1 == knots[n].y) {
                        knots[n] = new Position(knots[n].x + 1, knots[n].y + 1);
                    } else if (knots[n-1].x - 2 == knots[n].x && knots[n-1].y + 1 == knots[n].y) {
                        knots[n] = new Position(knots[n].x + 1, knots[n].y - 1);
                    } else if (knots[n-1].x + 2 == knots[n].x && knots[n-1].y - 1 == knots[n].y) {
                        knots[n] = new Position(knots[n].x - 1, knots[n].y + 1);
                    } else if (knots[n-1].x + 2 == knots[n].x && knots[n-1].y + 1 == knots[n].y) {
                        knots[n] = new Position(knots[n].x - 1, knots[n].y - 1);
                    } else if(knots[n-1].x - 2 == knots[n].x && knots[n-1].y - 2 == knots[n].y){
                        knots[n] = new Position(knots[n].x + 1, knots[n].y + 1);
                    } else if(knots[n-1].x - 2 == knots[n].x && knots[n-1].y + 2 == knots[n].y){
                        knots[n] = new Position(knots[n].x + 1, knots[n].y - 1);
                    } else if(knots[n-1].x + 2 == knots[n].x && knots[n-1].y + 2 == knots[n].y){
                        knots[n] = new Position(knots[n].x - 1, knots[n].y - 1);
                    } else if(knots[n-1].x + 2 == knots[n].x && knots[n-1].y - 2 == knots[n].y){
                        knots[n] = new Position(knots[n].x - 1, knots[n].y + 1);
                    }
                }
                visited.add(knots[9]);
            }
        }

        System.out.println(visited.size());
    }

    public record Position(int x, int y) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
