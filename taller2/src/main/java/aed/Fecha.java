package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        this.dia = fecha.dia;
        this.mes = fecha.mes;
    }

    public Integer dia() {
        return this.dia;
    }

    public Integer mes() {
        return this.mes;
    }

    public String toString() {
        String res = String.format("%s/%s", this.dia, this.mes);
        return res;
    }

    @Override
    public boolean equals(Object otra) {
        boolean otraEsNull = (otra == null);
        boolean otraClase = otra.getClass() != this.getClass();
        if(otraEsNull || otraClase) {
            return false;
        }

        Fecha otraFecha = (Fecha) otra;

        boolean res = this.dia == otraFecha.dia 
            && this.mes == otraFecha.mes;

        return res;
    }

    public void incrementarDia() {
        this.dia += 1;
        if(dia > diasEnMes(this.mes)) {
            this.dia = 1;
            this.mes += 1;
        }

        if(this.mes > 12) {
            this.mes = 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}