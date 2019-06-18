package object;

import java.io.Serializable;

public class ValoresOperacao implements Serializable {

    private int numero1;
    private int numero2;
    private int operacao;

    public ValoresOperacao(){}

    public int getNumero1() {
        return numero1;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public void setNumero2(int numero2) {
        this.numero2 = numero2;
    }

    public int getOperacao() {
        return operacao;
    }

    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }
}
