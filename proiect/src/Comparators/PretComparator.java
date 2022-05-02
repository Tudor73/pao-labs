package Comparators;

import Comanda.Comanda;

import java.util.Comparator;

public class PretComparator implements Comparator<Comanda> {
    @Override
    public int compare(Comanda c1, Comanda c2) {
        return c2.getPret() - c1.getPret();
    }
}
