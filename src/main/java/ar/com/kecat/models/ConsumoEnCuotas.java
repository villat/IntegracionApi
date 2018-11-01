package ar.com.kecat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="consumos_en_cuotas")
public class ConsumoEnCuotas extends Consumo implements Serializable {

    private static final long serialVersionUID = -1772243571543392387L;

    @Column(name = "cant_cuotas")
    private Integer cantCuotas;

    @Column(name = "interes")
    private Long interes;

    @OneToMany(mappedBy="consumoEnCuotas",fetch = FetchType.LAZY)
    @Where(clause ="activo =1")
    @JsonIgnore
    private List<Cuota> cuotas = new ArrayList<>();

    public BigDecimal calcularMontoCuota(){
        return monto.divide(BigDecimal.valueOf(cantCuotas),RoundingMode.HALF_EVEN);
    }

    public Integer getCantCuotas() {
        return cantCuotas;
    }

    public void setCantCuotas(Integer cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public Long getInteres() {
        return interes;
    }

    public void setInteres(Long interes) {
        this.interes = interes;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    public static final class Builder {
        protected Boolean activo = true;
        protected Date fechaCreacion = new Date();
        protected Date fechaActualizacion = new Date();
        private Liquidacion liquidacion;
        private Long id;
        private Integer cantCuotas;
        private Date fecha = new Date();
        private Long interes;
        private Establecimiento establecimiento;
        private String descripcion;
        private BigDecimal monto;
        private List<Cuota> cuotas = new ArrayList<>();

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

        public Builder withMonto(BigDecimal monto) {
            this.monto = monto;
            return this;
        }

        public Builder withCuotas(List<Cuota> cuotas){
            this.cuotas = cuotas;
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
            consumoEnCuotas.setInteres(interes);
            consumoEnCuotas.setEstablecimiento(establecimiento);
            consumoEnCuotas.setDescripcion(descripcion);
            consumoEnCuotas.setMonto(monto);
            consumoEnCuotas.setCuotas(cuotas);
            return consumoEnCuotas;
        }
    }
}
