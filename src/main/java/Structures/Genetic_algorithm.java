package Structures;

import java.util.ArrayList;
import java.util.Random;

public class Genetic_algorithm {
    String cancion;
    ArrayList<String> lista;
    ArrayList<Palabra> analizadas;
    ArrayList<Palabra> usadas;

    public Genetic_algorithm(String cancion_to){
        cancion =cancion_to;
        lista = new ArrayList<String>();
        analizadas = new ArrayList<>();
        usadas = new ArrayList<>();
    }
 public    void analize_cancion(){
        int largo =cancion.length();
        int index = 0;
        StringBuilder to_append = new StringBuilder();
        while (index<largo){
            while(cancion.charAt(index)==' '){
                if(index!=0&&!to_append.toString().isEmpty()){
                    System.out.print(to_append.toString());
                    analizadas.add(new Palabra(to_append.toString()));
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
                    analizadas.add(new Palabra(to_append.toString()));
                    return;
                }
            }
        }
        return;
    }
    public  Palabra random(){
        Random rand = new Random();
        int randomness = rand.nextInt(analizadas.size());
        Palabra sacada = analizadas.get(randomness);
        usadas.add(sacada);
        analizadas.remove(sacada);
        return sacada;
    }
 public    Palabra palabra_mas_coincidencia(){
        int largo = analizadas.size();
        int index = 0 ;
        Palabra mayor=analizadas.get(0);
        while (index<analizadas.size()){
            if(analizadas.get(index).parecido>mayor.parecido){
                mayor=analizadas.get(index);
            }
            index++;
        }
        usadas.add(mayor);
        analizadas.remove(mayor);
        return  mayor;
    }
    public void reordenate(String palabra,ListaSimple<Integer> indices){
        System.out.print(palabra);
        ArrayList<Palabra> lista = new ArrayList<>();
        int index = 0;
        while (index<analizadas.size()){
            int index2 = 0;
            Palabra palabra_analize = analizadas.get(index);
            while (index2<indices.largo){
                if(indices.getvalue(index2)>=palabra.length()||indices.getvalue(index2)>=palabra_analize.palabra.length()){
                    System.out.print("Me exedi");
                }
                else if (palabra_analize.palabra.charAt(indices.getvalue(index2))==palabra.charAt(indices.getvalue(index2))){
                    palabra_analize.parecido++;
                }
                index2++;
            }
            lista.add(palabra_analize);
            index++;
        }
        analizadas=lista;
    }
}

