package general;

import exceptions.ForbiddenMoveException;

/*
    Attributs : 
        - tailleCases 
    Methodes : 
        + Carte(nbLignes : int, nbColonnes : int)
        + getNbLignes() : int
        + getNbColonnes() : int
        + getTailleCases() : int
        + getCase(lig, col : int) : Case
        + voisinExiste(src : case, dir : Direction) : boolean
        + getVoisin(src : Case, dir : Direction) : Case
*/
public class Carte {
    private int tailleCases;
    private int nbLignes;
    private int nbColonnes;
    private Case[][] tabCases;
    
    
    /*
        Constructeur Carte : 
            -   créé un tableau de nbLignes et nbColonnes à partir
                du pointeur tabCases, initialise le terrain à unset
    */
    public Carte(int lignes, int colonnes, int tailleC) {
        tailleCases = tailleC;
        nbLignes = lignes;
        nbColonnes = colonnes;
        tabCases = new Case[nbLignes][nbColonnes];
        for(int i=0; i<nbLignes; i++){
           for(int j=0; j<nbColonnes; j++){
               tabCases[i][j] = new Case(i, j, NatureTerrain.UNSET);
           } 
        }
    }
    
    
    
    public int getNbLignes() {
        return nbLignes;
    }
    public int getNbColonnes() {
        return nbColonnes;
    }
    public int getTailleCases() {
        return tailleCases;
    }

    public void setTailleCases(int tailleCases) {
        this.tailleCases = tailleCases;
    }

    public Case getCase(int ligne, int colonne) {
        return tabCases[ligne][colonne]; 
    }
    
    public Case getCase(int num) {
        return tabCases[num/nbColonnes][num%nbColonnes]; 
    }
    
    public int getNumCase(Case cases) {
        return cases.getColonne() + cases.getLigne()*nbColonnes;
    }


       /* public boolean voisinExiste(Case src, Direction dir) {
        int ligneCase = src.getLigne();
        int colonneCase = src.getColonne();

        switch (dir) {
            case NORD:
                if (ligneCase == 0) {
                    return false;
                } else {
                    return true;
                }
            case SUD:
                if (ligneCase >= nbLignes - 1) {
                    return false;
                } else {
                    return true;
                }
            case EST:
                if (colonneCase >= nbColonnes - 1) {
                    return false;
                } else {
                    return true;
                }
            case OUEST:
                if (colonneCase == 0) {
                    return false;
                } else {
                    return true;
                }
        }
        return false;
    }*/

   
    public Case getVoisin(Case src, Direction dir) throws ForbiddenMoveException {
        // Vérification que le voisin existe bien
        switch (dir) {
            case NORD:
                if (src.getLigne() <= 0) throw new ForbiddenMoveException(src, dir);
                return getCase(src.getLigne() - 1, src.getColonne());
            case SUD:
                if (src.getLigne() >= this.nbLignes - 1) throw new ForbiddenMoveException(src, dir);
                return getCase(src.getLigne() + 1, src.getColonne());
            case EST:
                if (src.getColonne() >= this.nbColonnes - 1) throw new ForbiddenMoveException(src, dir);
                return getCase(src.getLigne(), src.getColonne() + 1);
            case OUEST:
                if (src.getColonne() <= 0) throw new ForbiddenMoveException(src, dir);
                return getCase(src.getLigne(), src.getColonne() - 1);
        }
        return null;
    }

}
