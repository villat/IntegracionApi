package ar.com.kecat.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="consumos_en_cuotas")
public class ConsumoEnCuotas extends Consumo implements Serializable {

    private static final long serialVersionUID = -1772243571543392387L;

    @Column(name = "cant_cuotas")
    private Integer cantCuotas;

    @Column(name = "monto_cuota")
    private BigDecimal montoCuota;

    @Column(name = "interes")
    private Long interes;

    public Integer getCantCuotas() {
        return cantCuotas;
    }

    public void setCantCuotas(Integer cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public BigDecimal getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(BigDecimal montoCuota) {
        this.montoCuota = montoCuota;
    }

    public Long getInteres() {
        return interes;
    }

    public void setInteres(Long interes) {
        this.interes = interes;
    }

    public static final class Builder {
        protected Boolean activo = true;
        protected Date fechaCreacion = new Date();
        protected Date fechaActualizacion = new Date();
        private Liquidacion liquidacion;
        private Long id;
        private Integer cantCuotas;
        private Date fecha = new Date();
        private BigDecimal montoCuota;
        private Long interes;
        private Establecimiento establecimiento;
        private String descripcion;
        private BigDecimal montoTotal;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withActivo(Boolean activo) {
            this.activo = activo;
            return this;
        }

        public Builder withFechaCreacion(Date fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
            return this;
        }

        public Builder withFechaActualizacion(Date fechaActualizacion) {
            this.fechaActualizacion = fechaActualizacion;
            return this;
        }

        public Builder withLiquidacion(Liquidacion liquidacion) {
            this.liquidacion = liquidacion;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withCantCuotas(Integer cantCuotas) {
            this.cantCuotas = cantCuotas;
            return this;
        }

        public Builder withFecha(Date fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder withMontoCuota(BigDecimal montoCuota) {
            this.montoCuota = montoCuota;
            return this;
        }

        public Builder withInteres(Long interes) {
            this.interes = interes;
            return this;
        }

        public Builder withEstablecimiento(Establecimiento establecimiento) {
            this.establecimiento = establecimiento;
            return this;
        }

        public Builder withDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder withMontoTotal(BigDecimal montoTotal) {
            this.montoTotal = montoTotal;
            return this;
        }

        public ConsumoEnCuotas build() {
            ConsumoEnCuotas consumoEnCuotas = new ConsumoEnCuotas();
            consumoEnCuotas.setActivo(activo);
            consumoEnCuotas.setFechaCreacion(fechaCreacion);
            consumoEnCuotas.setFechaActualizacion(fechaActualizacion);
            consumoEnCuotas.setLiquidacion(liquidacion);
            consumoEnCuotas.setId(id);
            consumoEnCuotas.setCantCuotas(cantCuotas);
            consumoEnCuotas.setFecha(fecha);
            consumoEnCuotas.setMontoCuota(montoCuota);
            consumoEnCuotas.setInteres(interes);
            consumoEnCuotas.setEstablecimiento(establecimiento);
            consumoEnCuotas.setDescripcion(descripcion);
            consumoEnCuotas.setMontoTotal(montoTotal);
            return consumoEnCuotas;
        }
    }
}
