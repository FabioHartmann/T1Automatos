import java.util.HashMap;

enum Status {
    NORMAL, INICIAL,FINAL
}

public class Estado {
    private Status status;
    private String name;
    private HashMap<String, String> transitions;

    /*
     * Método construtor do autômato
     * @param status, um estado do enum Status
     * @param name, um caractere que será atribuido ao nome do estado
     * */
    public Estado(Status status, String name) {
        this.name = name;
        this.status = status;
        transitions = new HashMap<String, String>();
    }

    // Método que retorna qual o status do estado, um dos membros do enum Status
    public Status getStatus() {
        return status;
    }

    //método que retorna o nome do estado
    public String getName() {
        return name;
    }

    /*
     * Método que seta o valor do status
     * @param status, um estado do enum Status
     * */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Método que cria transações do estado baseado em um conjunto de chave-valor,
     * onde a chave é a entrada e o valor é o estado que o automato se direciona
     *
     * @param entry      O valor de entrada que o estado recebe
     * @param nextState  Estado o qual a entrada direciona o autômato
     */
    public void addTransition(String entry, String nextState) {
        this.transitions.put(entry, nextState);
    }

    public String getTransition(String entry) {
        return this.transitions.get(entry);
    }
}
