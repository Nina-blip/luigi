package be.vdab.luigi.domain;

public class Adres {
    private final String straat, huisNr, gemeente;
    private final int postcode;

    public Adres(String straat, String huisNr, String gemeente, int postcode) {
        this.straat = straat;
        this.huisNr = huisNr;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public String getGemeente() {
        return gemeente;
    }

    public int getPostcode() {
        return postcode;
    }
}
