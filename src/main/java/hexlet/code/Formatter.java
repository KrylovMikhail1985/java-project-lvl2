package hexlet.code;

public class Formatter {
    public static String formater(String format) {
        return switch (format) {
            case "stylish" -> "stylish";
            case "plain" -> "plain";
            case "json" -> "json";
            default -> "pointed format of output \"" + App.format + "\" is not correct! Try without indicating";
        };
    }
    public static String formater() {
            return switch (App.format) {
            case "stylish" -> "stylish";
            case "plain" -> "plain";
            case "json" -> "json";
            default -> "pointed format of output \"" + App.format + "\" is not correct! Try without indicating";
        };
    }
}
