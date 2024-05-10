import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateName {

    public static void main(String[] args) {
        System.out.println( isValidName("aa12") );
        System.out.println( isValidName("aaaaa") );
        System.out.println( isValidName("alvaresds") );
        System.out.println( isValidName("oio1oio") );
    }

    public static boolean isValidName(String name) {
        boolean isNameValid = false;

        if (name != null && name.length() > 0) {
            String regex = "^[a-zA-Z]+";

            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(name);

            if (matcher.matches()) {
                isNameValid = true;
            }
        }

        return isNameValid;
    }

}