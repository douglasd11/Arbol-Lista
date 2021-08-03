/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author User
 */
public class ArbolG {

    private class NodoArbol {
        
        String nombre;
        ArrayList<NodoArbol> lista;

        NodoArbol(String elNombre) {
            this.nombre = elNombre;
            this.lista = new ArrayList<>();
        }
        NodoArbol(String elNombre, ArrayList<NodoArbol> lista) {
            this.nombre = elNombre;
            this.lista = lista;
        }
    }

    private NodoArbol root; // Puntero a la raiz del arbol

    // Constructor
    public ArbolG() {
        this.root = null;
    }

    public NodoArbol getRoot() {
        return root;
    }

    public void setRoot(NodoArbol root) {
        this.root = root;
    }
    
   
    NodoArbol crearNodo(String name) {
        return new NodoArbol(name);
    }
    
    NodoArbol copiaNodo(NodoArbol copia){
        return new NodoArbol(copia.nombre, copia.lista);
    }
    
    
    // Metodos a implementar
    void InsertarHijo(NodoArbol n, String Nombre) {

        NodoArbol nuevoN = new NodoArbol(Nombre);
        n.lista.add(nuevoN);
    }

    NodoArbol cd(String path) {

        String partes[];
        partes = path.split("/");

        return recorridoArbol(root, partes, 1);
    }

    NodoArbol recorridoArbol(NodoArbol n, String partes[], int ite) {

        if (n == null) {
            return null;
        }
        if (n.nombre.equals(partes[ite])) {

            if (ite == partes.length - 1) {
                return n;
            }
            ite++;

            NodoArbol aux = null;
            for (int i = 0; i < n.lista.size(); i++) {
                aux = recorridoArbol(n.lista.get(i), partes, ite);
                if (aux != null) {
                    break;
                }
            }
            return aux;
        }
        return null;
    }

    int cantidadNiveles(NodoArbol n, int nivel) {

        if (n == null) {
            return 0;
        }
        int res, aux = nivel;
        
        for (int i = 0; i < n.lista.size(); i++) {
            res = cantidadNiveles(n.lista.get(i), nivel+1);
            if(aux<res){
                aux = res;
            }
        }
        return aux;
    }

    void PreorderRecursivo(NodoArbol n) {

        if (n == null) {
            return;
        }
        System.out.print(n.nombre + ", ");
        for (int i = 0; i < n.lista.size(); i++) {
            PreorderRecursivo(n.lista.get(i));
        }
    }
    
    void PreorderIterativo(){
        
        Stack<NodoArbol> pila = new Stack<>();
        
        NodoArbol aux = root;
        
        do{
            if(aux!=null){
                System.out.print(aux.nombre + ", ");
            }
            if(!pila.empty()){
                pila.pop();
            }
            if(aux!=null){
                for(int i=aux.lista.size()-1; i>=0; i--){
                    pila.push(aux.lista.get(i));
                }
            }
            if(!pila.empty()){
                aux = pila.peek();
            }
            
        }while(!pila.empty());
        
    }

    void PorNivel() {

        String niveles[] = new String[cantidadNiveles(root, 0)+1];
        for(int i=0; i<niveles.length; i++)
            niveles[i] = "";
        
        guardarNivel(root, 0, niveles);
        
        System.out.println("Niveles del arbol");
        System.out.print("<");
        for (String nivel : niveles) {
            System.out.print(nivel);
        }
        System.out.println(">");
    }

    void guardarNivel(NodoArbol nP, int nivel, String niveles[]) {
        if (nP == null) {
            return;
        }
        niveles[nivel] += nP.nombre+", ";
        for (int i = 0; i < nP.lista.size(); i++) {
            guardarNivel(nP.lista.get(i), nivel+1, niveles);
        }
    }

    void Print(NodoArbol n, int nivel, int pos, String varas) {
        if(n == null){
            return;
        }
        
        if(pos==0){
            if(nivel!=0) System.out.print("---+---");
        }
        else{
            for(int i=0; i<nivel; i++){
                
                if(i<varas.length() && varas.charAt(i)=='1'){
                    System.out.print("|       ");
                }
                else{
                    if(i==0) System.out.print("    ");
                    else System.out.print("        ");
                }
            }
            System.out.print("|---");
        }
        System.out.print(n.nombre);
        
        if(n.lista.isEmpty()) System.out.println("");
        
        for(int i=0; i<n.lista.size(); i++){
            String aux = varas;
            if(i!=n.lista.size()-1) aux += '1';
            else aux += '0';
            
            Print(n.lista.get(i), nivel+1, i, aux);
            
            if(i==n.lista.size()-1){
                for(int j=0; j<nivel+1; j++){
                
                    if(j<aux.length() && aux.charAt(j)=='1'){
                        System.out.print("|       ");
                    }
                    else{
                        if(j==0) System.out.print("    ");
                        else System.out.print("        ");
                    }
                }
                System.out.println("");
            }
        }
    }
    

    void Mover(NodoArbol origen, NodoArbol destino) {
        
        destino.lista.add(copiaNodo(origen));
        borrarNodo(root,origen);
    }
    
    void borrarNodo(NodoArbol n,NodoArbol nodo){
        if(n==null){
            return;
        }
        for(int i = 0; i < n.lista.size(); i++) {
            if(n.lista.get(i).equals(nodo)){
                n.lista.remove(i);
                break;
            }
            else{
                borrarNodo(n.lista.get(i), nodo);
            }
        }
    }

}
