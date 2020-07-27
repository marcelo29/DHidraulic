package br.com.android.domain;

public class Banheiro {
    private int numTorneira, numPrivada, numDucha, numBanheira, numBebedouro, numTanque, numMC, numChuveiro;
    private boolean valvula;

    public int getNumTorneira() {
        return numTorneira;
    }

    public void setNumTorneira(int numTorneira) {
        this.numTorneira = numTorneira;
    }

    public int getNumPrivada() {
        return numPrivada;
    }

    public void setNumPrivada(int numPrivada) {
        this.numPrivada = numPrivada;
    }

    public int getNumDucha() {
        return numDucha;
    }

    public void setNumDucha(int numDucha) {
        this.numDucha = numDucha;
    }

    public int getNumBanheira() {
        return numBanheira;
    }

    public void setNumBanheira(int numBanheira) {
        this.numBanheira = numBanheira;
    }

    public int getNumBebedouro() {
        return numBebedouro;
    }

    public void setNumBebedouro(int numBebedouro) {
        this.numBebedouro = numBebedouro;
    }

    public int getNumTanque() {
        return numTanque;
    }

    public void setNumTanque(int numTanque) {
        this.numTanque = numTanque;
    }

    public int getNumMC() {
        return numMC;
    }

    public void setNumMC(int numMC) {
        this.numMC = numMC;
    }

    public int getNumChuveiro() {
        return numChuveiro;
    }

    public void setNumChuveiro(int numChuveiro) {
        this.numChuveiro = numChuveiro;
    }

    public boolean isValvula() {
        return valvula;
    }

    public void setValvula(boolean valvula) {
        this.valvula = valvula;
    }
}
