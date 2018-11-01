package ar.com.kecat.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="cuotas")
@Where(clause = "activo = 1")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Cuota extends ModeloBase implements Serializable {

    private static final long serialVersionUID = 1784910693845399740L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_consumo_en_cuotas")
    private ConsumoEnCuotas consumoEnCuotas;

    @Column(name = "numero_de_cuota")
    private Integer numeroDeCuota;

    @Column(name = "monto_cuota")
    private BigDecimal montoCuota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_liquidacion")
    private Liquidacion liquidacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConsumoEnCuotas getConsumoEnCuotas() {
        return consumoEnCuotas;
    }

    public void setConsumoEnCuotas(ConsumoEnCuotas consumoEnCuotas) {
        this.consumoEnCuotas = consumoEnCuotas;
    }

    public Integer getNumeroDeCuota() {
        return numeroDeCuota;
    }

    public void setNumeroDeCuota(Integer numeroDeCuota) {
        this.numeroDeCuota = numeroDeCuota;
    }

    public BigDecimal getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(BigDecimal montoCuota) {
        this.montoCuota = montoCuota;
    }

    public Liquidacion getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(Liquidacion liquidacion) {
        this.liquidacion = liquidacion;
    }


    public static final class Builder {
        protected Boolean activo = true;
        protected Date fechaCreacion = new Date();
        protected Date fechaActualizacion = new Date();
        private Long id;
        private ConsumoEnCuotas consumoEnCuotas;
        private Integer numeroDeCuota;
        private BigDecimal montoCuota;
        private Liquidacion liquidacion;

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

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withConsumoEnCuotas(ConsumoEnCuotas consumoEnCuotas) {
            this.consumoEnCuotas = consumoEnCuotas;
            return this;
        }

        public Builder withNumeroDeCuota(Integer numeroDeCuota) {
            this.numeroDeCuota = numeroDeCuota;
            return this;
        }

        public Builder withMontoCuota(BigDecimal montoCuota) {
            this.montoCuota = montoCuota;
            return this;
        }

        public Builder withLiquidacion(Liquidacion liquidacion) {
            this.liquidacion = liquidacion;
            return this;
        }

        public Cuota build() {
            Cuota cuota = new Cuota();
            cuota.setActivo(activo);
            cuota.setFechaCreacion(fechaCreacion);
            cuota.setFechaActualizacion(fechaActualizacion);
            cuota.setId(id);
            cuota.setConsumoEnCuotas(consumoEnCuotas);
            cuota.setNumeroDeCuota(numeroDeCuota);
            cuota.setMontoCuota(montoCuota);
            cuota.setLiquidacion(liquidacion);
            return cuota;
        }
    }
}
