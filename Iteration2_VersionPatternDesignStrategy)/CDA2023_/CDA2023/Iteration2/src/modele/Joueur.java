package modele;

public class Joueur {
    private String nomJ ;
    private Damier damier;
    private String noir = "\u26AB";
    private String blanc = "\u26AA";

    private String couleur;
    private int nbPartiesGagnees;

    private int nbPartiesEgaux;


    public Joueur(){

    }
    public Joueur(String joueur, String couleur){
        this.nomJ = joueur;
        this.couleur = couleur;
        nbPartiesGagnees = 0;
    }


    public String getNomJ() {
        return nomJ;
    }

    public String getCouleur(){
        return couleur;
    }

    public void gagnerPartie(){
        this.nbPartiesGagnees++;
    }
    public int getNbPartiesGagnees(){
        return this.nbPartiesGagnees;
    }
    public void casEgaux(){
        this.nbPartiesEgaux++;
    }
    public int getNbPartiesEgaux() {
        return nbPartiesEgaux;
    }

    public String getNoir() {
        return noir;
    }

    public String getBlanc() {
        return blanc;
    }



}
