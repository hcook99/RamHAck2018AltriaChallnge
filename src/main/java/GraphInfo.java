
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class GraphInfo {
    final static String extremlyPositive = "Extremely Positive";
    final static String positive = "Positive";
    final static String neutral = "Neutral";
    final static String negative = "Negative";
    final static String extremlyNegative = "Extremely Negative";
    private static String companyValue;
    private static Scanner reader= new Scanner(System.in);

    public static void main(String[] args){
        System.out.print("Enter Sentiment Search: ");
        companyValue = reader.nextLine();
        ArrayList<Integer> values=null;
        try {
            values = getCountAnalysis();
        }catch(Exception e){
            e.printStackTrace();
        }
        PrintWriter printWriter=null;
        try {
            FileWriter fileWriter = new FileWriter("values.txt");
            printWriter = new PrintWriter(fileWriter);
        }catch(Exception e){
            e.printStackTrace();
        }
        int count = 0;
        String info=null;
        for(int value: values){
            switch(count){
                case(0):
                    info=extremlyNegative;
                    break;
                case(1):
                    info=negative;
                    break;
                case(2):
                    info=neutral;
                    break;
                case(3):
                    info=positive;
                    break;
                case(4):
                    info=extremlyPositive;
                    break;
            }
            printWriter.println(value+" % " + info);
            count++;
        }
        printWriter.close();
    }

    public static ArrayList getCountAnalysis() throws Exception{
        ArrayList<Integer> countForValue = new ArrayList<Integer>();
        countForValue.add(0);
        countForValue.add(0);
        countForValue.add(0);
        countForValue.add(0);
        countForValue.add(0);
        GetUserTweets getUserTweets = new GetUserTweets();
        ArrayList<Float> userValues = getUserTweets.sentimentValues(companyValue);
        for(float value: userValues){
            if(value>0.75){
                countForValue.set(4, (countForValue.get(4)+1));
            }
            else if(value>0.25){
                countForValue.set(3, (countForValue.get(3)+1));
            }
            else if(value>-0.25){
                countForValue.set(2, (countForValue.get(2)+1));
            }
            else if(value>-0.75){
                countForValue.set(1, (countForValue.get(1)+1));
            }
            else if(value>-1.00){
                countForValue.set(0, (countForValue.get(0)+1));
            }
        }
        return countForValue;
    }
}
