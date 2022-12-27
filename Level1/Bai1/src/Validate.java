import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static Pattern pattern;
    private Matcher matcher;
    private static final String NAME_REGEX =   "^[a-z A-Z]{1,50}$";
    private static final String EMAIL_REGEX =   "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public boolean validateEmail(String regex) {
        pattern = Pattern.compile(EMAIL_REGEX);
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    public boolean validateName(String regex) {
        pattern = Pattern.compile(NAME_REGEX);
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
