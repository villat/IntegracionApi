package ar.com.kecat.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class TransferenciaDTO {

    private String origen;
    private String destino;
    private BigDecimal monto;
    private String descripcion;
    private Date fecha;

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public static final class Builder {
        private String origen;
        private String destino;
        private BigDecimal monto;
        private String descripcion;
        private Date fecha;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withOrigen(String origen) {
            this.origen = origen;
            return this;
        }

        public Builder withDestino(String destino) {
            this.destino = destino;
            return this;
        }

        public Builder withMonto(BigDecimal monto) {
            this.monto = monto;
            return this;
        }

        public Builder withDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder withFecha(Date fecha) {
            this.fecha = fecha;
            return this;
        }

        public TransferenciaDTO build() {
            TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
            transferenciaDTO.setOrigen(origen);
            transferenciaDTO.setDestino(destino);
            transferenciaDTO.setMonto(monto);
            transferenciaDTO.setDescripcion(descripcion);
            transferenciaDTO.setFecha(fecha);
            return transferenciaDTO;
        }
    }
}
