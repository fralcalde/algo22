package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatorios;
    private int usados;

    public ArregloRedimensionableDeRecordatorios() {
        this.recordatorios = new Recordatorio[]{};
				this.usados = 0;
    }

    public int longitud() {
        return this.usados;
    }

    public void agregarAtras(Recordatorio i) {
        if(this.usados + 1 >= this.recordatorios.length) {

						this.recordatorios = copiarRefsAListaExpandida();
        }

				this.recordatorios[usados] = i;
				this.usados += 1;
    }

    public Recordatorio obtener(int i) {
        return this.recordatorios[i];
    }

    public void quitarAtras() {
        this.recordatorios[this.usados] = null;
				this.usados -= 1;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        this.recordatorios[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
				this.recordatorios = copiarElemsAlista(vector.recordatorios, vector.usados);
				this.usados = vector.usados;
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
			ArregloRedimensionableDeRecordatorios res = new ArregloRedimensionableDeRecordatorios(this);
        return res;
    }

		private Recordatorio[] copiarRefsAListaExpandida() {
				Recordatorio[] nueva_lista = new Recordatorio[this.recordatorios.length * 2 + 1];

				for(int j = 0; j < this.recordatorios.length; j++){
					nueva_lista[j] = this.recordatorios[j];
				}

				return nueva_lista;
		}

		private Recordatorio[] copiarElemsAlista(Recordatorio[] rh, int hasta) {
			Recordatorio[] nueva_lista = new Recordatorio[rh.length];

			for(int j = 0; j < hasta; j++) {
				nueva_lista[j] = new Recordatorio(rh[j]);
			}

			return nueva_lista;
		}
}
