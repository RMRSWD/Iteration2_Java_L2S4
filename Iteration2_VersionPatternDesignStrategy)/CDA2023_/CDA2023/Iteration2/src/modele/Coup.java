package modele;

public class Coup {
    // cette classe crée les pion noirs et blancs
    private int numLigne;
    private char lettre;

    public Coup(int numLigne, char lettre){
        this.numLigne = numLigne;
        this.lettre = lettre;

    }

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public char getLettre() {
        return lettre;
    }

    public void setLettre(char lettre) {
        this.lettre = lettre;
    }
}