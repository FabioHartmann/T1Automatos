import java.io.FileReader;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Scanner;

// Classe que representa o autômato.

public class Automato {
    private ArrayList<Estado> estados;
    private ArrayList<String> alfabeto;

    /*
     * Método construtor do automato
     * @param scanner criado a partir das rules
     */
    public Automato(Scanner esquema) throws Exception {
        this.estados = new ArrayList<>();
        this.alfabeto = new ArrayList<>();

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

    /*
     * Método para adicionar estados no autômato
     * @param um estado a ser adicionado no autômato
     */
    public void adicionaEstado(Estado estado) {
        estados.add(estado);
    }

    /*
     * Método que cria os estados
     * @param linha com os "identificadores" dos estados
     */
    private void criaEstados(String line) {
        String[] nomesEstados = line.split(" ");
        for(int i = 0; i < nomesEstados.length; i++){
            //percorre os identificadores e cria um estado para cada, sendo que o primeiro fica como inicial
            Estado estado = new Estado(i==0 ? Status.INICIAL : Status.NORMAL, nomesEstados[i]);
            this.adicionaEstado(estado);
        }
    }

    /*
     * Método que edita os estados para finais
     * @param linha com os "identificadores" dos estados
     */
    private void estadosFinais(String line) throws Exception {
        String[] nomesEstados = line.split(" ");
        for(int i = 0; i < nomesEstados.length; i++) {
            //percorre os identificadores e edita os estados da lista para FINAIS
            Estado estado = this.findByName(nomesEstados[i]);
            estado.setStatus(Status.FINAL);
        }
    }

    /*
     * Método que cria as transições entre os estados
     * @param entrada linha das rules com um estado, o valor da transição e estado que vai essa transição
     */
    public void criaTransicoes(String entrada) throws Exception {
        String[] dadosTransicao = entrada.split(" ");
        Estado estado = this.findByName(dadosTransicao[0]);

        estado.addTransition(dadosTransicao[1], dadosTransicao[2]);
        alfabeto.add(dadosTransicao[1]);
    }

    /*
     * Método encontra estados pelo "nome"
     * @param "nome" do estado
     */
    public Estado findByName(String name) throws Exception {
        Estado status = null;
        for(Estado state : estados){
            if(state.getName().equals(name)){
                status = state;
                break;
            }
        }
        if(status == null) throw new Exception("Automato inválido");
        return status;
    }

    /*
     * Método encontra estados pelo índice
     * @param indice do estado
     */
    public Estado findByIndex(int i) {
        if(i > estados.size()) throw new ArrayIndexOutOfBoundsException();
        return estados.get(i);
    }

    /*
     * Método que executa o automato
     */
    public void executaAutomato() throws Exception {
        //Lê o arquivo de input
        String path = FileSystems.getDefault().getPath("src", "input").toAbsolutePath().toString();
        Scanner input = new Scanner(new FileReader(path)).useDelimiter("\\n");

        //Itera pela lista de inputs
        while (input.hasNext()){
            String line = input.nextLine();

            //Seleciona o estado inicial
            Estado state = this.findByIndex(0);

            for (Character ch : line.toCharArray()) {
                String entry = ch.toString();
                if (!alfabeto.contains(entry)) {
                    throw new Exception("Invalid entry");
                }
                /*
                 * Seleciona a transição, pra cada linha do input,
                 *  seta o estado atual pelo findByName passando o nome do estado da transição 0
                 */
                state = this.findByName(state.getTransition(entry));
            }
            if(state.getStatus() == Status.FINAL) System.out.println("Success");
            else System.out.println("Fails");
        }
    }

}
