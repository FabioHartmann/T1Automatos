import java.util.ArrayList;

public class Automato {
    private ArrayList<Estado> estados;

    public Automato(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public void adicionaEstado(Estado estado) {
        estados.add(estado);
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public Estado findByName(char name) throws Exception {
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
        if(i > estados.size()) throw new ArrayIndexOutOfBoundsException();
        return estados.get(i);
    }

    public int size(){
        int i = 0;
        while( i < estados.size()) i++;
        return i;
    }
}
