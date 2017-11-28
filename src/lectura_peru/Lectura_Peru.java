/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectura_peru;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

/**
 *
 * @author X220
 */
public class Lectura_Peru {

    private static void lectura_fichero() {
        try {
            FileReader file = new FileReader("Aging101000031102017.XLS");
            BufferedReader buf = new BufferedReader(file);
            String encabezado="";
            String cad="";
            String arr[];
            int i = 0;
            
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());//17 nombres de las columnas
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            System.out.println(buf.readLine());
            //21 primera fila con valores
            int cant=21;
            while((cad=buf.readLine())!=null){
                if(cant%2!=0){
                     arr = cad.split("\t");
                    System.out.println(cant+" "+cad);
                }
                cant++;
            }
//            System.out.println(cad=buf.readLine().replace("\t", "@@@@@"));
//            System.out.println(cad.trim());
            //cad=buf.readLine();
            int cont=0;
//            while(cad.replace("\t", " ").trim().length()<0){
//                System.out.println("entro");
//            }
//            System.out.println("");
//            System.out.println(cad.replace("\t", "______"));
//            System.out.println(cad.trim());
//            
//            System.out.println(cad.split("\t").length);
            //System.out.println(cad.split("\t")[2]);
           
           
            //ystem.out.println(cad);
//        while((cad=buf.readLine())!=null){
//            //cad=cad.replaceAll("\t", "______________");
////            if(cad.contains("\t")){
////                System.out.println("true");
////                //System.out.println("aparicion "+cad.indexOf("\t"));
////            }
//            //System.out.println(cad.indexOf("\t"));
//            System.out.println(cad);
//            i++;
//        }
            //System.out.println("");
//            System.out.println("cantidad de filas leidas " + i);
        } catch (Exception e) {
            //System.out.println("Error al intentar abrir el fichero");
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        lectura_fichero();

    }

}
