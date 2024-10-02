package aed;

public class Agenda {
	private Fecha fecha_actual;
	private ArregloRedimensionableDeRecordatorios recordatorios;

	public Agenda(Fecha fechaActual) {
		this.fecha_actual = new Fecha(fechaActual);
		this.recordatorios = new ArregloRedimensionableDeRecordatorios();
	}

	public void agregarRecordatorio(Recordatorio recordatorio) {
		this.recordatorios.agregarAtras(recordatorio);
	}

	@Override
	public String toString() {
		String res = String.format(
				"%s%n=====%n",
				this.fechaActual().toString()
		);

		for(int i = 0; i < this.recordatorios.longitud(); i++) {
			if(this.recordatorios.obtener(i).fecha().equals(this.fecha_actual)) {
				res = res + String.format("%s%n", this.recordatorios.obtener(i));
			}
		}

		return res;
	}

	public void incrementarDia() {
		this.fecha_actual.incrementarDia();
	}

	public Fecha fechaActual() {
		Fecha res = new Fecha(this.fecha_actual);
		return res;
	}

}
