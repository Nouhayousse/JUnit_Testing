package TestingLab;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static String reverse(String text){
        return new StringBuilder(text).reverse().toString();
    }

    public static boolean isPalindrome(String text){
        String cleaned=text.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        return cleaned.contentEquals(new StringBuilder(cleaned).reverse());
    }

    public static int countVowels(String text){
        int count=0;
        for(char c:text.toLowerCase().toCharArray()){
            if("aeiou".indexOf(c)>=0)
                count++;
        }
        return count;
    }

    public static List<String> splitWords(String text){
        List<String> mots=new ArrayList<>();
        StringBuilder mot=new StringBuilder();
        for(char c: text.toCharArray()){
            if(c==' ') {
                if(!mot.isEmpty()) {
                    mots.add(mot.toString());
                    mot = new StringBuilder();
                }
            }
            else {
                mot.append(c);
            }
        }
        if(!mot.isEmpty())
            mots.add(mot.toString());

        return mots;
    }
}
