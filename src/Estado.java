enum Status {
    NORMAL, INICIAL,FINAL
}
public class Estado {
    private Status status;
    private char name;
    private char transition1, transition0;

    //método que retorna o nome do estado
    public char getName() {
        return name;
    }

    /*
     * Método construtor do autômato
     * @param status, um estado do enum Status
     * @param name, um caractere que será atribuido ao nome do estado
     * */
    public Estado(Status status, char name) {
        this.name = name;
        this.status = status;
    }

    // Método que retorna qual o status do estado, um dos membros do enum Status
    public Status getStatus() {
        return status;
    }

    /*
     * Método que seta o valor do status
     * @param status, um estado do enum Status
     * */
    public void setStatus(Status status) {
        this.status = status;
    }

    //Método que retorna o nome do estado da transição caso receba 1 como valor
    public char getTransition1() {
        return transition1;
    }

    /*
     * Método que seta o valor do status
     * @param transition1, recebe caracter que é o nome do status da transição caso receba 1 como entrada
     * */
    public void setTransition1(char transition1) {
        this.transition1 = transition1;
    }

    //Método que retorna o nome do estado da transição caso receba 0 como valor
    public char getTransition0() {
        return transition0;
    }

    /*
     * Método que seta o valor do status
     * @param transition0, recebe caracter que é o nome do status da transição caso receba 0 como entrada
     * */
    public void setTransition0(char transition0) {
        this.transition0 = transition0;
    }
}
