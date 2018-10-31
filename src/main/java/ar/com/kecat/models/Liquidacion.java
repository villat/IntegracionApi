package ar.com.kecat.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="liquidaciones")
@Where(clause = "activo = 1")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Liquidacion extends ModeloBase implements Serializable {

    private static final long serialVersionUID = 2944643005691251573L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_tarjeta")
    @JsonIgnore
    private Tarjeta tarjeta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_cierre")
    private Date fechaCierre= new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_vencimiento")
    private Date fechaVencimiento= new Date();

    @OneToMany(mappedBy="liquidacion",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @Where(clause ="activo =1")
    private List<Consumo> consumos = new ArrayList<>();

    @OneToMany(mappedBy="liquidacion",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @Where(clause ="activo =1")
    private List<Cobranza> cobranzas = new ArrayList<>();

    public enum Estado {
        ABIERTA ("Liquidación actual"),
        CERRADA ("Liquidación cerrada"),
        ELIMINADA ("Liquidación eliminada");

        private String descripcion;

        Estado(String descripcion) {
            this.descripcion= descripcion;
        }

        public String getDescripcion() {return this.descripcion;}
    }

    @Column(name="estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name="pagada")
    private Boolean pagada = false;

    public BigDecimal calcularSaldoPendiente(){
        if(pagada) return BigDecimal.ZERO;
        BigDecimal montoConsumos = consumos.stream().map(Consumo::getMonto).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        if(montoConsumos.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        BigDecimal montoCobranzas = cobranzas.stream().map(Cobranza::getMonto).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        return montoConsumos.subtract(montoCobranzas);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public List<Cobranza> getCobranzas() {
        return cobranzas;
    }

    public void setCobranzas(List<Cobranza> cobranzas) {
        this.cobranzas = cobranzas;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Boolean getPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }

    public static final class Builder {
        protected Boolean activo = true;
        protected Date fechaCreacion = new Date();
        protected Date fechaActualizacion = new Date();
        private Long id;
        private Tarjeta tarjeta;
        private Date fechaCierre= new Date();
        private Date fechaVencimiento= new Date();
        private List<Consumo> consumos = new ArrayList<>();
        private List<Cobranza> cobranzas = new ArrayList<>();
        private Estado estado;
        private Boolean pagada = false;

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

        public Builder withTarjeta(Tarjeta tarjeta) {
            this.tarjeta = tarjeta;
            return this;
        }

        public Builder withFechaCierre(Date fechaCierre) {
            this.fechaCierre = fechaCierre;
            return this;
        }

        public Builder withFechaVencimiento(Date fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
            return this;
        }

        public Builder withConsumos(List<Consumo> consumos) {
            this.consumos = consumos;
            return this;
        }

        public Builder withCobranzas(List<Cobranza> cobranzas) {
            this.cobranzas = cobranzas;
            return this;
        }

        public Builder withEstado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public Builder withPagada(Boolean pagada){
            this.pagada = pagada;
            return this;
        }

        public Liquidacion build() {
            Liquidacion liquidacion = new Liquidacion();
            liquidacion.setActivo(activo);
            liquidacion.setFechaCreacion(fechaCreacion);
            liquidacion.setFechaActualizacion(fechaActualizacion);
            liquidacion.setId(id);
            liquidacion.setTarjeta(tarjeta);
            liquidacion.setFechaCierre(fechaCierre);
            liquidacion.setFechaVencimiento(fechaVencimiento);
            liquidacion.setConsumos(consumos);
            liquidacion.setCobranzas(cobranzas);
            liquidacion.setEstado(estado);
            liquidacion.setPagada(pagada);
            return liquidacion;
        }
    }
}
