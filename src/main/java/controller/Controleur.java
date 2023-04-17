package controller;

import java.util.List;

import model.Batiment;
import model.Etage;
import model.Noeud;
import view.Vue;

/**
 * Gère les interactions entre la vue et le modèle
 */
public class Controleur {
    //Attributs

    /**
     * Vue associée au controleur
     */
    private Vue vue;

    /**
     * le batiment sur lequel la vue est centrée
     */
    private Batiment batActuel;


    //Constructeur

    /**
     * Construit le controleur de l'app
     * @param bat le bâtiment de départ
     */
    public Controleur(Batiment bat) {
        this.batActuel = bat;
        this.vue = new Vue(this);
    }


    //Getters & setters

    /**
     * Renvoie l'étage max du bâtiment sur lequel la vue est centrée
     * @return l'étage max du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMaxActuel() {
        return batActuel.getMax();
    }

    /**
     * Renvoie l'étage min du bâtiment sur lequel la vue est centrée
     * @return l'étage min du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMinActuel() {
        return batActuel.getMin();
    }

    /**
     * Renvoie le batiment actuel
     * @return le batiment actuel
     */
    public Batiment getBatiment(){
        return batActuel;
    }

    /**
     * Renvoie la salle qui a pour nom {@code nom}
     * @param salle nom de la salle à trouver
     * @return le noeud de la salle qui a pour nom {@code nom}
     */
    private Noeud getSalle(String salle){
        return batActuel.getSalle(salle);
    }

    /**
     * Renvoie la vue du controleur
     * @return la vue du controleur
     */
    public Vue getVue(){
        return vue;
    }

    /**
     * Renvoie la liste des noeuds à afficher de l'étage numéro {@code numEtage}
     * @param numEtage le numéro de l'étage
     * @return la liste de ses points
     */
    public List<Noeud> getNoeudsEtage(int numEtage) {
        Etage etage = batActuel.getEtage(numEtage);

        return etage.getNoeuds();
    }


    //Méthodes

    /**
     * Vérifie que les textes contenus dans les TextFieldBox sont soit des noms de salles, soit "Départ", soit "Arrivée"
     * @return vrai si les textes dans les TextFieldBox sont soit des noms de salles, soit "Départ", soit "Arrivée", faux sinon
     */
    public boolean verifGoButton(){
        String start = vue.getApp().getStart().getText();
        String finish = vue.getApp().getFinish().getText();
        if((start.equalsIgnoreCase("Départ") || estSalle(start)) && (finish.equalsIgnoreCase("Arrivée") || estSalle(finish))){
            return true;
        }
        return false;
    }

    /**
     * Vérifie si le nom {@code salle} est celui d'une salle du batiment
     * @param salle nom de la salle
     * @return vrai si le nom correspond à celui d'une salle du batiment, faux sinon
     */
    public boolean estSalle(String salle){
        return batActuel.findSalle(salle);
    }

    /**
     * Met à jour l'application en fonction des entrées utilisateur
     */
    public void majApp(){
        if(this.vue.getApp().getStart().getText().equals("Départ") && this.vue.getApp().getFinish().getText().equals("Arrivée")){
            //on affiche seulement les plans
            this.vue.majApp(null, null, vue.getApp().getAscenseur().isSelected());
        }
        else if(this.vue.getApp().getStart().estSalle() && this.vue.getApp().getFinish().estSalle()){
            //on affiche le chemin le plus court entre deux salles
            this.vue.majApp(getSalle(vue.getApp().getStart().getText()), getSalle(vue.getApp().getFinish().getText()), vue.getApp().getAscenseur().isSelected());
        }
        else if((this.vue.getApp().getStart().getText().equals("Départ") && this.vue.getApp().getFinish().estSalle())){
            //on affiche les portes de la salle d'arrivée
            this.vue.majApp(null, getSalle(this.vue.getApp().getFinish().getText()), vue.getApp().getAscenseur().isSelected());
        }
        else if((this.vue.getApp().getStart().estSalle() && this.vue.getApp().getFinish().getText().equals("Arrivée"))){
            //on affiche les portes de la salle de départ
            this.vue.majApp(getSalle(this.vue.getApp().getStart().getText()), null, this.vue.getApp().getAscenseur().isSelected());
        }
    }
    
}