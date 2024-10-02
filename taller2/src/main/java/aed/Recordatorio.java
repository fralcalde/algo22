package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = new String(mensaje);
        this.fecha = new Fecha(fecha);
        this.horario = new Horario(horario);
    }

		public Recordatorio(Recordatorio rh) {
			this.mensaje = new String(rh.mensaje);
			this.fecha = new Fecha(rh.fecha);
			this.horario = new Horario(rh.horario);
		}

    public Horario horario() {
        return new Horario(this.horario);
    }

    public Fecha fecha() {
        return new Fecha(this.fecha);
    }

    public String mensaje() {
        return new String(this.mensaje); 
    }

    @Override
    public String toString() {
        String res = String.format(
            "%s @ %s %s",
            this.mensaje,
            this.fecha,
            this.horario
        );

        return res;
    }

    @Override
    public boolean equals(Object otro) {
        boolean otroEsNull = (otro == null);
        boolean esOtraClase = (otro.getClass() != this.getClass());
        if(otroEsNull || esOtraClase) {
            return false;
        }

        Recordatorio rh = (Recordatorio) otro;
        
        boolean res = (this.mensaje.equals(rh.mensaje))
            && (this.fecha.equals(rh.fecha))
            && (this.horario.equals(rh.horario));

        return res;
    }
}
