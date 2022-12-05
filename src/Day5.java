import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Inputs/Input5"));

        ArrayList<LinkedList<Character>> stacks = new ArrayList<>();
        for(int i=0; i<9; i++){
            LinkedList<Character> list = new LinkedList<>();
            stacks.add(list);
        }

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.charAt(1) == '1') break;
            for(int i=0; i<9; i++){
                if(i*4+1 >= line.length()) break;
                char c = line.charAt(i*4+1);
                if(c == ' ') continue;
                stacks.get(i).addFirst(c);
            }
        }
        scanner.nextLine();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int num = Integer.parseInt(line.substring(5, line.indexOf('f')-1));
            int s1 = Character.getNumericValue(line.charAt(line.indexOf('t')-2)) - 1;
            int s2 = Character.getNumericValue(line.charAt(line.length()-1)) - 1;

            /* Part1
            for(int i=0; i<num; i++){
                char c = stacks.get(s1).removeLast();
                stacks.get(s2).addLast(c);
            }
             */

            LinkedList<Character> removed = new LinkedList<>();
            for(int i=0; i<num; i++){
                removed.addFirst(stacks.get(s1).removeLast());
            }
            stacks.get(s2).addAll(removed);
        }

        StringBuilder s = new StringBuilder();
        for(LinkedList<Character> list : stacks){
            s.append(list.removeLast());
        }
        System.out.println(s);
    }
}
