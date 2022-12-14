import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day14 {
    public static void main(String[] args) throws IOException {
        String file = Files.readString(Path.of("Inputs/Input14"));
        String[] lines = file.split("\n");

        char[][] array = new char[200][1000];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = ' ';
            }
        }

        int largestY = 0;

        for (String line : lines) {
            String[] cords = line.split(" -> ");
            int prevX = -1, prevY = -1;
            for (String cord : cords) {
                String[] xy = cord.split(",");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);
                if (y > largestY) largestY = y;
                array[y][x] = '#';
                if (prevX != -1) {
                    while (prevX != x) {
                        prevX += (prevX < x ? 1 : -1);
                        array[y][prevX] = '#';
                    }
                    while (prevY != y) {
                        prevY += (prevY < y ? 1 : -1);
                        array[prevY][x] = '#';
                    }
                }
                prevX = x;
                prevY = y;
            }
        }

        for (int i = 0; i < array[0].length; i++) {
            array[largestY + 2][i] = '#';
        }


        int num = 0;
        while (true) {
            num++;
            int x = 500, y = 0;
            while (true) {
                if (array[y + 1][x] == ' ') y++;
                else if (array[y + 1][x - 1] == ' ') {
                    x--;
                    y++;
                } else if (array[y + 1][x + 1] == ' ') {
                    x++;
                    y++;
                } else {
                    array[y][x] = 'o';
                    break;
                }

                /* Part 1
                if(y > largestY){
                    sand = false;
                    break;
                }
                 */

            }
            if (x == 500 && y == 0) {
                break;
            }
        }

        System.out.println(num);
    }
}