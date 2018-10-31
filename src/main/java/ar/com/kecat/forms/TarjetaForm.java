package ar.com.kecat.forms;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class TarjetaForm {

    @NotNull
    private Long idCliente;
    @NotNull
    private String nroTarjeta;
    @NotNull
    private Date fechaValidaDesde;
    @NotNull
    private Date fechaValidaHasta;
    @NotNull
    private Long codigoSeguridad;
    @NotNull
    private Long diaCierre;
    private String categoria;
    private BigDecimal montoLimite;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public Date getFechaValidaDesde() {
        return fechaValidaDesde;
    }

    public void setFechaValidaDesde(Date fechaValidaDesde) {
        this.fechaValidaDesde = fechaValidaDesde;
    }

    public Date getFechaValidaHasta() {
        return fechaValidaHasta;
    }

    public void setFechaValidaHasta(Date fechaValidaHasta) {
        this.fechaValidaHasta = fechaValidaHasta;
    }

    public Long getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(Long codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public Long getDiaCierre() {
        return diaCierre;
    }

    public void setDiaCierre(Long diaCierre) {
        this.diaCierre = diaCierre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getMontoLimite() {
        return montoLimite;
    }

    public void setMontoLimite(BigDecimal montoLimite) {
        this.montoLimite = montoLimite;
    }
}
