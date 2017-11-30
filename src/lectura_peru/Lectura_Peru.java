/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectura_peru;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.sqlite.util.StringUtils;

/**
 *
 * @author X220
 */
public class Lectura_Peru {
    
    public ArrayList<Partidas_Abiertas> list_pa=new ArrayList<Partidas_Abiertas>();
    public Partidas_Abiertas pa;
    private static DatabaseUtilities db = new DatabaseUtilities();
    private static String db_name="db_prueba";
    private static String tb_name="partidas_abiertas";
    private static String url;
    private static Map<Integer,String> columnas=new TreeMap<Integer, String>();
    private static Connection connection;
    
    private void lectura_fichero() {
        try {
//            FileReader file = new FileReader("Aging101000031102017.XLS");
            FileReader file = new FileReader("Aging101000031102017_Ecuador.XLS");
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
            
//             File file1=new File("db_prueba.db");//direccion fija por el momento
//            if(file1.exists()){
//                file1.delete();
//                System.out.println(file1.exists());
//            }
            
            url=db.createNewDatabase(db_name);
            add_campos();
            //columnas.get(0);
            db.createTable(url, tb_name, columnas);
            
            //stream.forEach((s)->System.out.println(s));
            int contador = 11; 
            //ArrayList<String[]> lista = new ArrayList<String[]>();
            while((cad=buf.readLine())!=null){ 
                if(cad.split("\t").length == 45)
                {
                    if(cant%2!=0){
                         arr = cad.split("\t");
                         //lista.add(arr);
                        if(arr.length == 45)
                        {     
                            //System.out.println(arr[38]+"   "+convert(arr[38]));
                             pa = new Partidas_Abiertas(arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11],
                                    arr[12], arr[13], arr[14], arr[15], arr[16], arr[17], arr[18], arr[19], arr[20], arr[21], arr[22], arr[23],
                                    arr[24], arr[25], arr[26], arr[27], arr[28],
                                     convert(arr[29]), 
                                     convert(arr[30]), 
                                     convert(arr[31]), 
                                     convert(arr[32]), 
                                     convert(arr[33]), 
                                     convert(arr[34]), 
                                     convert(arr[35]), 
                                     convert(arr[36]), 
                                     convert(arr[37]), 
                                     convert(arr[38]),
                                     arr[39], arr[40], arr[41], arr[42], arr[43], arr[44]);
                            list_pa.add(pa);
                        }
                    }
                    cant++;
                }
            }
            db.insertIntoPartidas(url, tb_name, columnas, list_pa);

            System.out.println(cant);
            buf.close();


        } catch (Exception e) {
            //System.out.println("Error al intentar abrir el fichero");
            e.printStackTrace();
        }
    }
    
    private static double convert(String cad){
        double res=0;
        if(cad.indexOf("-") != -1){
            res = Double.parseDouble(cad.replaceAll("\\W", "")) * -1 / 100;
        }else{
            res = Double.parseDouble(cad.replaceAll("\\W", "")) / 100;
        }
        return res;
    }
    
    private static Map<Integer,String> add_campos(){
        columnas = new TreeMap<Integer, String>();

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
        //System.out.println(columnas.get(0));

        return columnas;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Lectura_Peru per=new Lectura_Peru();
                per.lectura_fichero();

    }

}
