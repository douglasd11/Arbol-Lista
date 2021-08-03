/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class app {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArbolG arb = new ArbolG();
        
        Scanner ent = new Scanner(System.in);
        
        System.out.println("Arbol general ordenado...");
        
        System.out.print("Ingrese nombre de nodo raiz: ");
        String name = ent.nextLine();
        arb.setRoot(arb.crearNodo(name));
        
        
        int opc;
        do{
            System.out.println("\n\n---MENU PRINCIPAL---\n");
            
            System.out.println("1) Ingresar nodo ");
            System.out.println("2) Preorden");
            System.out.println("3) Por nivel");
            System.out.println("4) Print arbol");
            System.out.println("5) Mover nodo");
            System.out.println("6) salir");
            
            System.out.print("Ingrese opcion: ");
            opc = ent.nextInt();
            
            switch (opc){
                case 1:{
                    System.out.println("---Ingresar nodo---\n");
                    
                    System.out.println("Ingrese direccion del padre ej: /1/5");
                    String path = ent.next();
                    ent.nextLine();
                    
                    System.out.print("Ingrese nombre del nuevo nodo: ");
                    name = ent.nextLine();
                    
                    if(arb.cd(path) != null){
                        arb.InsertarHijo(arb.cd(path), name);
                        System.out.println("Nodo agregado con exito.");
                    }  
                    else{
                        System.out.println("No se encontro la direccion...");
                    }
                    break;
                }
                case 2:{
                    System.out.println("---Preorder Recursivo---\n");
                    
                    arb.PreorderRecursivo(arb.getRoot());
                    
                    System.out.println("\n---Preorden iterativo---\n");
                    
                    arb.PreorderIterativo();
                    break;
                }
                case 3:{
                    System.out.println("---Por nivel---");
                   
                    arb.PorNivel();
                    break;
                }
                case 4:{
                    System.out.println("---Print arbol---\n");
                    
                    arb.Print(arb.getRoot(),0,0,"0");
                    break;
                }
                case 5:{
                    System.out.println("---Mover nodo---");
                    
                    System.out.print("Ingrese direccion de nodo origen: ");
                    String path1 = ent.next();
                    System.out.print("Ingrese direccion de nodo destino: ");
                    String path2 = ent.next();
                    
                    arb.Mover(arb.cd(path1), arb.cd(path2));
                    
                    break;
                }
                case 6:{
                    System.out.println("Fin del programa.");
                    break;
                }
                default:{
                    System.out.println("Opcion incorrecta...");
                }
            }
                
        }while(opc!=6);
     
    }
}
