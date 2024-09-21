package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public Horario(Horario rh) {
        this.hora = rh.hora;
        this.minutos = rh.minutos;
    }

    public int hora() {
        return this.hora;
    }

    public int minutos() {
        return this.minutos;
    }

    @Override
    public String toString() {
        String res = String.format("%s:%s", this.hora, this.minutos);
        return res;
    }

    @Override
    public boolean equals(Object otro) {
        boolean otroEsNull = (otro == null);
        boolean esOtraClase = (otro.getClass() != this.getClass());
        if(otroEsNull || esOtraClase) {
            return false;
        }

        Horario rh = (Horario) otro;

        boolean res = (this.hora == rh.hora) && (this.minutos == rh.minutos);

        return res;
    }

}
