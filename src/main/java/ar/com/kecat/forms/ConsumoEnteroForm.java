package ar.com.kecat.forms;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class ConsumoEnteroForm {

    @NotNull
    private Date fecha;
    @NotNull
    private Long idEstablecimiento;
    @NotNull
    private String descripcion;
    @NotNull
    private BigDecimal monto;
    @NotNull
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

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Long getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(Long codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }
}
