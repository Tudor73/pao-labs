package Local;

import Produs.Produs;

import java.util.List;

public class Cafenea extends Local {
    private List<Produs> meniuCafele;

    public Cafenea(String numeLocal, String program, String adresa, List<Produs> meniuCafele ) {
        super(numeLocal, program, adresa);
        this.meniuCafele = meniuCafele;
    }
    public List<Produs> getMeniuCafele() {
        return meniuCafele;
    }

    public void setMeniuCafele(List<Produs> meniuCafele) {
        this.meniuCafele = meniuCafele;
    }

    @Override
    public void adaugaProdusInMeniu(Produs p) {
        this.meniuCafele.add(p);
    }

    @Override
    public void eliminaProdusDinMeniu(Produs p) {
        this.meniuCafele.remove(p);
    }
}
