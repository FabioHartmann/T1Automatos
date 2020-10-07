import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// Classe que representa o autômato.

public class Automato {
    private ArrayList<Estado> estados;


    public Automato(Scanner esquema) throws Exception {
        /*
         * Método construtor do automato
         * @param scanner criado a partir das rules
         */
        this.estados = new ArrayList<>();
        int cont = 0;
        while (esquema.hasNext()) {
            // iteração sobre as linhas do esquema
            if(cont == 0 ){
                //caso seja a primeira linha, cria os estados
                String line = esquema.nextLine();
                criaEstados(line);
                cont++;
            } else if(cont == 1){
                //caso seja a segunda linha, edita os estados que devem ser finais para finais
                String line = esquema.nextLine();
                System.out.println("Line: " + line);
                estadosFinais(line);
                cont++;
            } else {
                while(esquema.hasNext()){
                    //após a segunda linha, cria as transições entre os estados
                    String line = esquema.nextLine();
                    criaTransicoes(line);
                }
            }
        }
    }
    public void adicionaEstado(Estado estado) {
        /*
         * Método para adicionar estados no autômato
         * @param um estado a ser adicionado no autômato
         */
        estados.add(estado);
    }

    private void criaEstados(String line) {
        /*
         * Método que cria os estados
         * @param linha com os "identificadores" dos estados
         */
        for(int i = 0; i < line.length(); i++){
            //percorre os identificadores e cria um estado para cada, sendo que o primeiro fica como inicial
            System.out.println(i);
            System.out.println("char at " + i + " : " + line.charAt(i));
            Estado estado = new Estado(i==0 ? Status.INICIAL : Status.NORMAL, line.charAt(i));
            this.adicionaEstado(estado);
            i+=1;
        }
    }

    private void estadosFinais(String line) throws Exception {
        /*
         * Método que edita os estados para finais
         * @param linha com os "identificadores" dos estados
         */
        for(int i = 0; i < line.length(); i++) {
            //percorre os identificadores e edita os estados da lista para FINAIS
            Estado estado = this.findByName(line.charAt(i));
            estado.setStatus(Status.FINAL);
            i+=1;
        }
    }
    public void criaTransicoes(String entrada) throws Exception {
        /*
         * Método que cria as transições entre os estados
         * @param entrada linha das rules com um estado, o valor da transição e estado que vai essa transição
         */
        Estado estado = this.findByName(entrada.charAt(0));
        if(entrada.charAt(2) == '0'){
            estado.setTransition0(entrada.charAt(4));
        } else if(entrada.charAt(2) == '1'){
            estado.setTransition1(entrada.charAt(4));
        }
        else{
            throw new Exception("Transição não aceita");
        }
    }

    public Estado findByName(char name) throws Exception {
        /*
         * Método encontra estados pelo "nome"
         * @param "nome" do estado
         */
        Estado status = null;
        for(Estado state : estados){
            if(state.getName() == name){
                status = state;
                break;
            }
        }
        if(status == null) throw new Exception("Automato inválido");
        return status;
    }

    public Estado findByIndex(int i) {
        /*
         * Método encontra estados pelo índice
         * @param indice do estado
         */
        if(i > estados.size()) throw new ArrayIndexOutOfBoundsException();
        return estados.get(i);
    }

    public void executaAutomato() throws Exception {
        /*
         * Método que executa o automato
         */
        Scanner input = new Scanner(new FileReader("C:\\Users\\fabio\\Desktop\\Faculdade\\2Semestre\\Automatos\\T1\\parte2\\src\\input")).useDelimiter("\\n");
        //Lê o arquivo de input
        Estado state = this.findByIndex(0);
        //Seleciona o estado inicial
        while (input.hasNext()){
            //Itera pela lista de inputs
            String line = input.nextLine();
            System.out.println("input: " + line);
            /*
             * Seleciona a transição, pra cada linha do input,
             *  seta o estado atual pelo findByName passando o nome do estado da transição 0
             */
            if(line.equals("0")){
                state = this.findByName(state.getTransition0());
            } else if(line.equals("1")){
                state = this.findByName(state.getTransition1());
            } else {
                throw new Exception("Caractere "+ line + " fora da linguagem");
            }
            System.out.println(state);
        }
        if(state.getStatus() == Status.FINAL) System.out.println("Success");
        else System.out.println("Fails");
    }

}
