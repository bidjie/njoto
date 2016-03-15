package ci_agriculture;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by 1199 on 3/14/2016.
 */
public class ListComparator {
    public class Farmer implements Comparable<Farmer> {
        public String cif;
        public Date modified;

        public Farmer(String cif, Date modified) {
            this.cif = cif;
            this.modified = modified;
        }

        public int compareTo(Farmer farmer) {
            return this.cif.compareTo(farmer.cif);
        }
    }

    public void test () {
        Set<Farmer> farmers1 = new TreeSet<Farmer>();
        insertTo(farmers1, new Farmer("a", new Date(System.currentTimeMillis())));
        insertTo(farmers1, new Farmer("b", new Date(System.currentTimeMillis())));
        insertTo(farmers1, new Farmer("c", new Date(System.currentTimeMillis())));
        insertTo(farmers1, new Farmer("d", new Date(System.currentTimeMillis())));
        insertTo(farmers1, new Farmer("e", new Date(System.currentTimeMillis())));

        Set<Farmer> farmers2 = new TreeSet<Farmer>();
        insertTo(farmers2, new Farmer("a", new Date(System.currentTimeMillis())));
        insertTo(farmers2, new Farmer("c", new Date(System.currentTimeMillis())));
        insertTo(farmers2, new Farmer("d", new Date(System.currentTimeMillis())));
        insertTo(farmers2, new Farmer("e", new Date(System.currentTimeMillis())));
        insertTo(farmers2, new Farmer("f", new Date(System.currentTimeMillis())));

        Sets.SetView<Farmer> setView1 = Sets.difference(Sets.union(farmers1, farmers2), Sets.intersection(farmers1, farmers2));
        for (Farmer farmer : new ArrayList<Farmer>(setView1)){
            System.out.print(farmer.cif + " ");
        }
        System.out.println();

    }

    public void insertTo (Set<Farmer> farmers, Farmer farmer) {
        if (farmers.contains(farmer)) System.out.println("sudah ada: " + farmer.cif);
        else farmers.add(farmer);
    }
}
