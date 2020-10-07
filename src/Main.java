import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {
        Scanner scanner = new Scanner(new FileReader("C:\\Users\\fabio\\Desktop\\Faculdade\\2Semestre\\Automatos\\T1\\parte2\\src\\rules.afd")).useDelimiter("\\n");

        Automato automato = new Automato(scanner);

        automato.executaAutomato();
    }
}
