package LessonBackEndFirst;

public class Palindrome {
    public static boolean isPalindrome(String s) {
        return s.replaceAll("\\W", "")
                .equalsIgnoreCase(new StringBuilder(s.replaceAll("\\W", ""))
                        .reverse().toString());
    }
}
