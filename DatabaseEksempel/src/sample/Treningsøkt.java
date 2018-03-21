package sample;

import javafx.beans.property.SimpleStringProperty;

public class Treningsøkt {
    private final SimpleStringProperty Dato;
    private final SimpleStringProperty Tid;
    private final SimpleStringProperty Varighet;
    private final SimpleStringProperty Prestasjon;
    private final SimpleStringProperty Informasjon;

    public Treningsøkt(String dato, String tid, String varighet, String prestasjon, String informasjon){
        this.Dato = new SimpleStringProperty(dato);
        this.Tid = new SimpleStringProperty(tid);
        this.Varighet = new SimpleStringProperty(varighet);
        this.Prestasjon = new SimpleStringProperty(prestasjon);
        this.Informasjon = new SimpleStringProperty(informasjon);
    }


    public String getDato() {
        return Dato.get();
    }

    public SimpleStringProperty datoProperty() {
        return Dato;
    }

    public void setDato(String dato) {
        this.Dato.set(dato);
    }

    public String getTid() {
        return Tid.get();
    }

    public SimpleStringProperty tidProperty() {
        return Tid;
    }

    public void setTid(String tid) {
        this.Tid.set(tid);
    }

    public String getVarighet() {
        return Varighet.get();
    }

    public SimpleStringProperty varighetProperty() {
        return Varighet;
    }

    public void setVarighet(String varighet) {
        this.Varighet.set(varighet);
    }

    public String getPrestasjon() {
        return Prestasjon.get();
    }

    public SimpleStringProperty prestasjonProperty() {
        return Prestasjon;
    }

    public void setPrestasjon(String prestasjon) {
        this.Prestasjon.set(prestasjon);
    }

    public String getInformasjon() {
        return Informasjon.get();
    }

    public SimpleStringProperty informasjonProperty() {
        return Informasjon;
    }

    public void setInformasjon(String informasjon) {
        this.Informasjon.set(informasjon);
    }
}
