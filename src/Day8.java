import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Inputs/Input8"));

        final int numRows = 99;
        int[][] trees = new int[numRows][numRows];
        //boolean[][] visible = new boolean[numRows][numRows];

        for(int i=0; i<numRows; i++){
            String line = scanner.nextLine();
            for(int j=0; j<numRows; j++){
                trees[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        /* Part1
        for(int i=0; i<numRows; i++){
            int max = -1;
            for(int j=0; j<numRows; j++){
                if(trees[j][i] > max){
                    visible[j][i] = true;
                    max = trees[j][i];
                }
                if(max == 9) break;
            }

            max = -1;
            for(int j=numRows-1; j>=0; j--){
                if(trees[j][i] > max){
                    visible[j][i] = true;
                    max = trees[j][i];
                }
                if(max == 9) break;
            }


            max = -1;
            for(int j=0; j<numRows; j++){
                if(trees[i][j] > max){
                    visible[i][j] = true;
                    max = trees[i][j];
                }
                if(max == 9) break;
            }

            max = -1;
            for(int j=numRows-1; j>=0; j--){
                if(trees[i][j] > max){
                    visible[i][j] = true;
                    max = trees[i][j];
                }
                if(max == 9) break;
            }
        }


        int total = 0;
        for(boolean[] b : visible){
            for(boolean a : b){
                if(a) total++;
            }
        }
        System.out.println(total);
         */

        int maxScore = 0;
        for(int i=1; i<numRows-1; i++){
            for(int j=1; j<numRows-1; j++){
                int score = 1;
                for(int n=i-1; n>=0; n--){
                    if(trees[n][j] >= trees[i][j]){
                        score *= (i-n);
                        break;
                    }
                    if(n == 0){
                        score *= (i-n);
                    }
                }
                for(int n=i+1; n<numRows; n++){
                    if(trees[n][j] >= trees[i][j]){
                        score *= (n-i);
                        break;
                    }
                    if(n == numRows-1){
                        score *= (n-i);
                    }
                }
                for(int n=j-1; n>=0; n--){
                    if(trees[i][n] >= trees[i][j]){
                        score *= (j-n);
                        break;
                    }
                    if(n == 0){
                        score *= (j-n);
                    }
                }
                for(int n=j+1; n<numRows; n++){
                    if(trees[i][n] >= trees[i][j]){
                        score *= (n-j);
                        break;
                    }
                    if(n == numRows-1){
                        score *= (n-j);
                    }
                }

                if(score > maxScore) maxScore = score;
            }
        }

        System.out.println(maxScore);
    }
}
