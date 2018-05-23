package Structures;

public class ListaSimple<T> {
    public Nodo<T> head;
    public int largo;
    Nodo<T> tail;
    String identificador;
    
    public ListaSimple () {
        this.head = null;
        this.tail = null;
    }
    
    public ListaSimple (String id) {
        this.identificador = id;
        this.head = null;
        this.tail = null;
    }
    
    public void append (T objeto) {
        largo++;
        Nodo<T> nodo = new Nodo<>(objeto);
        if (this.head == null) {
            this.head = nodo;
            this.tail = this.head;
        } else {
            this.tail.set_next(nodo);
            this.tail = this.tail.get_next();
        }
    }
    
    public void eliminar (int posicion) {
        
        if (head == null) {
            return;
        }
        if (posicion == 0 && head != null) {
            System.out.println(this.head.get_objeto());
            this.head = this.head.get_next();
            this.largo--;
            return;
        } else {
            Nodo<T> anterior = null;
            Nodo<T> actual = this.head;
            int indice = 0;
            while (indice != posicion) {
                anterior = actual;
                actual = actual.get_next();
                indice++;
                if (actual == null) {
                    return;
                }
            }
            if (actual == this.tail) {
                anterior.set_next(null);
                this.tail = anterior;
                this.largo--;
                return;
            }
            if (actual != this.tail) {
                anterior.set_next(actual.get_next());
                this.largo--;
                return;
            }
        }
    }
    
    public void print () {
        Nodo<T> actual = this.head;
        while (actual != null) {
            System.out.println(actual.get_objeto());
            actual = actual.get_next();
        }
    }
    
    public T getvalue (int indice) {
        Nodo<T> actual = this.head;
        while (indice != 0 && actual != null) {
            actual = actual.next;
            indice--;
        }
        
        return actual.objeto;
    }
}
