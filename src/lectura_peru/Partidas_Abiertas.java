/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectura_peru;

import java.math.BigDecimal;

/**
 *
 * @author X220
 */
public class Partidas_Abiertas {
    
    private String Sociedad;
    private String Numero_Cliente;
    private String Nombre_Cliente;
    private String Num_Ident_Fiscal;
    private String Responsable;
    private String Texto_Responsable;
    private String Canal_Ventas;
    private String Descripcion_Canal;
    private String Indicador_CME;
    private String Num_Documento_Compensacion;
    private String Fecha_Compens;
    private String Codigo_Sucursal; //arreglar este campo
    private String Nombre_Sucursal;
    private String Clase_Documento;
    private String Numero_Documento;
    private String Ejercicio;
    private String Referencia;
    private String Fe_Contable;
    private String Fecha_Documento;
    private String Fecha_Base;
    private String Descripcion_Cond_Pago;
    private String Origen_diferencias;
    private String Indicador_Debe_Haber;
    private String Referencia_Factura;
    private String Ejercicio_Referencia;
    private String Fecha_Vencimiento;
    private String Condicion_Pago;
    private double Saldo_0_0_días;
    private double Saldo_1_15_días;
    private double Saldo_16_21_días;
    private double Saldo_22_30_días;
    private double Saldo_31_60_días;
    private double Saldo_61_90_días;
    private double Saldo_91_120_días;
    private double Saldo_121_180_días;
    private double Saldo_mayor_180_días;
    private double Saldo;
    private String Ruta;
    private String Clasificacion_Cliente;
    private String Codigo_Industria_1;
    private String Oficina_Ventas;
    private String Descripción_Of_Vtas;
    private String Grupo_Vendedores;

    public Partidas_Abiertas(String Sociedad, String Numero_Cliente, String Nombre_Cliente, String Num_Ident_Fiscal, String Responsable, String Texto_Responsable, String Canal_Ventas, String Descripcion_Canal, String Indicador_CME, String Num_Documento_Compensacion, String Fecha_Compens, String Codigo_Sucursal, String Nombre_Sucursal, String Clase_Documento, String Numero_Documento, String Ejercicio, String Referencia, String Fe_Contable, String Fecha_Documento, String Fecha_Base, String Descripcion_Cond_Pago, String Origen_diferencias, String Indicador_Debe_Haber, String Referencia_Factura, String Ejercicio_Referencia, String Fecha_Vencimiento, String Condicion_Pago, double Saldo_0_0_días, double Saldo_1_15_días, double Saldo_16_21_días, double Saldo_22_30_días, double Saldo_31_60_días, double Saldo_61_90_días, double Saldo_91_120_días, double Saldo_121_180_días, double Saldo_mayor_180_días, double Saldo, String Ruta, String Clasificacion_Cliente, String Codigo_Industria_1, String Oficina_Ventas, String Descripción_Of_Vtas, String Grupo_Vendedores) {
        this.Sociedad = Sociedad;
        this.Numero_Cliente = Numero_Cliente;
        this.Nombre_Cliente = Nombre_Cliente;
        this.Num_Ident_Fiscal = Num_Ident_Fiscal;
        this.Responsable = Responsable;
        this.Texto_Responsable = Texto_Responsable;
        this.Canal_Ventas = Canal_Ventas;
        this.Descripcion_Canal = Descripcion_Canal;
        this.Indicador_CME = Indicador_CME;
        this.Num_Documento_Compensacion = Num_Documento_Compensacion;
        this.Fecha_Compens = Fecha_Compens;
        this.Codigo_Sucursal = Codigo_Sucursal;
        this.Nombre_Sucursal = Nombre_Sucursal;
        this.Clase_Documento = Clase_Documento;
        this.Numero_Documento = Numero_Documento;
        this.Ejercicio = Ejercicio;
        this.Referencia = Referencia;
        this.Fe_Contable = Fe_Contable;
        this.Fecha_Documento = Fecha_Documento;
        this.Fecha_Base = Fecha_Base;
        this.Descripcion_Cond_Pago = Descripcion_Cond_Pago;
        this.Origen_diferencias = Origen_diferencias;
        this.Indicador_Debe_Haber = Indicador_Debe_Haber;
        this.Referencia_Factura = Referencia_Factura;
        this.Ejercicio_Referencia = Ejercicio_Referencia;
        this.Fecha_Vencimiento = Fecha_Vencimiento;
        this.Condicion_Pago = Condicion_Pago;
        this.Saldo_0_0_días = Saldo_0_0_días;
        this.Saldo_1_15_días = Saldo_1_15_días;
        this.Saldo_16_21_días = Saldo_16_21_días;
        this.Saldo_22_30_días = Saldo_22_30_días;
        this.Saldo_31_60_días = Saldo_31_60_días;
        this.Saldo_61_90_días = Saldo_61_90_días;
        this.Saldo_91_120_días = Saldo_91_120_días;
        this.Saldo_121_180_días = Saldo_121_180_días;
        this.Saldo_mayor_180_días = Saldo_mayor_180_días;
        this.Saldo = Saldo;
        this.Ruta = Ruta;
        this.Clasificacion_Cliente = Clasificacion_Cliente;
        this.Codigo_Industria_1 = Codigo_Industria_1;
        this.Oficina_Ventas = Oficina_Ventas;
        this.Descripción_Of_Vtas = Descripción_Of_Vtas;
        this.Grupo_Vendedores = Grupo_Vendedores;
    }
    
    



    public String getSociedad() {
        return Sociedad;
    }

    public void setSociedad(String Sociedad) {
        this.Sociedad = Sociedad;
    }

    public String getNumero_Cliente() {
        return Numero_Cliente;
    }

    public void setNumero_Cliente(String Numero_Cliente) {
        this.Numero_Cliente = Numero_Cliente;
    }

    public String getNombre_Cliente() {
        return Nombre_Cliente;
    }

    public void setNombre_Cliente(String Nombre_Cliente) {
        this.Nombre_Cliente = Nombre_Cliente;
    }

    public String getNum_Ident_Fiscal() {
        return Num_Ident_Fiscal;
    }

    public void setNum_Ident_Fiscal(String Num_Ident_Fiscal) {
        this.Num_Ident_Fiscal = Num_Ident_Fiscal;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    public String getTexto_Responsable() {
        return Texto_Responsable;
    }

    public void setTexto_Responsable(String Texto_Responsable) {
        this.Texto_Responsable = Texto_Responsable;
    }

    public String getCanal_Ventas() {
        return Canal_Ventas;
    }

    public void setCanal_Ventas(String Canal_Ventas) {
        this.Canal_Ventas = Canal_Ventas;
    }

    public String getDescripcion_Canal() {
        return Descripcion_Canal;
    }

    public void setDescripcion_Canal(String Descripcion_Canal) {
        this.Descripcion_Canal = Descripcion_Canal;
    }

    public String getIndicador_CME() {
        return Indicador_CME;
    }

    public void setIndicador_CME(String Indicador_CME) {
        this.Indicador_CME = Indicador_CME;
    }

    public String getNum_Documento_Compensacion() {
        return Num_Documento_Compensacion;
    }

    public void setNum_Documento_Compensacion(String Num_Documento_Compensacion) {
        this.Num_Documento_Compensacion = Num_Documento_Compensacion;
    }

    public String getFecha_Compens() {
        return Fecha_Compens;
    }

    public void setFecha_Compens(String Fecha_Compens) {
        this.Fecha_Compens = Fecha_Compens;
    }

    public String getNombre_Sucursal() {
        return Nombre_Sucursal;
    }

    public void setNombre_Sucursal(String Nombre_Sucursal) {
        this.Nombre_Sucursal = Nombre_Sucursal;
    }

    public String getClase_Documento() {
        return Clase_Documento;
    }

    public void setClase_Documento(String Clase_Documento) {
        this.Clase_Documento = Clase_Documento;
    }

    public String getNumero_Documento() {
        return Numero_Documento;
    }

    public void setNumero_Documento(String Numero_Documento) {
        this.Numero_Documento = Numero_Documento;
    }

    public String getEjercicio() {
        return Ejercicio;
    }

    public void setEjercicio(String Ejercicio) {
        this.Ejercicio = Ejercicio;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public String getFe_Contable() {
        return Fe_Contable;
    }

    public void setFe_Contable(String Fe_Contable) {
        this.Fe_Contable = Fe_Contable;
    }

    public String getFecha_Documento() {
        return Fecha_Documento;
    }

    public void setFecha_Documento(String Fecha_Documento) {
        this.Fecha_Documento = Fecha_Documento;
    }

    public String getFecha_Base() {
        return Fecha_Base;
    }

    public void setFecha_Base(String Fecha_Base) {
        this.Fecha_Base = Fecha_Base;
    }

    public String getDescripcion_Cond_Pago() {
        return Descripcion_Cond_Pago;
    }

    public void setDescripcion_Cond_Pago(String Descripcion_Cond_Pago) {
        this.Descripcion_Cond_Pago = Descripcion_Cond_Pago;
    }

    public String getOrigen_diferencias() {
        return Origen_diferencias;
    }

    public void setOrigen_diferencias(String Origen_diferencias) {
        this.Origen_diferencias = Origen_diferencias;
    }

    public String getIndicador_Debe_Haber() {
        return Indicador_Debe_Haber;
    }

    public void setIndicador_Debe_Haber(String Indicador_Debe_Haber) {
        this.Indicador_Debe_Haber = Indicador_Debe_Haber;
    }

    public String getReferencia_Factura() {
        return Referencia_Factura;
    }

    public void setReferencia_Factura(String Referencia_Factura) {
        this.Referencia_Factura = Referencia_Factura;
    }

    public String getEjercicio_Referencia() {
        return Ejercicio_Referencia;
    }

    public void setEjercicio_Referencia(String Ejercicio_Referencia) {
        this.Ejercicio_Referencia = Ejercicio_Referencia;
    }

    public String getFecha_Vencimiento() {
        return Fecha_Vencimiento;
    }

    public void setFecha_Vencimiento(String Fecha_Vencimiento) {
        this.Fecha_Vencimiento = Fecha_Vencimiento;
    }

    public String getCondicion_Pago() {
        return Condicion_Pago;
    }

    public void setCondicion_Pago(String Condicion_Pago) {
        this.Condicion_Pago = Condicion_Pago;
    }

    public String getCodigo_Sucursal() {
        return Codigo_Sucursal;
    }

    public void setCodigo_Sucursal(String Codigo_Sucursal) {
        this.Codigo_Sucursal = Codigo_Sucursal;
    }

    public double getSaldo_0_0_días() {
        return Saldo_0_0_días;
    }

    public void setSaldo_0_0_días(double Saldo_0_0_días) {
        this.Saldo_0_0_días = Saldo_0_0_días;
    }

    public double getSaldo_1_15_días() {
        return Saldo_1_15_días;
    }

    public void setSaldo_1_15_días(double Saldo_1_15_días) {
        this.Saldo_1_15_días = Saldo_1_15_días;
    }

    public double getSaldo_16_21_días() {
        return Saldo_16_21_días;
    }

    public void setSaldo_16_21_días(double Saldo_16_21_días) {
        this.Saldo_16_21_días = Saldo_16_21_días;
    }

    public double getSaldo_22_30_días() {
        return Saldo_22_30_días;
    }

    public void setSaldo_22_30_días(double Saldo_22_30_días) {
        this.Saldo_22_30_días = Saldo_22_30_días;
    }

    public double getSaldo_31_60_días() {
        return Saldo_31_60_días;
    }

    public void setSaldo_31_60_días(double Saldo_31_60_días) {
        this.Saldo_31_60_días = Saldo_31_60_días;
    }

    public double getSaldo_61_90_días() {
        return Saldo_61_90_días;
    }

    public void setSaldo_61_90_días(double Saldo_61_90_días) {
        this.Saldo_61_90_días = Saldo_61_90_días;
    }

    public double getSaldo_91_120_días() {
        return Saldo_91_120_días;
    }

    public void setSaldo_91_120_días(double Saldo_91_120_días) {
        this.Saldo_91_120_días = Saldo_91_120_días;
    }

    public double getSaldo_121_180_días() {
        return Saldo_121_180_días;
    }

    public void setSaldo_121_180_días(double Saldo_121_180_días) {
        this.Saldo_121_180_días = Saldo_121_180_días;
    }

    public double getSaldo_mayor_180_días() {
        return Saldo_mayor_180_días;
    }

    public void setSaldo_mayor_180_días(double Saldo_mayor_180_días) {
        this.Saldo_mayor_180_días = Saldo_mayor_180_días;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }

    public String getClasificacion_Cliente() {
        return Clasificacion_Cliente;
    }

    public void setClasificacion_Cliente(String Clasificacion_Cliente) {
        this.Clasificacion_Cliente = Clasificacion_Cliente;
    }

    public String getCodigo_Industria_1() {
        return Codigo_Industria_1;
    }

    public void setCodigo_Industria_1(String Codigo_Industria_1) {
        this.Codigo_Industria_1 = Codigo_Industria_1;
    }

    public String getOficina_Ventas() {
        return Oficina_Ventas;
    }

    public void setOficina_Ventas(String Oficina_Ventas) {
        this.Oficina_Ventas = Oficina_Ventas;
    }

    public String getDescripción_Of_Vtas() {
        return Descripción_Of_Vtas;
    }

    public void setDescripción_Of_Vtas(String Descripción_Of_Vtas) {
        this.Descripción_Of_Vtas = Descripción_Of_Vtas;
    }

    public String getGrupo_Vendedores() {
        return Grupo_Vendedores;
    }

    public void setGrupo_Vendedores(String Grupo_Vendedores) {
        this.Grupo_Vendedores = Grupo_Vendedores;
    }

    
    
}
