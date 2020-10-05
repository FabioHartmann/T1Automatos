import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {
        Automato automato = new Automato(new ArrayList<>());
        int cont = 0;
        Scanner scanner = new Scanner(new FileReader("C:\\Users\\fabio\\Desktop\\Faculdade\\2Semestre\\Automatos\\T1\\parte2\\src\\rules.afd")).useDelimiter("\\n");
        while (scanner.hasNext()) {
            if(cont == 0 ){
                String line = scanner.nextLine();
                for(int i = 0; i < line.length(); i++){
                    System.out.println(i);
                    System.out.println("char at " + i + " : " + line.charAt(i));
                    Estado estado = new Estado(i==0 ? Status.INICIAL : Status.NORMAL, line.charAt(i));
                    automato.adicionaEstado(estado);
                    i+=1;
//                    continue;
                }
                cont++;
            } else if(cont == 1){
                String line = scanner.nextLine();
                System.out.println("Line: " + line);

                for(int i = 0; i < line.length(); i++) {
                Estado estado = automato.findByName(line.charAt(i));
                estado.setStatus(Status.FINAL);
                i+=1;
                }
                cont++;
            } else {
                //Transições
                int i = 0;
                while(scanner.hasNext()){
                    String line = scanner.nextLine();
                    Estado estado = automato.findByName(line.charAt(0));
                    if(line.charAt(2) == '0'){
                        estado.setTransition0(line.charAt(4));
                    } else if(line.charAt(2) == '1'){
                        estado.setTransition1(line.charAt(4));
                    }
                    else{
                        throw new Exception("Transição não aceita");
                    }
                }
            }
        }
        Scanner input = new Scanner(new FileReader("C:\\Users\\fabio\\Desktop\\Faculdade\\2Semestre\\Automatos\\T1\\parte2\\src\\input")).useDelimiter("\\n");
        Estado state = automato.findByIndex(0);
        while (input.hasNext()){
            String line = input.nextLine();
            System.out.println("input: " + line);
        if(line.equals("0")){
            System.out.println("no 0");
            state = automato.findByName(state.getTransition0());
        } else if(line.equals("1")){
            state = automato.findByName(state.getTransition1());
        } else {
            throw new Exception("Caractere "+ line + " fora da linguagem");
        }
            System.out.println(state);
        }
        if(state.getStatus() == Status.FINAL) System.out.println("Succes");
        else System.out.println("Fails");
    }
}
