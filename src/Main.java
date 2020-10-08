import java.io.*;
import java.nio.file.FileSystems;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {
        String path = FileSystems.getDefault().getPath("src", "rules.afd").toAbsolutePath().toString();
        Scanner scanner = new Scanner(new FileReader(path)).useDelimiter("\\n");

        Automato automato = new Automato(scanner);

        automato.executaAutomato();
    }
}
