package model;

import java.util.ArrayList;

public class Salle{
    //Attributs

    /**
     * identifiant de la salle
     */
    public final String id;

    /**
     * liste des portes qui mènent à la salle
     */
    private ArrayList<Porte> portes;

    //Constructeurs

    Salle(String id){
        this.id = id;
        portes  = new ArrayList<Porte>();
    }

    //Méthodes

    /**
     * ajoute une porte à la liste de portes qui mènent à la salle
     * @param porte
     */
    public void addPorte(Porte porte){
        portes.add(porte);
    }

    /**
     * renvoie la liste des portes qui mènent à la salle
     * @return renvoie la liste des portes qui mènent à la salle
     */
    public ArrayList<Porte> getPortes(){
        return portes;
    }
}