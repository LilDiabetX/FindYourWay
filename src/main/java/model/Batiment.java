package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Batiment{
    // Attributs
    protected final int nbEtages;
    protected final int max;
    protected final int min;


    public final List<Etage> etages =  new ArrayList<>();
    public final List<Noeud> noeuds = new ArrayList<>();

    // Constructeur 
    public Batiment(int nbEtages, int max, int min){
        this.nbEtages = nbEtages;
        this.max = max;
        this.min = min;

        initEtages();
        createNoeuds();
        Parseur p = new Parseur(this, "src/main/ressources/halle_aux_farines.csv");
        p.initNoeuds();
        sortNoeuds();
    }


    // Methodes

    /**
     * Initialise les étages
     */
    private void initEtages(){
        for(int i = 0; i<nbEtages; i++){
            etages.add(new Etage(i)); //les salles sont initialisées dans les étages
        }  
    }

    /**
     * Crée tous les noeuds du graphe
     */
    private void createNoeuds(){
        //TODO : initialiser les vrais noeuds

        //TODO : supprimer les noeuds de test
        noeuds.add(new Carrefour(false, 0, this));
        noeuds.add(new Carrefour(true, 1, this));
        noeuds.add(new Porte("01", 0, this)); 
    }

    /*
     * Répartit les noeuds du batiment dans leurs étages respectifs
     */
    private void sortNoeuds(){
        for(int i = 0; i<noeuds.size(); i++){
            Noeud n = noeuds.get(i);
            if(n.etage >= 0 && n.etage <= 5){
                etages.get(n.etage).addNoeud(n);
            }else{
                throw new IllegalArgumentException("Mauvais numéro d'étage");
            }
        }
    }
    
    /**
     * Retourne la liste de noeuds du bâtiment
     * @return la liste de noeuds du bâtiment
     */
    public List<Noeud> getNoeuds() {
        return this.noeuds;
    }

    /**
     * Retourne le noeud situé à l'indice i de la liste de noeuds
     * @param i
     * @return le noeud situé à l'indice i de la liste de noeuds
     */
    public Noeud getNoeud(int i) {
        return this.noeuds.get(i);
    }

    public static void main(String[] args) {
        Batiment haf = new Batiment(6, 5, 0);

        Iterator<Noeud> it = haf.noeuds.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
            
        }
    }

}