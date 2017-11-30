/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectura_peru;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author X220
 */
public class DatabaseUtilities {
    
    public static String createNewDatabase(String dbname) {
        String url = null;
        try {
            Class.forName("org.sqlite.JDBC");
            //System.out.println("Existe la Clase");
        } catch (ClassNotFoundException e) {
            //System.out.println("Error: " + e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
        }
        try {
            url = "jdbc:sqlite:"+dbname+".db";
        } catch (Exception e) {
            //System.out.println("Error: " + e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
        }
        
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                //System.out.println("db has been created");
            }
        } catch (SQLException e) 
        {
            //System.out.println("Error: " + e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
        }
        return url;
    }
    
    public static Connection connectDatabase(String url) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            //server.info("Connection to SQLite has been established.");
            //System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            //System.out.println("Error: " + e.getMessage()); 
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
        }
        return conn;
    }
    
    public static void createTable(String url, String tableName, Map<Integer, String> columns) {
        try {
            Connection connection = connectDatabase(url);
            Statement statement = connection.createStatement();
            StringBuilder createTableQuery = new StringBuilder();
            createTableQuery.append("CREATE TABLE IF NOT EXISTS ");
            createTableQuery.append(tableName);
            createTableQuery.append(" (");
            String fields = getTableColumnInformation(tableName, columns);
            /*String fields = headers.values().stream()
                    .map(s -> {
                        switch(s) {
                            case "id": return s + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT";
                            case "numero_folio":
                            case "nombre":
                                return s + " TEXT NOT NULL";
                            default:
                                return s + " TEXT NOT NULL";
                        }
                    }).collect(Collectors.joining(","));*/
            createTableQuery.append(fields);
            createTableQuery.append(")");
            statement.executeUpdate(createTableQuery.toString());
            statement.close();
            connection.close();
            //System.out.println("Creacion de Tabla Exitosamente");
        } catch (SQLException e) {
            //System.out.println("Error: " + e.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            
        }
    }
    
    public static String getTableColumnInformation(String tableName, Map<Integer, String> columnName)
    {
        String tableColumnInformation = "";
        if(tableName != null)
        {
            tableName = tableName.replace(" ", "");
            if(tableName.length() > 0)
            {               
                tableColumnInformation = aging_peru(columnName);                    
            }
        }
        return tableColumnInformation;
    }
    
    public static String aging_peru(Map<Integer, String> columns)
    {
        String columnParameters = ""; 
        if(columns != null)
        {
            if(columns.size() > 0)
            {
                for(int a = 0; a < columns.size(); a++)
                {
                    if(a==0){
                        columnParameters += columns.get(a) + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,";
                    }else{
                        columnParameters += columns.get(a) + " TEXT NOT NULL,";
                    }
                }
                columnParameters = columnParameters.substring(0, columnParameters.lastIndexOf(","));
            }
        }
        //System.out.println(columnParameters);
        return columnParameters;
    }
    
    
    
    public static String partidas_abiertasInformation(Map<Integer, String> columns)
    {
        String partidasInformation = "";
        //if(columns != null && reception != null)
        if(columns != null)
        {
            if(columns.size() > 0)
            {
                for(int a = 0; a < columns.size(); a++)
                {
                    if (a == columns.size() - 1) {
                        partidasInformation += "?";
                    } else if(a==0){
                        partidasInformation += "NULL,";
                    }else{
                        partidasInformation += "?,";
                    }                  
                }
            }
        }
        System.out.println(partidasInformation);
        return partidasInformation;
    }
    
    
        public static void insertIntoPartidas(String url, String tableName, Map<Integer, String> columns, ArrayList<Partidas_Abiertas> lista_pa)
    {
        try
        {
            int times = 0;
            String partidas_abiertasInformation = "";
            Connection connection = connectDatabase(url);
            connection.setAutoCommit(false);
            //Statement statement = connection.createStatement(); 
            PreparedStatement prepareStatement = null; 
            StringBuilder sqlQuery = new StringBuilder();
            sqlQuery.append("INSERT INTO partidas_abiertas");
            sqlQuery.append(" (");
            if(columns != null)
            {
                for(int a = 0; a < columns.size(); a++)
                {
                    sqlQuery.append(columns.get(a));
                    sqlQuery.append(",");
                }
                sqlQuery.deleteCharAt(sqlQuery.lastIndexOf(","));
            }
            
            sqlQuery.append(")"); 
            sqlQuery.append(" VALUES");
            sqlQuery.append(" (");
            System.out.println(sqlQuery);
            partidas_abiertasInformation = partidas_abiertasInformation(columns);
            sqlQuery.append(partidas_abiertasInformation);
            sqlQuery.append(");");
            prepareStatement = connection.prepareStatement(sqlQuery.toString());
            
            for(Partidas_Abiertas partidas: lista_pa)
            {                
                prepareStatement.setString(1, partidas.getSociedad());
                prepareStatement.setString(2, partidas.getNumero_Cliente());
                prepareStatement.setString(3, partidas.getNombre_Cliente());
                prepareStatement.setString(4, partidas.getNum_Ident_Fiscal());
                prepareStatement.setString(5, partidas.getResponsable());
                prepareStatement.setString(6, partidas.getTexto_Responsable());
                prepareStatement.setString(7, partidas.getCanal_Ventas());
                prepareStatement.setString(8, partidas.getDescripcion_Canal());
                prepareStatement.setString(9, partidas.getIndicador_CME());
                prepareStatement.setString(10, partidas.getNum_Documento_Compensacion());
                prepareStatement.setString(11, partidas.getFecha_Compens());
                prepareStatement.setString(12, partidas.getNombre_Sucursal());
                prepareStatement.setString(13, partidas.getClase_Documento());
                prepareStatement.setString(14, partidas.getNumero_Documento());
                prepareStatement.setString(15, partidas.getEjercicio());
                prepareStatement.setString(16, partidas.getReferencia());
                prepareStatement.setString(17, partidas.getFe_Contable());
                prepareStatement.setString(18, partidas.getFecha_Documento());
                prepareStatement.setString(19, partidas.getFecha_Base());
                prepareStatement.setString(20, partidas.getDescripcion_Cond_Pago());
                prepareStatement.setString(21, partidas.getOrigen_diferencias());
                prepareStatement.setString(22, partidas.getIndicador_Debe_Haber());
                prepareStatement.setString(23, partidas.getReferencia_Factura());
                prepareStatement.setString(24, partidas.getEjercicio_Referencia());
                prepareStatement.setString(25, partidas.getFecha_Vencimiento());
                prepareStatement.setString(26, partidas.getCondicion_Pago());
                prepareStatement.setString(27, partidas.getCodigo_Sucursal());
                prepareStatement.setDouble(28, partidas.getSaldo_0_0_días());
                prepareStatement.setDouble(29, partidas.getSaldo_1_15_días());
                prepareStatement.setDouble(30, partidas.getSaldo_16_21_días());
                prepareStatement.setDouble(31, partidas.getSaldo_22_30_días());
                prepareStatement.setDouble(32, partidas.getSaldo_31_60_días());
                prepareStatement.setDouble(33, partidas.getSaldo_61_90_días());
                prepareStatement.setDouble(34, partidas.getSaldo_91_120_días());
                prepareStatement.setDouble(35, partidas.getSaldo_121_180_días());
                prepareStatement.setDouble(36, partidas.getSaldo_mayor_180_días());
                prepareStatement.setDouble(37, partidas.getSaldo());
                prepareStatement.setString(38, partidas.getRuta());
                prepareStatement.setString(39, partidas.getClasificacion_Cliente());
                prepareStatement.setString(40, partidas.getCodigo_Industria_1());
                prepareStatement.setString(41, partidas.getOficina_Ventas());
                prepareStatement.setString(42, partidas.getDescripción_Of_Vtas());
                prepareStatement.setString(43, partidas.getGrupo_Vendedores());
                prepareStatement.addBatch();
                times++;
                
                if(times % 1000 == 0 || times == lista_pa.size())
                {
                    prepareStatement.executeBatch();
                }
            }
            //prepareStatement.executeUpdate();
            //statement.executeUpdate(sqlQuery.toString());
            //statement.close();
            prepareStatement.executeBatch();
            prepareStatement.close();
            connection.commit();
            connection.close();
        }
        catch(SQLException e)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
        }
    }
    
}
