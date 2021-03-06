/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectura_peru;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.Buffer;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import org.sqlite.util.StringUtils;

/**
 *
 * @author X220
 */
public class Lectura_Peru {
    
    private ArrayList<Partidas_Abiertas> list_pa=new ArrayList<Partidas_Abiertas>();
    //private ArrayList<Partidas_Abiertas> list_pa1=new ArrayList<Partidas_Abiertas>();
    private Partidas_Abiertas pa;
    private  DatabaseUtilities db = new DatabaseUtilities();
    private  String db_name="db_prueba";
    private  String tb_name="partidas_abiertas";
    private  String input_file = "1010002 31 OCTUBRE 17 Colombia.XLS";
    private  String output_file = "LayoutAR_CASA-Provisión - copia.xlsx";
    private  String url;
    private   Map<Integer,String> columnas=new TreeMap<Integer, String>();
    private  Connection connection;
    
    private void lectura_fichero() {
        try {
//            FileReader file = new FileReader("Aging101000031102017.XLS");
            //FileReader file = new FileReader("Aging101000031102017_Ecuador.XLS");
            InputStream is = new FileInputStream(Paths
                    .get(input_file).toFile());
            //BufferedReader buf = new BufferedReader(file);
            //BufferedReader buf = new BufferedReader(new InputStreamReader(is, "8859_1"));
            BufferedReader buf = new BufferedReader(new InputStreamReader(is, "UTF-16"));
            
            String cad="";
            String arr[];
            
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
            
            int cant=10;
            
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
                    //if(cant%2!=0){
                         arr = cad.split("\t");
                         //lista.add(arr);
                        if(arr.length == 45)
                        {     
                            //System.out.println(arr[29]);
                             pa = new Partidas_Abiertas(arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11],
                                    arr[12], arr[13], arr[14], arr[15], arr[16], arr[17], arr[18], arr[19], arr[20], arr[21], arr[22], arr[23],
                                    arr[24], arr[25], arr[26], arr[27], arr[28],
                                     convert_double(arr[29]), 
                                     convert_double(arr[30]),                                      
                                     convert_double(arr[31]), 
                                     convert_double(arr[32]), 
                                     convert_double(arr[33]), 
                                     convert_double(arr[34]), 
                                     convert_double(arr[35]), 
                                     convert_double(arr[36]), 
                                     convert_double(arr[37]), 
                                     convert_double(arr[38]),
                                     arr[39], arr[40], arr[41], arr[42], arr[43], arr[44]);
                             list_pa.add(pa);
                        }
                    //}
                    cant++;
                }
            }
            
                            //System.out.println("size of list_pa "+list_pa.size());
            db.insertIntoPartidas(url, tb_name, columnas, list_pa);

            System.out.println(cant);
            buf.close();


        } catch (Exception e) {
            //System.out.println("Error al intentar abrir el fichero");            
            e.printStackTrace();
        }
    }
    
    private  double convert(String cad){
        double res=0;
        if(cad.indexOf("-") != -1){
            res = Double.parseDouble(cad.replaceAll("\\W", "")) * -1 / 100;
        }else{
            res = Double.parseDouble(cad.replaceAll("\\W", "")) / 100;
        }
        return res;
    }
    //actualmente en uso
    private  double convert_double(String cad){
        if(cad.indexOf(",")!=-1){
            cad=cad.replaceAll(",", "");
        }
        return Double.parseDouble(cad);
    }
    
    private BigDecimal convert_big(String cad) {
        BigDecimal bigDecimal=new BigDecimal(cad);
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            String pattern = "#,##0.0#";
            DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
            decimalFormat.setParseBigDecimal(true);
            if(cad.indexOf(",")!=-1){
                // parse the string
                bigDecimal = (BigDecimal) decimalFormat.parse(cad);
            }else{
                bigDecimal = new BigDecimal(cad);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bigDecimal);
        return bigDecimal;
    }
    
    private  Map<Integer,String> add_campos(){
        columnas = new TreeMap<Integer, String>();

        //columnas.put(0, "Auto_incr");
        columnas.put(0, "Sociedad");
        columnas.put(1, "Numero_Cliente");
        columnas.put(2, "Nombre_Cliente");
        columnas.put(3, "Num_Ident_Fiscal");
        columnas.put(4, "Responsable");
        columnas.put(5, "Texto_Responsable");
        columnas.put(6, "Canal_Ventas");
        columnas.put(7, "Descripcion_Canal");
        columnas.put(8, "Indicador_CME");
        columnas.put(9, "Num_Documento_Compensacion");
        columnas.put(10, "Fecha_Compens");
        columnas.put(11, "Codigo_Sucursal");
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
        columnas.put(27, "Saldo_0_0_días");
        columnas.put(28, "Saldo_1_15_días");
        columnas.put(29, "Saldo_16_21_días");
        columnas.put(30, "Saldo_22_30_días");
        columnas.put(31, "Saldo_31_60_días");
        columnas.put(32, "Saldo_61_90_días");
        columnas.put(33, "Saldo_91_120_días");
        columnas.put(34, "Saldo_121_180_días");
        columnas.put(35, "Saldo_mayor_180_días");
        columnas.put(36, "Saldo");
        columnas.put(37, "Ruta");
        columnas.put(38, "Clasificacion_Cliente");
        columnas.put(39, "Codigo_Industria_1");
        columnas.put(40, "Oficina_Ventas");
        columnas.put(41, "Descripción_Of_Vtas");
        columnas.put(42, "Grupo_Vendedores");
        //System.out.println(columnas.get(0));

        return columnas;
    }
    
    public void escribir_hoja_calc(ArrayList<Partidas_Abiertas> list_pa1) {
    try {
            File file = new File(output_file);
        FileInputStream in = new FileInputStream(file);
         Workbook libro = WorkbookFactory.create(in);
         Sheet hoja_actual = libro.getSheetAt(1);
         int cont_row=9;
         
         //prueba
            
            int j=0;
            int cont=0;
            double sum=0;
            ArrayList<Double>activos=new ArrayList<>();//acumular numeros a tener en en cuenta para las reglas
            ArrayList<Boolean>signos=new ArrayList<>();//signos de los numeros acumulados true para "+" y false para "-"
            double max=0;
             double min=0;
             double abs=0;
             int place=44;
         for(int i=1; i<list_pa1.size(); i++){//
             Row row = hoja_actual.createRow(cont_row);
             Cell cell;
             double saldo=0;
             Partidas_Abiertas partida=list_pa1.get(j);

             
            if(partida.getNumero_Cliente().equals(list_pa1.get(i).getNumero_Cliente()) && partida.getReferencia_Factura().equals(list_pa1.get(i).getReferencia_Factura())){ 
                          
             for(int a=0; a<43; a++){
                     switch(a){
                     case 0: cell = row.createCell(0);  cell.setCellValue(partida.getSociedad());break;
                     case 1: cell = row.createCell(1);  cell.setCellValue(partida.getNumero_Cliente());break;
                     case 2: cell = row.createCell(2);  cell.setCellValue(partida.getNombre_Cliente());break;
                     case 3: cell = row.createCell(3);  cell.setCellValue(partida.getNum_Ident_Fiscal());break;
                     case 4: cell = row.createCell(4);  cell.setCellValue(partida.getResponsable());break;
                     case 5: cell = row.createCell(5);  cell.setCellValue(partida.getTexto_Responsable());break;
                     case 6: cell = row.createCell(6);  cell.setCellValue(partida.getCanal_Ventas());break;
                     case 7: cell = row.createCell(7);  cell.setCellValue(partida.getDescripcion_Canal());break;
                     case 8: cell = row.createCell(8);  cell.setCellValue(partida.getIndicador_CME());break;
                     case 9: cell = row.createCell(9);  cell.setCellValue(partida.getNum_Documento_Compensacion());break;
                     case 10: cell = row.createCell(10);  cell.setCellValue(partida.getFecha_Compens());break;
                     case 11: cell = row.createCell(11);  cell.setCellValue(partida.getCodigo_Sucursal());break;
                     case 12: cell = row.createCell(12);  cell.setCellValue(partida.getNombre_Sucursal());break;
                     case 13: cell = row.createCell(13);  cell.setCellValue(partida.getClase_Documento());break;
                     case 14: cell = row.createCell(14);  cell.setCellValue(partida.getNumero_Documento());break;
                     case 15: cell = row.createCell(15);  cell.setCellValue(partida.getEjercicio());break;
                     case 16: cell = row.createCell(16);  cell.setCellValue(partida.getReferencia());break;
                     case 17: cell = row.createCell(17);  cell.setCellValue(partida.getFe_Contable());break;
                     case 18: cell = row.createCell(18);  cell.setCellValue(partida.getFecha_Documento());break;
                     case 19: cell = row.createCell(19);  cell.setCellValue(partida.getFecha_Base());break;
                     case 20: cell = row.createCell(20);  cell.setCellValue(partida.getDescripcion_Cond_Pago());break;
                     case 21: cell = row.createCell(21);  cell.setCellValue(partida.getOrigen_diferencias());break;
                     case 22: cell = row.createCell(22);  cell.setCellValue(partida.getIndicador_Debe_Haber());break;
                     case 23: cell = row.createCell(23);  cell.setCellValue(partida.getReferencia_Factura());break;
                     case 24: cell = row.createCell(24);  cell.setCellValue(partida.getEjercicio_Referencia());break;
                     case 25: cell = row.createCell(25);  cell.setCellValue(partida.getFecha_Vencimiento());break;
                     case 26: cell = row.createCell(26);  cell.setCellValue(partida.getCondicion_Pago());break;
                         case 27:
                             cell = row.createCell(27);
                             cell.setCellValue(partida.getSaldo_0_0_días());
                             if (partida.getSaldo_0_0_días() != 0) {
                                 activos.add(partida.getSaldo_0_0_días());
                                 sum += partida.getSaldo_0_0_días();
                                 place = 44;
                                 if(abs<Math.abs(partida.getSaldo_0_0_días()))
                                     abs=partida.getSaldo_0_0_días();
                                 if (partida.getSaldo_0_0_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 28:
                             cell = row.createCell(28);
                             cell.setCellValue(partida.getSaldo_1_15_días());
                             if (partida.getSaldo_1_15_días() != 0) {
                                 activos.add(partida.getSaldo_1_15_días());
                                 sum += partida.getSaldo_1_15_días();
                                 place = 45;
                                 if(abs<partida.getSaldo_1_15_días())
                                     abs=partida.getSaldo_1_15_días();
                                 if (partida.getSaldo_1_15_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 29:
                             cell = row.createCell(29);
                             cell.setCellValue(partida.getSaldo_16_21_días());
                             if (partida.getSaldo_16_21_días() != 0) {
                                 activos.add(partida.getSaldo_16_21_días());
                                 sum += partida.getSaldo_16_21_días();
                                 if(abs<partida.getSaldo_16_21_días())
                                     abs=partida.getSaldo_16_21_días();
                                 place = 46;                                 
                                 if (partida.getSaldo_16_21_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 30:
                             cell = row.createCell(30);
                             cell.setCellValue(partida.getSaldo_22_30_días());
                             if (partida.getSaldo_22_30_días() != 0) {
                                 activos.add(partida.getSaldo_22_30_días());
                                 sum += partida.getSaldo_22_30_días();
                                 if(abs<partida.getSaldo_22_30_días())
                                     abs=partida.getSaldo_22_30_días();
                                 place = 47;
                                 if (partida.getSaldo_22_30_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 31:
                             cell = row.createCell(31);
                             cell.setCellValue(partida.getSaldo_31_60_días());
                             if (partida.getSaldo_31_60_días() != 0) {
                                 activos.add(partida.getSaldo_31_60_días());
                                 sum += partida.getSaldo_31_60_días();
                                 if(abs<partida.getSaldo_31_60_días())
                                     abs=partida.getSaldo_31_60_días();
                                 place = 48;
                                 if (partida.getSaldo_31_60_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 32:
                             cell = row.createCell(32);
                             cell.setCellValue(partida.getSaldo_61_90_días());
                             if (partida.getSaldo_61_90_días() != 0) {
                                 activos.add(partida.getSaldo_61_90_días());
                                 sum += partida.getSaldo_61_90_días();
                                 if(abs<partida.getSaldo_61_90_días())
                                     abs=partida.getSaldo_61_90_días();
                                 place = 49;
                                 if (partida.getSaldo_61_90_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 33:
                             cell = row.createCell(33);
                             cell.setCellValue(partida.getSaldo_91_120_días());
                             if (partida.getSaldo_91_120_días() != 0) {
                                 activos.add(partida.getSaldo_91_120_días());
                                 sum += partida.getSaldo_91_120_días();
                                 if(abs<partida.getSaldo_91_120_días())
                                     abs=partida.getSaldo_91_120_días();
                                 place = 50;
                                 if (partida.getSaldo_91_120_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 34:
                             cell = row.createCell(34);
                             cell.setCellValue(partida.getSaldo_121_180_días());
                             if (partida.getSaldo_121_180_días() != 0) {
                                 activos.add(partida.getSaldo_121_180_días());
                                 sum += partida.getSaldo_121_180_días();
                                 if(abs<partida.getSaldo_121_180_días())
                                     abs=partida.getSaldo_121_180_días();
                                 place = 51;
                                 if (partida.getSaldo_121_180_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 35:
                             cell = row.createCell(35);
                             cell.setCellValue(partida.getSaldo_mayor_180_días());
                             if (partida.getSaldo_mayor_180_días() != 0) {
                                 activos.add(partida.getSaldo_mayor_180_días());
                                 sum += partida.getSaldo_mayor_180_días();
                                 if(abs<partida.getSaldo_mayor_180_días())
                                     abs=partida.getSaldo_mayor_180_días();
                                 place = 52;
                                 if (partida.getSaldo_mayor_180_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 36:
                             cell = row.createCell(36);
                             cell.setCellValue(partida.getSaldo());
                             saldo = partida.getSaldo();
                             break;
                     case 37: cell = row.createCell(37);  cell.setCellValue(partida.getRuta());break;
                     case 38: cell = row.createCell(38);  cell.setCellValue(partida.getClasificacion_Cliente());break;
                     case 39: cell = row.createCell(39);  cell.setCellValue(partida.getCodigo_Industria_1());break;
                     case 40: cell = row.createCell(40);  cell.setCellValue(partida.getOficina_Ventas());break;
                     case 41: cell = row.createCell(41);  cell.setCellValue(partida.getDescripción_Of_Vtas());break;
                     case 42: cell = row.createCell(42);  cell.setCellValue(partida.getGrupo_Vendedores());break;
                 }
                     
                 }
             
             //System.out.println(partida.getNumero_Cliente()+"  "+partida.getReferencia_Factura()+"      "+list_pa1.get(i).getNumero_Cliente()+"  "+list_pa1.get(i).getReferencia_Factura( ));
             
                     cell = row.createCell(44);  cell.setCellValue(0.00);
                     cell = row.createCell(45);  cell.setCellValue(0.00);
                     cell = row.createCell(46);  cell.setCellValue(0.00);
                     cell = row.createCell(47);  cell.setCellValue(0.00);
                     cell = row.createCell(48);  cell.setCellValue(0.00);
                     cell = row.createCell(49);  cell.setCellValue(0.00);
                     cell = row.createCell(50);  cell.setCellValue(0.00);
                     cell = row.createCell(51);  cell.setCellValue(0.00);
                     cell = row.createCell(52);  cell.setCellValue(0.00);
                     
                     //Provisión
                     if(partida.getSaldo_31_60_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(redondeo(partida.getSaldo_31_60_días()*10/100, i));
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_31_60_días()*10/100, 2));
                     }else if(partida.getSaldo_61_90_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(redondeo(partida.getSaldo_61_90_días()*20/100, 2));
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_61_90_días()*20/100, 2));
                     }else if(partida.getSaldo_91_120_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(redondeo(partida.getSaldo_91_120_días()*50/100, 2));
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_91_120_días()*50/100, 2));
                     }else if(partida.getSaldo_121_180_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(redondeo(partida.getSaldo_121_180_días()*50/100, 2));
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_121_180_días()*50/100, 2));
                     }else if(partida.getSaldo_mayor_180_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(partida.getSaldo_mayor_180_días());
                         cell = row.createCell(59);  cell.setCellValue(partida.getSaldo_mayor_180_días());
                     }else{
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(0.00);
                     }
                 
                 System.out.println("*_J");
                 cont++;
             }else{
                 if(cont>0){
                     System.out.println("*_J_2");
                     
                     
                   for(int a = 0; a < 43; a++) {//no tengo que hacerlo dentro de un ciclo
                    switch(a){
                     case 0: cell = row.createCell(0);  cell.setCellValue(partida.getSociedad());break;
                     case 1: cell = row.createCell(1);  cell.setCellValue(partida.getNumero_Cliente());break;
                     case 2: cell = row.createCell(2);  cell.setCellValue(partida.getNombre_Cliente());break;
                     case 3: cell = row.createCell(3);  cell.setCellValue(partida.getNum_Ident_Fiscal());break;
                     case 4: cell = row.createCell(4);  cell.setCellValue(partida.getResponsable());break;
                     case 5: cell = row.createCell(5);  cell.setCellValue(partida.getTexto_Responsable());break;
                     case 6: cell = row.createCell(6);  cell.setCellValue(partida.getCanal_Ventas());break;
                     case 7: cell = row.createCell(7);  cell.setCellValue(partida.getDescripcion_Canal());break;
                     case 8: cell = row.createCell(8);  cell.setCellValue(partida.getIndicador_CME());break;
                     case 9: cell = row.createCell(9);  cell.setCellValue(partida.getNum_Documento_Compensacion());break;
                     case 10: cell = row.createCell(10);  cell.setCellValue(partida.getFecha_Compens());break;
                     case 11: cell = row.createCell(11);  cell.setCellValue(partida.getCodigo_Sucursal());break;
                     case 12: cell = row.createCell(12);  cell.setCellValue(partida.getNombre_Sucursal());break;
                     case 13: cell = row.createCell(13);  cell.setCellValue(partida.getClase_Documento());break;
                     case 14: cell = row.createCell(14);  cell.setCellValue(partida.getNumero_Documento());break;
                     case 15: cell = row.createCell(15);  cell.setCellValue(partida.getEjercicio());break;
                     case 16: cell = row.createCell(16);  cell.setCellValue(partida.getReferencia());break;
                     case 17: cell = row.createCell(17);  cell.setCellValue(partida.getFe_Contable());break;
                     case 18: cell = row.createCell(18);  cell.setCellValue(partida.getFecha_Documento());break;
                     case 19: cell = row.createCell(19);  cell.setCellValue(partida.getFecha_Base());break;
                     case 20: cell = row.createCell(20);  cell.setCellValue(partida.getDescripcion_Cond_Pago());break;
                     case 21: cell = row.createCell(21);  cell.setCellValue(partida.getOrigen_diferencias());break;
                     case 22: cell = row.createCell(22);  cell.setCellValue(partida.getIndicador_Debe_Haber());break;
                     case 23: cell = row.createCell(23);  cell.setCellValue(partida.getReferencia_Factura());break;
                     case 24: cell = row.createCell(24);  cell.setCellValue(partida.getEjercicio_Referencia());break;
                     case 25: cell = row.createCell(25);  cell.setCellValue(partida.getFecha_Vencimiento());break;
                     case 26: cell = row.createCell(26);  cell.setCellValue(partida.getCondicion_Pago());break;
                         case 27:
                             cell = row.createCell(27);
                             cell.setCellValue(partida.getSaldo_0_0_días());
                             if (partida.getSaldo_0_0_días() != 0) {
                                 activos.add(partida.getSaldo_0_0_días());
                                 sum += partida.getSaldo_0_0_días();
                                 place = 44;
                                 if(abs<Math.abs(partida.getSaldo_0_0_días()))
                                     abs=partida.getSaldo_0_0_días();
                                 if (partida.getSaldo_0_0_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 28:
                             cell = row.createCell(28);
                             cell.setCellValue(partida.getSaldo_1_15_días());
                             if (partida.getSaldo_1_15_días() != 0) {
                                 activos.add(partida.getSaldo_1_15_días());
                                 sum += partida.getSaldo_1_15_días();
                                 place = 45;
                                 if(abs<partida.getSaldo_1_15_días())
                                     abs=partida.getSaldo_1_15_días();
                                 if (partida.getSaldo_1_15_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 29:
                             cell = row.createCell(29);
                             cell.setCellValue(partida.getSaldo_16_21_días());
                             if (partida.getSaldo_16_21_días() != 0) {
                                 activos.add(partida.getSaldo_16_21_días());
                                 sum += partida.getSaldo_16_21_días();
                                 if(abs<partida.getSaldo_16_21_días())
                                     abs=partida.getSaldo_16_21_días();
                                 place = 46;                                 
                                 if (partida.getSaldo_16_21_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 30:
                             cell = row.createCell(30);
                             cell.setCellValue(partida.getSaldo_22_30_días());
                             if (partida.getSaldo_22_30_días() != 0) {
                                 activos.add(partida.getSaldo_22_30_días());
                                 sum += partida.getSaldo_22_30_días();
                                 if(abs<partida.getSaldo_22_30_días())
                                     abs=partida.getSaldo_22_30_días();
                                 place = 47;
                                 if (partida.getSaldo_22_30_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 31:
                             cell = row.createCell(31);
                             cell.setCellValue(partida.getSaldo_31_60_días());
                             if (partida.getSaldo_31_60_días() != 0) {
                                 activos.add(partida.getSaldo_31_60_días());
                                 sum += partida.getSaldo_31_60_días();
                                 if(abs<partida.getSaldo_31_60_días())
                                     abs=partida.getSaldo_31_60_días();
                                 place = 48;
                                 if (partida.getSaldo_31_60_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 32:
                             cell = row.createCell(32);
                             cell.setCellValue(partida.getSaldo_61_90_días());
                             if (partida.getSaldo_61_90_días() != 0) {
                                 activos.add(partida.getSaldo_61_90_días());
                                 sum += partida.getSaldo_61_90_días();
                                 if(abs<partida.getSaldo_61_90_días())
                                     abs=partida.getSaldo_61_90_días();
                                 place = 49;
                                 if (partida.getSaldo_61_90_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 33:
                             cell = row.createCell(33);
                             cell.setCellValue(partida.getSaldo_91_120_días());
                             if (partida.getSaldo_91_120_días() != 0) {
                                 activos.add(partida.getSaldo_91_120_días());
                                 sum += partida.getSaldo_91_120_días();
                                 if(abs<partida.getSaldo_91_120_días())
                                     abs=partida.getSaldo_91_120_días();
                                 place = 50;
                                 if (partida.getSaldo_91_120_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 34:
                             cell = row.createCell(34);
                             cell.setCellValue(partida.getSaldo_121_180_días());
                             if (partida.getSaldo_121_180_días() != 0) {
                                 activos.add(partida.getSaldo_121_180_días());
                                 sum += partida.getSaldo_121_180_días();
                                 if(abs<partida.getSaldo_121_180_días())
                                     abs=partida.getSaldo_121_180_días();
                                 place = 51;
                                 if (partida.getSaldo_121_180_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 35:
                             cell = row.createCell(35);
                             cell.setCellValue(partida.getSaldo_mayor_180_días());
                             if (partida.getSaldo_mayor_180_días() != 0) {
                                 activos.add(partida.getSaldo_mayor_180_días());
                                 sum += partida.getSaldo_mayor_180_días();
                                 if(abs<partida.getSaldo_mayor_180_días())
                                     abs=partida.getSaldo_mayor_180_días();
                                 place = 52;
                                 if (partida.getSaldo_mayor_180_días() > 0){
                                     signos.add(true);
                                 }else{
                                     signos.add(false);
                                 }
                             }
                             ;
                             break;
                         case 36:
                             cell = row.createCell(36);
                             cell.setCellValue(partida.getSaldo());
                             saldo = partida.getSaldo();
                             break;
                     case 37: cell = row.createCell(37);  cell.setCellValue(partida.getRuta());break;
                     case 38: cell = row.createCell(38);  cell.setCellValue(partida.getClasificacion_Cliente());break;
                     case 39: cell = row.createCell(39);  cell.setCellValue(partida.getCodigo_Industria_1());break;
                     case 40: cell = row.createCell(40);  cell.setCellValue(partida.getOficina_Ventas());break;
                     case 41: cell = row.createCell(41);  cell.setCellValue(partida.getDescripción_Of_Vtas());break;
                     case 42: cell = row.createCell(42);  cell.setCellValue(partida.getGrupo_Vendedores());break;
                                      
                 }
                 
             }                     
                     
                     if(sum==0){
                        cell = row.createCell(44);  cell.setCellValue(0.00);
                        cell = row.createCell(45);  cell.setCellValue(0.00);
                        cell = row.createCell(46);  cell.setCellValue(0.00);
                        cell = row.createCell(47);  cell.setCellValue(0.00);
                        cell = row.createCell(48);  cell.setCellValue(0.00);
                        cell = row.createCell(49);  cell.setCellValue(0.00);
                        cell = row.createCell(50);  cell.setCellValue(0.00);
                        cell = row.createCell(51);  cell.setCellValue(0.00);
                        cell = row.createCell(52);  cell.setCellValue(0.00);
                     }else{//suma diferente de 0 tratar dentro demas casos                         
                         if(signos.indexOf(true)!=-1 && signos.indexOf(false)!=-1){//teniendo en cuenta que solo vamos a tener un positivo
                             cell = row.createCell(44);  cell.setCellValue(0.00);
                             cell = row.createCell(45);  cell.setCellValue(0.00);
                             cell = row.createCell(46);  cell.setCellValue(0.00);
                             cell = row.createCell(47);  cell.setCellValue(0.00);
                             cell = row.createCell(48);  cell.setCellValue(0.00);
                             cell = row.createCell(49);  cell.setCellValue(0.00);
                             cell = row.createCell(50);  cell.setCellValue(0.00);
                             cell = row.createCell(51);  cell.setCellValue(0.00);
                             cell = row.createCell(52);  cell.setCellValue(0.00);
                             int marca=signos.indexOf(true);
                             Row row_aux = hoja_actual.getRow(cont_row+1-(signos.size()-marca));                           
                             Cell cell_aux=row_aux.getCell(place); 
                             //cell_aux.setCellValue(activos.get(marca));
                             cell_aux.setCellValue(sum);
                         }
                         else if(signos.indexOf(true)==-1){
                             int marca=activos.indexOf(abs);
                             Row row_aux = hoja_actual.getRow(cont_row+1-(activos.size()-marca));
                             System.out.println("momento del fallo: "+cont_row);
                             Cell cell_aux=row_aux.getCell(place); 
                             //cell_aux.setCellValue(activos.get(marca));
                             cell_aux.setCellValue(sum);
                         }
                     }
                     //Provisión
                     if(partida.getSaldo_31_60_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(redondeo(partida.getSaldo_31_60_días()*10/100, i));
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_31_60_días()*10/100, 2));
                     }else if(partida.getSaldo_61_90_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(redondeo(partida.getSaldo_61_90_días()*20/100, 2));
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_61_90_días()*20/100, 2));
                     }else if(partida.getSaldo_91_120_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(redondeo(partida.getSaldo_91_120_días()*50/100, 2));
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_91_120_días()*50/100, 2));
                     }else if(partida.getSaldo_121_180_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(redondeo(partida.getSaldo_121_180_días()*50/100, 2));
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_121_180_días()*50/100, 2));
                     }else if(partida.getSaldo_mayor_180_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(partida.getSaldo_mayor_180_días());
                         cell = row.createCell(59);  cell.setCellValue(partida.getSaldo_mayor_180_días());
                     }else{
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(0.00);
                     }
                     signos.clear();
                     activos.clear();
                     sum=0;
                     abs=0;
                     cont=0;
                 }else{
                     System.out.println("Ç_I");
                     
                     for(int a = 0; a < 53; a++) {//no tengo que hacerlo dentro de un ciclo
                    switch(a){
                     case 0: cell = row.createCell(0);  cell.setCellValue(partida.getSociedad());break;
                     case 1: cell = row.createCell(1);  cell.setCellValue(partida.getNumero_Cliente());break;
                     case 2: cell = row.createCell(2);  cell.setCellValue(partida.getNombre_Cliente());break;
                     case 3: cell = row.createCell(3);  cell.setCellValue(partida.getNum_Ident_Fiscal());break;
                     case 4: cell = row.createCell(4);  cell.setCellValue(partida.getResponsable());break;
                     case 5: cell = row.createCell(5);  cell.setCellValue(partida.getTexto_Responsable());break;
                     case 6: cell = row.createCell(6);  cell.setCellValue(partida.getCanal_Ventas());break;
                     case 7: cell = row.createCell(7);  cell.setCellValue(partida.getDescripcion_Canal());break;
                     case 8: cell = row.createCell(8);  cell.setCellValue(partida.getIndicador_CME());break;
                     case 9: cell = row.createCell(9);  cell.setCellValue(partida.getNum_Documento_Compensacion());break;
                     case 10: cell = row.createCell(10);  cell.setCellValue(partida.getFecha_Compens());break;
                     case 11: cell = row.createCell(11);  cell.setCellValue(partida.getCodigo_Sucursal());break;
                     case 12: cell = row.createCell(12);  cell.setCellValue(partida.getNombre_Sucursal());break;
                     case 13: cell = row.createCell(13);  cell.setCellValue(partida.getClase_Documento());break;
                     case 14: cell = row.createCell(14);  cell.setCellValue(partida.getNumero_Documento());break;
                     case 15: cell = row.createCell(15);  cell.setCellValue(partida.getEjercicio());break;
                     case 16: cell = row.createCell(16);  cell.setCellValue(partida.getReferencia());break;
                     case 17: cell = row.createCell(17);  cell.setCellValue(partida.getFe_Contable());break;
                     case 18: cell = row.createCell(18);  cell.setCellValue(partida.getFecha_Documento());break;
                     case 19: cell = row.createCell(19);  cell.setCellValue(partida.getFecha_Base());break;
                     case 20: cell = row.createCell(20);  cell.setCellValue(partida.getDescripcion_Cond_Pago());break;
                     case 21: cell = row.createCell(21);  cell.setCellValue(partida.getOrigen_diferencias());break;
                     case 22: cell = row.createCell(22);  cell.setCellValue(partida.getIndicador_Debe_Haber());break;
                     case 23: cell = row.createCell(23);  cell.setCellValue(partida.getReferencia_Factura());break;
                     case 24: cell = row.createCell(24);  cell.setCellValue(partida.getEjercicio_Referencia());break;
                     case 25: cell = row.createCell(25);  cell.setCellValue(partida.getFecha_Vencimiento());break;
                     case 26: cell = row.createCell(26);  cell.setCellValue(partida.getCondicion_Pago());break;
                     case 27: cell = row.createCell(27);  cell.setCellValue(partida.getSaldo_0_0_días());break;
                     case 28: cell = row.createCell(28);  cell.setCellValue(partida.getSaldo_1_15_días());break;
                     case 29: cell = row.createCell(29);  cell.setCellValue(partida.getSaldo_16_21_días());break;
                     case 30: cell = row.createCell(30);  cell.setCellValue(partida.getSaldo_22_30_días());break;
                     case 31: cell = row.createCell(31);  cell.setCellValue(partida.getSaldo_31_60_días());break;
                     case 32: cell = row.createCell(32);  cell.setCellValue(partida.getSaldo_61_90_días());break;
                     case 33: cell = row.createCell(33);  cell.setCellValue(partida.getSaldo_91_120_días());break;
                     case 34: cell = row.createCell(34);  cell.setCellValue(partida.getSaldo_121_180_días());break;
                     case 35: cell = row.createCell(35);  cell.setCellValue(partida.getSaldo_mayor_180_días());break;
                     case 36: cell = row.createCell(36);  cell.setCellValue(partida.getSaldo());break;
                     case 37: cell = row.createCell(37);  cell.setCellValue(partida.getRuta());break;
                     case 38: cell = row.createCell(38);  cell.setCellValue(partida.getClasificacion_Cliente());break;
                     case 39: cell = row.createCell(39);  cell.setCellValue(partida.getCodigo_Industria_1());break;
                     case 40: cell = row.createCell(40);  cell.setCellValue(partida.getOficina_Ventas());break;
                     case 41: cell = row.createCell(41);  cell.setCellValue(partida.getDescripción_Of_Vtas());break;
                     case 42: cell = row.createCell(42);  cell.setCellValue(partida.getGrupo_Vendedores());break;
                     //en la 43 no se hace nada
                     case 44: cell = row.createCell(44);  cell.setCellValue(partida.getSaldo_0_0_días());break;
                     case 45: cell = row.createCell(45);  cell.setCellValue(partida.getSaldo_1_15_días());break;
                     case 46: cell = row.createCell(46);  cell.setCellValue(partida.getSaldo_16_21_días());break;
                     case 47: cell = row.createCell(47);  cell.setCellValue(partida.getSaldo_22_30_días());break;
                     case 48: cell = row.createCell(48);  cell.setCellValue(partida.getSaldo_31_60_días());break;
                     case 49: cell = row.createCell(49);  cell.setCellValue(partida.getSaldo_61_90_días());break;
                     case 50: cell = row.createCell(50);  cell.setCellValue(partida.getSaldo_91_120_días());break;
                     case 51: cell = row.createCell(51);  cell.setCellValue(partida.getSaldo_121_180_días());break;
                     case 52: cell = row.createCell(52);  cell.setCellValue(partida.getSaldo_mayor_180_días());break;
                 }
                 
             }
                     if(partida.getSaldo_31_60_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(redondeo(partida.getSaldo_31_60_días()*10/100, i));
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_31_60_días()*10/100, 2));
                     }else if(partida.getSaldo_61_90_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(redondeo(partida.getSaldo_61_90_días()*20/100, 2));
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_61_90_días()*20/100, 2));
                     }else if(partida.getSaldo_91_120_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(redondeo(partida.getSaldo_91_120_días()*50/100, 2));
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_91_120_días()*50/100, 2));
                     }else if(partida.getSaldo_121_180_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(redondeo(partida.getSaldo_121_180_días()*50/100, 2));
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(redondeo(partida.getSaldo_121_180_días()*50/100, 2));
                     }else if(partida.getSaldo_mayor_180_días()>0){
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(partida.getSaldo_mayor_180_días());
                         cell = row.createCell(59);  cell.setCellValue(partida.getSaldo_mayor_180_días());
                     }else{
                         cell = row.createCell(54);  cell.setCellValue(0.00);
                         cell = row.createCell(55);  cell.setCellValue(0.00);
                         cell = row.createCell(56);  cell.setCellValue(0.00);
                         cell = row.createCell(57);  cell.setCellValue(0.00);
                         cell = row.createCell(58);  cell.setCellValue(0.00);
                         cell = row.createCell(59);  cell.setCellValue(0.00);
                     }
                     
                 }
             }
             //PARA TRATAR LA ULTIMA FILA Revisar****
             if(i==list_pa1.size()-1){
                 Partidas_Abiertas last_row=list_pa1.get(i);
                 if(cont>0){
                     System.out.println("*_I_FINAL ");
                 }else{
                     System.out.println("Ç_I_FINAL");
                 }
             }
             j++;
             cont_row++;
         }
         
//        cell.setCellValue("hola");
        FileOutputStream fileOut = new FileOutputStream(output_file);
        libro.write(fileOut);
        fileOut.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
         
    }
    
    private void poblar_excel(){
        try {            
        ArrayList<Partidas_Abiertas> list_pa1=db.sin_duplicar(url, tb_name, columnas);        
        escribir_hoja_calc(list_pa1);
        //System.out.println(list_pa1.get(0).getNumero_Cliente());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public double redondeo(double num, int cifras) {
        int aux;
        if (cifras == 2) {
            num = num + 0.005;
            num = num * 100;
            aux = (int) num;
            num = (double) aux / 100;
        } else if (cifras == 1) {
            num = num + 0.05;
            num = num * 10;
            aux = (int) num;
            num = (double) aux / 10;
        }
        return num;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        // TODO code application logic here
        Lectura_Peru per=new Lectura_Peru();
        per.lectura_fichero();
        per.poblar_excel();

    }

}
