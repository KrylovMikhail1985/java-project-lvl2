package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.text.Format;
import java.time.format.FormatStyle;
import java.util.concurrent.Callable;
//    //Annotate the class with @Command and give it a name. The mixinStandardHelpOptions attribute adds --help and
//    // --version options to your application.
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
    // Create a class that implements Runnable or Callable. This is your command.
public class App implements Runnable {
//    // For each positional parameter, add a @Parameters-annotated field to your command class.
    @Parameters(index = "0", description = "path to first file.")
    private Path filepath1;

    @Parameters(index = "0", description = "path to second file.")
    private Path filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "Format";

//
//    // Define your business logic in the run or call method of your class. This method is called after parsing
//    // is successfully completed.
    @Override
    public void run() { // your business logic goes here...
        System.out.println("Hello picocli!");
    }

    public static void main(String[] args) {
//        // In the main method of your class, use the CommandLine.execute method bootstrap your application. This will
//        // parse the command line, handle errors, handle requests for usage and version help,
//        // and invoke the business logic.
          CommandLine.run(new App(), args);
//        int exitCode = new CommandLine(new App()).execute(args);
//        // The CommandLine.execute method returns an exit code. Your application can call System.exit with this
//        // exit code to signal success or failure to the calling process.
//        System.exit(exitCode);
//        System.out.println("Hello World!");
    }
}
