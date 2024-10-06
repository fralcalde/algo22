package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
		private Nodo ultimo;
		private int length;

    private class Nodo {
			private T valor;
			private Nodo anterior;
			private Nodo siguiente;
    }

    public ListaEnlazada() {
			this.primero = null;
			this.ultimo = null;
			this.length = 0;
    }

    public int longitud() {
			return this.length;
    }

    public void agregarAdelante(T elem) {
			Nodo nodo = new Nodo();
			nodo.valor = elem;
			nodo.siguiente = this.primero;
			nodo.anterior = null;

			// Si la lista está vacía, tambien se debe actualizar el puntero
			// al último elemento.
			if(this.length == 0) {
				this.ultimo = nodo;
			}
			else{
				this.primero.anterior = nodo;
			}

			this.primero = nodo;
			this.length += 1;
    }

    public void agregarAtras(T elem) {
			Nodo nodo = new Nodo();
			nodo.valor = elem;
			nodo.siguiente = null;
			nodo.anterior = this.ultimo;

			// Si la lista está vacía, también se debe actualizar el puntero
			// al primer elemento.
			if(this.length == 0) {
				this.primero = nodo;
			}
			else {
				this.ultimo.siguiente = nodo;
			}

			this.ultimo = nodo;
			this.length += 1;
    }

    public T obtener(int i) {
			ListaIterador it = (ListaIterador) this.iterador();

			while(i > 0) {
				it.siguiente();
				i -= 1;
			}

			return it.siguiente();
    }

    public void eliminar(int i) {
			Nodo actual = this.primero;

			while(i > 0) {
				actual = actual.siguiente;
				i -= 1;
			}

			this.length -= 1;

			Nodo anterior = actual.anterior;
			Nodo siguiente = actual.siguiente;

			if(anterior != null) {
				anterior.siguiente = siguiente;
			}

			if(siguiente != null) {
				siguiente.anterior = anterior;
			}

			// Si se eliminan los bordes de la lista, se deben actualizar los punteros
			// "primero" o "ultimo";
			if(actual == this.primero) {
				this.primero = actual.siguiente;
			}

			if(actual == this.ultimo) {
				this.ultimo = actual.anterior;
			}
    }

    public void modificarPosicion(int indice, T elem) {
			Nodo actual = this.primero;

			while(indice > 0) {
				actual = actual.siguiente;
				indice -= 1;
			}

			actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
			ListaIterador it = (ListaIterador) lista.iterador();

			if(lista.longitud() > 0) {
				while(it.haySiguiente()) {
					this.agregarAtras(it.siguiente());
				}
			}
    }
    
    @Override
    public String toString() {
			String res = "[";
			ListaIterador it = (ListaIterador) this.iterador();

			while(it.haySiguiente()) {
				res = res + String.format("%s", it.siguiente());

				if(it.haySiguiente()) {
					res = res + ", ";
				} else {
					res = res + "]";
				}
			}

			return res;
    }

    private class ListaIterador implements Iterador<T> {
    	private Nodo actual;

			public boolean haySiguiente() {
				boolean res = hayValor(); // && this.actual.siguiente != null;
				return res;
			}
			
			public boolean hayAnterior() {
				boolean res = this.actual != null && this.actual.anterior != null;
				return res;
			}

			private boolean hayValor() {
				boolean res = this.actual != null && this.actual.valor != null;
				return res;
			}

			public T siguiente() {
				T res = null;

				if(hayValor()) {
					res = this.actual.valor;
				}

				// Esta condición sería la verdadera haySiguiente
				if(this.actual.siguiente != null) {
					this.actual = this.actual.siguiente;
				} else {
					// Fabricando un nodo vacío que apunte al nodo anterior
					// logro mantener la funcionalidad de anterior()
					Nodo vacio = new Nodo();
					vacio.anterior = this.actual;
					vacio.valor = null;
					vacio.siguiente = null;

					this.actual = vacio;
				}

				return res;
			}

			public T anterior() {
				T res = null;

				if(hayAnterior()){
					this.actual = this.actual.anterior;
					res = this.actual.valor;
				}

				return res;
			}
    }

    public Iterador<T> iterador() {
			ListaIterador it = new ListaIterador(); 
			it.actual = this.primero;

			return it;
    }

}
