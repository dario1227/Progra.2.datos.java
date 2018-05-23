package Structures;

public class Nodo<T> {
    public T objeto;
    public Nodo<T> next;
    public Nodo<T> previo;
    
    public Nodo (T nombre) {
        this.objeto = nombre;
    }
    
    public Nodo () {
    }
    
    public T get_objeto () {
        return this.objeto;
    }
    
    public void set_objeto (T objeto) {
        this.objeto = objeto;
    }
    
    public Nodo<T> get_next () {
        return this.next;
    }
    
    public void set_next (Nodo<T> next) {
        this.next = next;
    }
    
    public Nodo<T> get_previo () {
        return previo;
    }
    
    public void set_previo (Nodo<T> previo) {
        this.previo = previo;
    }
}
