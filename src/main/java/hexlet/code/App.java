package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
//import java.io.File;
//import java.io.IOException;
//import java.math.BigInteger;
//import java.nio.file.Files;
import java.nio.file.Path;
//import java.security.MessageDigest;
//import java.text.Format;
//import java.time.format.FormatStyle;
import java.util.concurrent.Callable;
//    Annotate the class with @Command and give it a name. The mixinStandardHelpOptions attribute adds --help and
//     --version options to your application.
@Command(name = "getDiff", mixinStandardHelpOptions = true, version = "getDiff 1.0",
        description = "Compares two configuration files and shows a difference.")
    // Create a class that implements Runnable or Callable. This is your command.
public class App implements Callable<String> {
//    // For each positional parameter, add a @Parameters-annotated field to your command class.
    @Parameters(index = "0", description = "path to first file.")
    private static Path filepath1;

    @Parameters(index = "1", description = "path to second file.")
    private static Path filepath2;

    @Option(names = {"-f", "--format"}, description = "output format \"stylish\" or \"plain\" or \"json\" "
            + "[default: stylish]")
    private String format = "stylish";

//  Define your business logic in the run or call method of your class. This method is called after parsing
//  is successfully completed.
    @Override
    public final String call() throws Exception { // your business logic goes here...
        Differ diff = new Differ();
        System.out.println(diff.generate(filepath1, filepath2, format));
        return "call is working";
    }

    public static void main(String[] args) throws Exception {
//  In the main method of your class, use the CommandLine.execute method bootstrap your application.
//  This will parse the command line, handle errors, handle requests for usage and version help,
//  and invoke the business logic.
        int exitCode = new CommandLine(new App()).execute(args);
//  The CommandLine.execute method returns an exit code. Your application can call System.exit with this exit code
//  to signal success or failure to the calling process.
        System.exit(exitCode);
    }
}
