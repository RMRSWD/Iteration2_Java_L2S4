package vue;

import controleur.Controleur;
import modele.AINaif;
import modele.Joueur;

import java.util.Scanner;

public class Ihm {
    private Controleur controleur;
    public Ihm(){
        System.out.println("------------------------------\n" +
                           "Bienvenue dans le jeu Reversi\n" +
                           "------------------------------");
    }
    public void setControleur(Controleur controleur){
        this.controleur = controleur;
    }

    public void afficherDamier() {
        System.out.println(controleur.afficherDamier());
    }

    public void demandeCoup(Joueur joueur){
        Scanner sc = new Scanner(System.in);
        Joueur joueurCourant = joueur;
        String input;
        int numColonne;
        char lettre;
        boolean a = true;

        while (a) {
            System.out.println(joueurCourant.getNomJ() + joueurCourant.getCouleur() + " à vous de jouer. Saisir une ligne entre 1 et 8 suivie d'une lettre entre A et H (ex: 3D ) ou P pour passer son tour.");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("P")) {
                int casesPossible = controleur.CoupPossibleDuJoueur(joueur);
                if (casesPossible > 0) {
                    System.out.println("Vous ne pouvez passer votre tour. Vous avez "+ casesPossible + " case(s) possible(s) pour jouer.\n" +
                            "Pour vous aider: Vous pouvez jouer un coup à la position par exemple: " +
                            "( "+controleur.NumLigneExemple(joueur) + "," + controleur.CharColonneExemple(joueur) + " )");
                    continue;
                } else {
                    System.out.println("Tour passé.");
                    break;
                }
            }
            if (input.length() != 2) {
                System.out.println("Entrée non valide veuillez réessayer.");
                continue;
            }
            numColonne = input.charAt(0) -'0'; // '0'=48
            lettre = input.charAt(1);

            if (numColonne < 1 || numColonne > 8) {
                System.out.println("Entrée non valide, veuillez réessayer.");
                continue;
            }
            if (lettre < 'A' || lettre > 'H') {
                System.out.println("Entrée non valide, veuillez réessayer.");
                continue;
            }
            boolean testCoupJoueur = controleur.gererCoup(numColonne, lettre, joueurCourant);

            if(testCoupJoueur){
                controleur.effectuerCoup(numColonne, lettre, joueurCourant);
                break;
            }
            else{
                System.out.println("Veuillez re_saisir une position correcte.");
            }
        }
    }
    public void demandeRejouer(Joueur j1, Joueur j2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre partie est fini. Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
        String rejouer = sc.nextLine();
        while(true) {
            if (rejouer.equalsIgnoreCase("r")) {
                controleur.jouer();
                break;
            } else if (rejouer.equalsIgnoreCase("q")) {
                afficherScoreFinal(j1, j2);
                break;
            } else {
                System.out.println("Veuillez re-saisir.Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
                rejouer = sc.nextLine();
            }
        }
    }
    public void demandeRejouerAI_Naif(Joueur j1, Joueur j2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre partie est fini. Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
        while(true) {
            String rejouer = sc.nextLine();
            if (rejouer.equalsIgnoreCase("r")) {
                controleur.AI(new AINaif());
                break;
            } else if(rejouer.equalsIgnoreCase("q")){
                afficherScoreFinal(j1, j2);
                break;
            }
            else{
                System.out.println("Veuillez re-saisir.Tapez vous \"r\" pour rejouer ou \"q\" pour quiter le jeu.");
            }
        }
    }

    public void afficherGagneeUneParie(Joueur joueur,int nbPion){
        System.out.println(joueur.getNomJ()+ " : a gagné!" + " ; Le nombre de pions que vous avez: "+ nbPion);
    }
    public void afficherScoreFinal( Joueur j1, Joueur j2){
        System.out.println("Nombre partie(s) gagnée(s) " +j1.getNomJ() + ": "+ j1.getNbPartiesGagnees() );
        System.out.println("Nombre partie(s) gagnée(s) "+ j2.getNomJ() + ": "+ j2.getNbPartiesGagnees());
        System.out.println("Nombre partie(s) égal(aux) :" +j1.getNbPartiesEgaux());
        System.out.println("Aurevoir!!!");
        System.out.println("L'application fait par l'équipe TPF avec: "+"\n 1: Vu The Duc" + "\n 2: Vu Ngoc Hai" + "\n 3: Nordine Seffar");
    }
    public void afficherEgaux() {
        System.out.println("ex aequo");
    }

    public void afficherPerduAdversaire(Joueur joueur, int nbPion) {
        System.out.println(joueur.getNomJ()+ " : a perdu!" + " ; Le nombre de pions que vous avez: "+ nbPion);
    }
    public void choixVersionJeu(String nomJ1) {
        System.out.println("Vous pouvez choisir les mode de jeu en cliquant les numéros suivant: \n" +
                "1: la version humain vs humain \n" +
                "2: la version humain vs AI");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String choixVersion = sc.nextLine();
            if (choixVersion.equals("1")) {
                controleur.nomJoueur(nomJ1, saisirNomJoueur2(nomJ1));
                controleur.jouer();
                break;
            } else if(choixVersion.equals("2")){
                choixNieauAi(nomJ1);
                break;
            }
            else{
                System.out.println("Vous pouvez uniquement choisir entre 1 ou 2 pour entrer le mode de jeu\n" +
                        "1: la version humain vs humain \n" +
                        "2: la version humain vs AI");
            }
        }
    }
    public void choixNieauAi(String nomJ1) {
        System.out.println("Vous êtes dans le mode de jouer contre AI (version agréable)");
        String nomAI = "AI";
        controleur.nomJoueur(nomJ1, nomAI);
        controleur.AI(new AINaif());
    }
    public void saisirNomJoueur1() {
        Scanner sc = new Scanner(System.in);
        boolean testNom = true;
        while(testNom) {
            System.out.println("Entrer le nom du premier joueur: ");
            String nomJ1 = sc.nextLine();
            if(!nomJ1.equals("") && !nomJ1.equals("AI")){
                choixVersionJeu(nomJ1);
                testNom = false;
            }
            else{
                System.out.println("Veuillez re-saisir le nom du joueur!");
            }
        }
    }
    public String saisirNomJoueur2(String nomJ1) {
        Scanner sc = new Scanner(System.in);
        String nomJ2="";
        while(true) {
            System.out.println("Entrer le nom du deuxième joueur: ");
            nomJ2 = sc.nextLine();
            if(!nomJ2.equals("") && !nomJ1.equals(nomJ2)){
                break;
            }
            else{
                System.out.println("Le nom du joueur 1 et joueur 2 doit être differement!!");
                System.out.println("Veuillez re-saisir le nom du joueur 2!");
            }
        }
        return nomJ2;
    }
    public void afficherCoupIA(int lineAI, char colAI) {
        System.out.println("AI a fait le coup ("+ lineAI + "," +colAI+")");
    }

    public void passerTourIA() {
        System.out.println("AI passe son tour");
    }
}





