package modele;

import java.util.List;
import java.util.Random;

public class AINaif {

    public AINaif(){
    }
    public Coup CoupAI(Damier damier, Joueur joueur){
        Random random = new Random();
        Coup coupAI;
        List <Coup> tousLesCoupPossibleAI = damier.prendTousLesCoupsPossible(joueur);
        int taille_table = tousLesCoupPossibleAI.size();
        if(taille_table > 0) {
            int randomNumber = random.nextInt(taille_table);
            coupAI = tousLesCoupPossibleAI.get(randomNumber);
            return coupAI;
        }
        else{
            return null;
        }
    }
}
