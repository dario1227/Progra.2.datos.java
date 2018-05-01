package Structures;

public class Genetic_algorithm {
    String cancion;
    ListaSimple<String> lista;
    ListaSimple<Palabra> analizadas;
    Genetic_algorithm(String cancion_to){
        cancion =cancion_to;
        lista = new ListaSimple<>();
        analizadas = new ListaSimple<>();
    }
 public    void analize_cancion(){
        int largo =cancion.length();
        int index = 0;
        StringBuilder to_append = new StringBuilder();
        while (index<largo){
            while(cancion.charAt(index)==' '){
                if(index!=0&&!to_append.toString().isEmpty()){
                    analizadas.append(new Palabra(to_append.toString()));
                    to_append=new StringBuilder();
                }
                index++;
                if(index==largo){
                    return;
                }
            }
            while(cancion.charAt(index)!=' '){
                to_append.append(cancion.charAt(index));
                index++;
                if(index==largo){
                    analizadas.append(new Palabra(to_append.toString()));
                    return;
                }
            }
        }
        return;
    }
 public    Palabra palabra_mas_coincidencia(){
        int largo = analizadas.largo;
        int index = 0 ;
        Palabra mayor=analizadas.getvalue(0);
        while (index<analizadas.largo){
            if(analizadas.getvalue(index).parecido>mayor.parecido){
                mayor=analizadas.getvalue(index);
            }
            index++;
        }
        return  mayor;
    }
    public void reordenate(String palabra,ListaSimple<Integer> indices){
        ListaSimple<Palabra> lista = new ListaSimple<>();
        int index = 0;
        while (index<analizadas.largo){
            int index2 = 0;
            Palabra palabra_analize = analizadas.getvalue(index);
            while (index2<indices.largo){
                if(index2>=palabra.length()||index2>=palabra_analize.palabra.length()){
                    break;
                }
                if(palabra_analize.palabra.charAt(indices.getvalue(index2))==palabra.charAt(indices.getvalue(index2))){
                    palabra_analize.parecido++;
                }
                index2++;
            }
            lista.append(palabra_analize);
            index++;
        }
        analizadas=lista;
    }
}

