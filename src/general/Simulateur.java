
package general;

import general.evenements.Evenement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author 66
 */
public class Simulateur {
    private long dateSimulation;
    private HashMap<Long, ArrayList<Evenement>> listEvenements;
    
    public Simulateur() {
        this.dateSimulation = 0;
        this.listEvenements = new HashMap<>();
    }

    public long getDateSimulation() {
        return dateSimulation;
    }
    
    public void setDateSImulation(long dateSimulation) {
        this.dateSimulation = dateSimulation;
    }

    public HashMap<Long, ArrayList<Evenement>> getListEvenements() {
        return listEvenements;
    }
    
    public void incrementeDate() {
        dateSimulation ++;
        
        //ececute
    }
    
    public void addEvenement(Evenement e) {
        if (listEvenements.containsKey(e.getDate())) {
            listEvenements.get(e.getDate()).add(e);
        } else {
            ArrayList<Evenement> list = new ArrayList<Evenement>();
            list.add(e);
            listEvenements.put(e.getDate(), list);
        }
    }
    
    public boolean simulationTerminee() {
        for (Long dateEvenement : listEvenements.keySet()) {
            if (dateEvenement > this.dateSimulation) return false;
        }
        return true;
    }
    
}
