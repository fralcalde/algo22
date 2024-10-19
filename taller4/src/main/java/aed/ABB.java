package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
		Nodo _raiz;
		int _cardinal;
		Nodo _max;
		Nodo _min;

    private class Nodo {
			Nodo _padre;
			T _valor;
			Nodo _izq;
			Nodo _der;

			Nodo(T elem) {
				this._padre = null;
				this._valor = elem;
				this._izq = null;
				this._der = null;
			}
    }

    public ABB() {
			this._raiz = null;
			this._cardinal = 0;
			this._max = null;
			this._min = null;
    }

    public int cardinal() {
			return this._cardinal;
    }

    public T minimo(){
			return this._min._valor;
    }

    public T maximo(){
			return this._max._valor;
    }

    public void insertar(T elem){
			if(_raiz == null) {
				this._raiz = new Nodo(elem);
				this._cardinal += 1;
			} else {
				abb_insert(null, _raiz, elem);
			}
    }

		private void abb_insert(Nodo padre, Nodo actual, T elem) {
			if(actual != null) {
				if(actual._valor.compareTo(elem) < 0) {
					abb_insert(actual, actual._der, elem);
				}
				if(actual._valor.compareTo(elem) > 0) {
					abb_insert(actual, actual._izq, elem);
				}
			}

			if(actual == null) {
				Nodo nodo = new Nodo(elem);
				nodo._padre = padre;
				this._cardinal += 1;

				if(padre != null) {
					if(padre._valor.compareTo(elem) < 0) {
						padre._der = nodo;
					}
					if(padre._valor.compareTo(elem) > 0) {
						padre._izq = nodo;
					}
				}
			}
		}

    public boolean pertenece(T elem){
			return abb_in(_raiz, elem);
    }

		private boolean abb_in(Nodo actual, T elem) {
			boolean res = false;
			if(actual != null) {
				if(actual._valor.compareTo(elem) < 0) {
					res = res || abb_in(actual._izq, elem);
				}
				if(actual._valor.compareTo(elem) > 0) {
					res = res || abb_in(actual._der, elem);
				}
			}

			return actual != null && actual._valor.compareTo(elem) == 0;
		}

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
