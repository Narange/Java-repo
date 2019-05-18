import java.util.*;
import java.io.*;

/**
 * Java sandbox. A bunch of miscellaneous stuff.
 */
public class JavaMisc{

    //put stuff you want to test in here!
    public static void main(String[] args){
    	manyCollatz(1000);
    }


    //Collatz sequence: take an int n, divide by 2 if even, else (n*3 + 1)
    //Sequence should collapse to 4, 2, 1, 4, 2, 1, etc.
    //returns the highest number in the sequence
    public static int collatz(int n){
        int x= n;
        int p= n;
        while (n!=1){
            if (n%2==1){
                n= 3*n + 1;
            } else {
                n= n/2;
            }
            if (n > p){
                p= n;
            }
        }
        System.out.println("" + x + " peaks at " + p);
        return p;
    }

    //the collatz() method done n many times
    public static void manyCollatz(int n){
      int num = 0, peak = 0;
      HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i= 1; i <= n; i++){
            int currentPeak = collatz(i);
            if (currentPeak > peak){
              peak = currentPeak;
              num = i;
              map.put(i, currentPeak);
            }
            
        }
        //I wanted to use a HashMap and also wanted to order it. I learned
        //on StackOverflow (of course) that Maps can be ordered
        //if you put them in a TreeMap. Woohoo, polymorphism!
        Map<Integer, Integer> orderedMap = new TreeMap<Integer, Integer>(map);
        System.out.println(orderedMap.toString());
        System.out.println("first highest peak was " + num + " peaking at " + peak);
    }

    //returns factorial of int n
    public static int factorial(int n){
      if (n == 0){
        return 1;
      } else {
        return n * factorial(n-1);
      }
    }
    
    //reverse an array
    public static int[] reverseArray(int[] a){
        int[] result= new int[a.length];
        int len= a.length-1;
        int x= 0;
        for(int i= len; i <=0; i--){
            result[x]= a[i];
            x++;
        }
        return result;
    }
    
    //return the first out of alphabetic order char (as a String) in a String by comparing ASCII vals
    public static String firstOutOfOrder(String s){
      int currentHighest = 0;
      int currentIndex = 0;
      String result = "";
      
      for(int i= 0; currentIndex >= currentHighest; i++){
        
        currentHighest = currentIndex;
        currentIndex = (int) s.charAt(i);
        
        //set result to the current char(substring)
        if(i != s.length()-1){
          result = s.substring(i, i+1);
        } else { result = s.substring(i); }
      }
      return result;
    }
    
    //all the lyrics to "Bottles of Beer" you'll ever need! (n bottles)
    public static void bottlesOfBeer(int n){
      for(int i = n; i > 1; i--){
        System.out.println(i + " bottles of beer on the wall\n" + i + " bottles of beer\nTake one down and pass it around");
      }
      System.out.println("1 bottle of beer on the wall\n1 bottle of beer\nTake it down and pass it around");
      System.out.println("There are no bottles of beer on the wall");
    }
    
    //template to create a text file and write a String in it
    public static void write(String filename, String specialText){
      
      try{
        FileWriter fw = new FileWriter(filename);
        PrintWriter pw = new PrintWriter(fw);
      
        pw.println("first line: test");
        pw.println("hello world");
        pw.println(specialText);
        
        pw.close();
        
      } catch (IOException e) {
        System.out.println("An IOException occurred");
      }
      
    }
    
    //template for reading from a text file
    //currently prints out the line at specified index "line"
    public static void read(String filename, int line){
      
      try{
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        
        String str;
        int count = 0;
        while((str = br.readLine()) != null){
          
          if(count == line){
            System.out.println(str);
          }
          count++;
        }
        
        br.close();
        
      } catch(IOException e) {
        System.out.println("An IOException occurred");
      }
      
    }
    
    //return number of times char c appears in String s
    public static int countChar(String s, char c){
      int count = 0;
      for (int i = 0; i < s.length(); i++){
        if (s.charAt(i) == c) {
          count++;
        }
      }
      return count;
    }
    
    
    //For a Project Euler problem
    //return sum of all multiples of a OR b that are less than limit
    public static int multiples(int limit, int a, int b){
      int sum = 0;
      for (int  i = 0; i < limit; i++){
        if (i%a==0 || i%b==0){sum += i;}
      }
      return sum;
    }
    
    //For a Project Euler problem
    //give it an int, returns if it reads the same backward/forward (palindrome)
    public static boolean isPalindrome(int x){
      String forward = "" + x;
      String backward = "";
      
      for (int i = forward.length()-1; i!=-1; i--){
        backward += forward.charAt(i);
      }
      return forward.equals(backward);
    }
    
    //For a Project Euler problem
    //using JavaMisc.isPalindrome above, find largest one that is the product
    //of two 3-digit numbers, also print those factors. Brute force method
    public static int largestPalindrome(){
      int highScore = 0;
      int a = 0;
      int b = 0;
      for (int i = 100; i <= 999; i++){
        for (int j = 100; j <= 999; j++){
          if (isPalindrome(i*j) && i*j > highScore){
            highScore = i*j;
            a = i;
            b = j;
          }
        }
      }
      System.out.println(a + ", " + b);
      return highScore;
    }
    
    //Project Euler problem: sum of squares of integers from 1 through n
    public static int sumOfSquares(int n){
      int result = 0;
      for (int i = 1; i <= n; i++){
        result += i*i;
      }
      return result;
    }
    
    //Project Euler problem: square of sum of integers from 1 through n
    public static int squareOfSum(int n){
      int result = 0;
      for (int i = 1; i <= n; i++){
        result += i;
      }
      return result * result;
    }
    
    //Project Euler problem: the difference in between the results of the above two functions
    public static int squareSumDiff(int n){
      return squareOfSum(n) - sumOfSquares(n);
    }
    
    //encode String by decrementing ascii value of chars
    //Do not use escape char "\"
    public static String encode(String s){
      String result = "";
      for (int i = 0; i < s.length(); i++){
        int x = (int) s.charAt(i);
        int xNew = x - 1;
        result += (char) xNew;
      }
      return result;
    }
    
    //decode String by incrementing ascii value of chars
    //Do not use escape char "\"
    public static String decode(String s){
      String result = "";
      for (int i = 0; i < s.length(); i++){
        int x = (int) s.charAt(i);
        int xNew = x + 1;
        result += (char) xNew;
      }
      return result;
    }
    
    //Remove all char x from String s
    public static String removeLetter(String s, char x){
      
      String result = "";

      for (int i = 0; i < s.length(); i++){
        if (!(s.charAt(i) == x)){
          result += s.charAt(i);
        }
      }
      return result;
    }
    
    //Just a joke: Translate Spanish to Chilean Spanish
    //(removes the letter "s" and appends "po" to everything)
    public static void toChilean(){
      String input = "";
      Scanner scanner = new Scanner(System.in);

      System.out.println("**Spanish to Chilean translator**\nEnter END to end program.");
      
      while(!input.equals("END")){
        System.out.print("Enter text to be translated: ");
        input = scanner.nextLine();
        String newChileanString = removeLetter(input, 's');
        System.out.println(newChileanString + " po");
      }
      scanner.close();
    }
    
    //Phenomenal question to ask me at an interview
    //Demonstrates peak human mental prowess
    //Requires machine learning and neural networks, probably
    public static void fizzBuzz(int n){
      for (int i = 0; i <= n; i++){
        System.out.print(i + " ");
        if (i%3 == 0) System.out.print("fizz ");
        if (i%5 == 0) System.out.print("buzz");
        System.out.print("\n");
      }
    }
    
}
