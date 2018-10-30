package ar.com.kecat.forms;

import java.math.BigDecimal;
import java.util.Date;

public class ConsumoEnteroForm {

    private Date fecha;
    private Long idEstablecimiento;
    private String descripcion;
    private BigDecimal montoTotal;
    private Long codigoSeguridad;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Long idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Long getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(Long codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }
}
