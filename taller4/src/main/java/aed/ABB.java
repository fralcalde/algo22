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

  public T minimo() {
    return this._min._valor;
  }

  public T maximo() {
    return this._max._valor;
  }

  public void insertar(T elem) {
    if (_raiz == null) {
      Nodo nodo = new Nodo(elem);
      this._raiz = nodo;
      this._cardinal += 1;
      this._min = nodo;
      this._max = nodo;
    } else {
      abb_insert(null, _raiz, elem);
    }
  }

  private void abb_insert(Nodo padre, Nodo actual, T elem) {
    if (actual != null) {
      if (actual._valor.compareTo(elem) < 0) {
        abb_insert(actual, actual._der, elem);
      }

      if (actual._valor.compareTo(elem) > 0) {
        abb_insert(actual, actual._izq, elem);
      }
    }

    if (actual == null) {
      Nodo nodo = new Nodo(elem);
      nodo._padre = padre;
      this._cardinal += 1;

      update_max_min(nodo);

      if (padre != null) {
        if (padre._valor.compareTo(elem) < 0) {
          padre._der = nodo;
        }

        if (padre._valor.compareTo(elem) > 0) {
          padre._izq = nodo;
        }
      }
    }
  }

  private void update_max_min(Nodo nodo) {
    if (this._max._valor.compareTo(nodo._valor) < 0) {
      this._max = nodo;
    }

    if (this._min._valor.compareTo(nodo._valor) > 0) {
      this._min = nodo;
    }
  }

  public boolean pertenece(T elem) {
    return abb_in(_raiz, elem);
  }

  private boolean abb_in(Nodo actual, T elem) {
    boolean res = false;
    if (actual != null) {
      if (actual._valor.compareTo(elem) < 0) {
        res = abb_in(actual._der, elem);
      }

      if (actual._valor.compareTo(elem) > 0) {
        res = abb_in(actual._izq, elem);
      }
    }

    return res || actual != null && actual._valor.compareTo(elem) == 0;
  }

  public void eliminar(T elem) {
    // Buscar nodo que se quiere borrar.
    // checkear si cae en uno de 3 casos:
    // 1. Es una hoja
    // 2. Tiene solo un hijo.
    // 3. Tiene dos hijos.
    // Recomendacion: Hacer una función específica para cada caso.
    Nodo target_node = seek_node(this._raiz, elem);

    abb_delete(target_node);
  }

  private void abb_delete(Nodo target) {
    if (target != null) {
      if (target._izq == null && target._der == null) {
        abb_delete_leaf(target);
      } else if (target._izq != null ^ target._der != null) {
        abb_delete_with_one_child(target);
      } else {
        abb_delete_with_two_children(target);
      }

      this._cardinal -= 1;

      // Actualizar _max y _min. Se podría haber eliminado uno de ellos.
      if (this._max == target) {
        this._max = seek_maximum();
      }
      if (this._min == target) {
        this._min = seek_minimum();
      }
    }
  }

  private void abb_delete_leaf(Nodo target) {
    if (this._raiz == target) {
      this._raiz = null;
    } else {
      Nodo parent = target._padre;

      if (parent._valor.compareTo(target._valor) < 0) {
        parent._der = null;
      }

      if (parent._valor.compareTo(target._valor) > 0) {
        parent._izq = null;
      }
    }
  }

  private void abb_delete_with_one_child(Nodo target) {
    Nodo parent = target._padre;

    // Actualizar punteros del hijo
    Nodo child = null;
    if (target._izq != null) {
      child = target._izq;
    } else {
      child = target._der;
    }
    child._padre = parent;

    if (this._raiz != target) {
      // Actualizar punteros del padre
      if (parent._valor.compareTo(target._valor) < 0) {
        parent._der = child;
      }

      if (parent._valor.compareTo(target._valor) > 0) {
        parent._izq = child;
      }
    } else {
      // Si borramos la raiz, el hijo es la nueva raiz
      this._raiz = child;
    }
  }

  private void abb_delete_with_two_children(Nodo target) {
    Nodo succesor = seek_immediate_succesor(target);
    T succ_value = succesor._valor;

    // Borrar el succesor
    // No uso la func abb_delete porque podría reducir
    // la cardinalidad mas de lo deseado
    if (succesor._izq == null && succesor._der == null) {
      abb_delete_leaf(succesor);
    } else if (succesor._izq != null ^ succesor._der != null) {
      abb_delete_with_one_child(succesor);
    }

    target._valor = succ_value;
    // Ademas no se tiene en cuenta el caso que se borra la raiz
    // porque no se elimina el nodo target, si no que se swapea
    // los valores con el sucesor, y luego se elimina el sucesor.
    // Quien es imposible que haya sido la raiz.
  }

  private Nodo seek_node(Nodo actual, T elem) {
    Nodo res = actual;

    if (actual != null && actual._valor.compareTo(elem) < 0) {
      res = seek_node(actual._der, elem);
    } else if (actual != null && actual._valor.compareTo(elem) > 0) {
      res = seek_node(actual._izq, elem);
    }

    return res;
  }

  private Nodo seek_immediate_succesor(Nodo node) {
    Nodo res = node._der;

    // No tiene subarbol derecho.
    if (node._der == null) {
      Nodo parent = node._padre;
      Nodo child = node;

      // Debemos checkear si hay padre. En el caso que se pide el sucesor
      // al elemento mas grande de todos, queremos devolver null.
      while (parent != null && parent._der._valor.equals(child._valor)) {
        child = parent;
        parent = parent._padre;
      }

      res = parent;
    } else {
      // Tiene subarbol derecho. Caso facil.
      // Ya avancé una pos a la derecha.
      // Busco el ejemplar mas a la izquierda.
      while (res._izq != null) {
        res = res._izq;
      }
    }

    return res;
  }

  private Nodo seek_minimum() {
    Nodo new_min = _raiz;
    while (new_min != null && new_min._izq != null) {
      new_min = new_min._izq;
    }

    return new_min;
  }

  private Nodo seek_maximum() {
    Nodo new_max = _raiz;
    while (new_max != null && new_max._der != null) {
      new_max = new_max._der;
    }

    return new_max;
  }

  public String toString() {
    String res = "{";
    Iterador<T> it = this.iterador();

    while (it.haySiguiente()) {
      res = res + String.format("%s", it.siguiente());

      if (it.haySiguiente()) {
        res = res + String.format(",");
      }
    }

    res = res + "}";
    return res;
  }

  private class ABB_Iterador implements Iterador<T> {
    private Nodo _actual;

    public boolean haySiguiente() {
      boolean haySiguiente = this._actual != null;
      // && (this._actual._der != null || this._actual._padre != null);
      return haySiguiente;
    }

    public T siguiente() {
      T value = this._actual._valor;
      this._actual = seek_immediate_succesor(this._actual);

      return value;
    }
  }

  public Iterador<T> iterador() {
    ABB_Iterador it = new ABB_Iterador();
    it._actual = this._min;

    return it;
  }
}
