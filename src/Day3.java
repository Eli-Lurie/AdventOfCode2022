import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Inputs/Input3"));

        int total = 0;

        /* //Part 1
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            Set<Character> charSet = new HashSet<>();
            int i=0;
            for( ; i<line.length()/2; i++){
                charSet.add(line.charAt(i));
            }
            for( ; i<line.length(); i++){
                char c = line.charAt(i);
                if(charSet.contains(c)){
                    if( 'a' <= c && c <= 'z'){
                        total += (c - 'a' + 1);
                    }
                    else{
                        total += (c - 'A' + 27);
                    }
                    break;
                }
            }
        }
        */

        while(scanner.hasNextLine()){
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            String line3 = scanner.nextLine();

            Set<Character> set1 = new HashSet<>();
            Set<Character> set2 = new HashSet<>();
            for(int i=0; i<line1.length(); i++){
                set1.add(line1.charAt(i));
            }
            for(int i=0; i<line2.length(); i++){
                set2.add(line2.charAt(i));
            }
            for(int i=0; i<line3.length(); i++){
                char c = line3.charAt(i);
                if(set1.contains(c) && set2.contains(c)){
                    if( 'a' <= c && c <= 'z'){
                        total += (c - 'a' + 1);
                    }
                    else{
                        total += (c - 'A' + 27);
                    }
                    break;
                }
            }
        }

        System.out.println(total);
    }
}
