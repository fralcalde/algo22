package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatorios;
    private int usados;

    public ArregloRedimensionableDeRecordatorios() {
        this.recordatorios = new Recordatorio[]{};
    }

    public int longitud() {
        return this.recordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {
        // TODO: Me quede aca
        if(this.usados + 1 >= this.recordatorios.length) {
            Recordatorio[] nueva_lista = new Recordatorio[this.recordatorios.length * 2 + 1];
        }
    }

    public Recordatorio obtener(int i) {
        // Implementar
        return null;
    }

    public void quitarAtras() {
        // Implementar
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        return null;
    }
}
