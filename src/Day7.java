import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Inputs/Input7"));

        Direc root = new Direc("/", null);
        Direc current = root;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.charAt(0) == '$'){
                if(line.charAt(2) == 'c'){
                    if(line.charAt(5) == '/') current = root;
                    else if(line.charAt(5) == '.') current = current.parent;
                    else{
                        String moveto = line.substring(5);
                        for(Direc child : current.children){
                            if(child.name.equals(moveto)){
                                current = child;
                                break;
                            }
                        }
                    }
                }
            }
            else{
                if(line.charAt(0) == 'd'){
                    Direc child = new Direc(line.substring(4), current);
                    current.children.add(child);
                }
                else{
                    current.size += Long.parseLong(line.substring(0, line.indexOf(' ')));
                }
            }
        }

        root.findActualSize();
        Queue<Direc> queue = new LinkedList<>();
        queue.add(root);
        /* Part 1
        long total = 0;
        while(!queue.isEmpty()){
            Direc cur = queue.remove();
            queue.addAll(cur.children);
            if(cur.size < 100000) total += cur.size;
        }
         */

        long spaceNeeded = 30000000 - (70000000 - root.size);
        long min = Long.MAX_VALUE;
        while(!queue.isEmpty()){
            Direc cur = queue.remove();
            queue.addAll(cur.children);
            if(cur.size >= spaceNeeded && cur.size < min){
                min = cur.size;
            }
        }

        System.out.println(min);
    }


    public static class Direc{
        String name;
        Direc parent;
        Set<Direc> children;
        long size;

        public Direc(String name, Direc parent) {
            this.name = name;
            this.parent = parent;
            this.children = new HashSet<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Direc direc = (Direc) o;
            return name.equals(direc.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public long findActualSize(){
            for(Direc child : children){
                this.size += child.findActualSize();
            }
            return this.size;
        }

    }
}
