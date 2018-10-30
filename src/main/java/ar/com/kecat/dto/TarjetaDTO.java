package ar.com.kecat.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TarjetaDTO {

    private Long clienteId;
    private String nroTarjeta;
    private Date fechaValidaDesde = new Date();
    private Date fechaValidaHasta = new Date();
    private Long codigoSeguridad;
    private Long diaCierre;
    private String categoria;
    private BigDecimal montoLimite;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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
