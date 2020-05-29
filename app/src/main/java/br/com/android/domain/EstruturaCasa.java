package br.com.android.domain;

public class EstruturaCasa {
    private int numPessoas, numPavimentos, numCozinha, numAreaServico, numBanheiro;

    public EstruturaCasa(){

    }

    public EstruturaCasa(int numPessoas) {
        this.numPessoas = numPessoas;
    }

    public int getNumPessoas() {
        return numPessoas;
    }

    public void setNumPessoas(int numPessoas) {
        this.numPessoas = numPessoas;
    }

    public int getNumBanheiro() {
        return numBanheiro;
    }

    public void setNumBanheiro(int numBanheiro) {
        this.numBanheiro = numBanheiro;
    }

    public int getNumPavimentos() {
        return numPavimentos;
    }

    public void setNumPavimentos(int numPavimentos) {
        this.numPavimentos = numPavimentos;
    }

    public int getNumCozinha() {
        return numCozinha;
    }

    public void setNumCozinha(int numCozinha) {
        this.numCozinha = numCozinha;
    }

    public int getNumAreaServico() {
        return numAreaServico;
    }

    public void setNumAreaServico(int numAreaServico) {
        this.numAreaServico = numAreaServico;
    }
}
