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
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import lectura_peru.Partidas_Abiertas;

/**
 *
 * @author X220
 */
public class Lectura_Peru {
    
    private static ArrayList<Partidas_Abiertas> list_pa=new ArrayList<>();
    private static Partidas_Abiertas pa;
    private static DatabaseUtilities db = new DatabaseUtilities();
    private static String db_name="db_prueba";
    private static String url;
    private static Map<Integer,String> columnas;
    
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
            
            url=db.createNewDatabase(db_name);
            
//            while((cad=buf.readLine())!=null){  
//                if(cant%2!=0){
//                     arr = cad.split("\t");
//                    pa = new Partidas_Abiertas(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11],
//                            arr[12], arr[13], arr[14], arr[15], arr[16], arr[17], arr[18], arr[19], arr[20], arr[21], arr[22], arr[23],
//                            arr[24], arr[25], arr[26], arr[27], arr[28], arr[29], arr[30], arr[31], arr[32], arr[33], arr[34], arr[35],
//                            arr[36], arr[37], arr[38], arr[39], arr[40], arr[41], arr[42]);
//                    list_pa.add(pa);
//                     
//                    System.out.println(cant+" "+cad);
//                }
//                cant++;
//            }


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
    
    private static Map<Integer,String> add_campos(){
        columnas=new TreeMap<Integer, String>();
        
        columnas.put(0, "Auto_incr");
            columnas.put(1, "Sociedad");
            columnas.put(2, "Numero_Cliente");
            columnas.put(3, "Nombre_Cliente");
            columnas.put(4, "Num_Ident_Fiscal");
            columnas.put(5, "Responsable");
            columnas.put(6, "Texto_Responsable");
            columnas.put(7, "Canal_Ventas");
            columnas.put(8, "Descripcion_Canal");
            columnas.put(9, "Indicador_CME");
            columnas.put(10, "Num_Documento_Compensacion");
            columnas.put(11, "Fecha_Compens");
            columnas.put(12, "Nombre_Sucursal");
            columnas.put(13, "Clase_Documento");
            columnas.put(14, "Numero_Documento");
            columnas.put(15, "Ejercicio");
            columnas.put(16, "Referencia");
            columnas.put(17, "Fe_Contable");
            columnas.put(18, "Fecha_Documento");
            columnas.put(19, "Fecha_Base");
            columnas.put(20, "Descripcion_Cond_Pago");
            columnas.put(21, "Origen_diferencias");
            columnas.put(22, "Indicador_Debe_Haber");
            columnas.put(23, "Referencia_Factura");
            columnas.put(24, "Ejercicio_Referencia");
            columnas.put(25, "Fecha_Vencimiento");
            columnas.put(26, "Condicion_Pago");
            columnas.put(27, "Codigo_Sucursal");
            columnas.put(28, "Saldo_0_0_días");
            columnas.put(29, "Saldo_1_15_días");
            columnas.put(30, "Saldo_16_21_días");
            columnas.put(31, "Saldo_22_30_días");
            columnas.put(32, "Saldo_31_60_días");
            columnas.put(33, "Saldo_61_90_días");
            columnas.put(34, "Saldo_91_120_días");
            columnas.put(35, "Saldo_121_180_días");
            columnas.put(36, "Saldo_mayor_180_días");
            columnas.put(37, "Saldo");
            columnas.put(38, "Ruta");
            columnas.put(39, "Clasificacion_Cliente");
            columnas.put(40, "Codigo_Industria_1");
            columnas.put(41, "Oficina_Ventas");
            columnas.put(42, "Descripción_Of_Vtas");
            columnas.put(43, "Grupo_Vendedores");
            
            return columnas;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        lectura_fichero();

    }

}
