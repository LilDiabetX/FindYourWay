package model;

/**
 * Class qui représente un groupe de deux Noeud
 */
public class Binome {
    public Noeud depart;
    public Noeud arrivee;

    public Binome(Noeud depart, Noeud arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public Noeud getDepart() {
        return depart;
    }

    public Noeud getArrivee() {
        return arrivee;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Binome)) {
            return false;
        }
        Binome b = (Binome) obj;
        if (this.depart == null && b.depart == null) {
            if (this.arrivee != null) {
                return this.arrivee.equals(b.arrivee);
            }
            return b.arrivee == null;
        }
        if (this.arrivee == null && b.arrivee == null) {
            if (this.depart != null) {
                return this.depart.equals(b.depart);
            }
            return b.depart == null;
        }
        if (this.depart == null || this.arrivee == null) {
            return false;
        }
        return this.depart.equals(b.depart) && this.arrivee.equals(b.arrivee);
    }

    @Override
    public String toString() {
        if (depart == null) {
            if (arrivee == null) {
                return "null";
            }
            return ((Salle) arrivee).getNom();
        }
        if (arrivee == null) {
            return ((Salle) depart).getNom();
        }
        return ((Salle) depart).getNom() + " ==> " + ((Salle) arrivee).getNom();
    }
}
