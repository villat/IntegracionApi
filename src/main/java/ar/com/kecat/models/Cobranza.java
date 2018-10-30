package ar.com.kecat.models;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="cobranzas")
@Where(clause = "activo = 1")
public class Cobranza extends ModeloBase implements Serializable {

    private static final long serialVersionUID = 3980796348979222562L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="monto")
    private BigDecimal monto;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha")
    private Date fecha = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_liquidacion")
    private Liquidacion liquidacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        private BigDecimal monto;
        private Date fecha = new Date();
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

        public Builder withMonto(BigDecimal monto) {
            this.monto = monto;
            return this;
        }

        public Builder withFecha(Date fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder withLiquidacion(Liquidacion liquidacion) {
            this.liquidacion = liquidacion;
            return this;
        }

        public Cobranza build() {
            Cobranza cobranza = new Cobranza();
            cobranza.setActivo(activo);
            cobranza.setFechaCreacion(fechaCreacion);
            cobranza.setFechaActualizacion(fechaActualizacion);
            cobranza.setId(id);
            cobranza.setMonto(monto);
            cobranza.setFecha(fecha);
            cobranza.setLiquidacion(liquidacion);
            return cobranza;
        }
    }
}
