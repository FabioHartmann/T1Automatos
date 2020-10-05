enum Status {
    NORMAL, INICIAL,FINAL
}
public class Estado {
    private Status status;
    private char name;
    private char transition1, transition0;


    public Estado(Status status, char name, char transition1, char transition0) {
        this.status = status;
        this.name = name;
        this.transition1 = transition1;
        this.transition0 = transition0;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public Estado(Status status, char name) {
        this.name = name;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public char getTransition1() {
        return transition1;
    }

    public void setTransition1(char transition1) {
        this.transition1 = transition1;
    }

    public char getTransition0() {
        return transition0;
    }

    public void setTransition0(char transition0) {
        this.transition0 = transition0;
    }

    public String toString(){
        return "Name: " + name + " transiction0: " + transition0 + " transiction1: " + transition1 + " Status: " + status;
    }
}
