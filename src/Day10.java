import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Inputs/Input10"));

        char[][] array = new char[6][40];

        int total = 1;
        int sum = 0;
        int cycle = 1;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.equals("noop")) {
                setBoard(array, cycle, total);
                cycle ++;
                if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220){
                    sum += (cycle * total);
                }
            }
            else{
                int x = Integer.parseInt(line.substring(5));
                setBoard(array, cycle, total);
                cycle ++;
                if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220){
                    sum += (cycle * total);
                }
                total += x;
                setBoard(array, cycle, total);
                cycle ++;
                if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220){
                    sum += (cycle * total);
                }
            }
        }
        System.out.println(sum);

        for(char[] a : array){
            for(char c : a){
                System.out.print(c == '#' ? c : ' ');
            }
            System.out.println();
        }
    }

    static void setBoard(char[][] array, int cycle, int total){
        int row = cycle%40;
        if(row == total || row == total + 1 || row == total - 1){
            array[(cycle-1)/40][row] = '#';
        }
    }
}
