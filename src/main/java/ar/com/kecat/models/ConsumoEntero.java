package ar.com.kecat.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="consumos_enteros")
public class ConsumoEntero extends Consumo implements Serializable {

    private static final long serialVersionUID = 2614597492529331307L;

    public static final class Builder {
        protected Boolean activo = true;
        protected Date fechaCreacion = new Date();
        protected Date fechaActualizacion = new Date();
        private Liquidacion liquidacion;
        private Long id;
        private Date fecha = new Date();
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

        public Builder withFecha(Date fecha) {
            this.fecha = fecha;
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

        public ConsumoEntero build() {
            ConsumoEntero consumoEntero = new ConsumoEntero();
            consumoEntero.setActivo(activo);
            consumoEntero.setFechaCreacion(fechaCreacion);
            consumoEntero.setFechaActualizacion(fechaActualizacion);
            consumoEntero.setLiquidacion(liquidacion);
            consumoEntero.setId(id);
            consumoEntero.setFecha(fecha);
            consumoEntero.setEstablecimiento(establecimiento);
            consumoEntero.setDescripcion(descripcion);
            consumoEntero.setMontoTotal(montoTotal);
            return consumoEntero;
        }
    }
}
