import java.io.Serializable;

public class Entidade implements Serializable {

    private int id;
    private String nome;

    public Entidade(String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Entidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
