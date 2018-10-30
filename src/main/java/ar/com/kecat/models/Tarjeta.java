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
@Table(name="tarjetas")
@Where(clause = "activo = 1")
public class Tarjeta extends ModeloBase implements Serializable {

    private static final long serialVersionUID = -5066119068471831833L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @Column(name="nro_tarjeta")
    private String nroTarjeta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_valida_desde")
    private Date fechaValidaDesde = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_valida_hasta")
    private Date fechaValidaHasta = new Date();

    @Column(name="codigo_seguridad")
    private Long codigoSeguridad;

    @Column(name="dia_cierre")
    private Long diaCierre;

    @Column(name="categoria")
    private String categoria;

    @Column(name="monto_limite")
    private BigDecimal montoLimite;

    @OneToMany(mappedBy="tarjeta",fetch = FetchType.EAGER)
    @Where(clause ="activo =1")
    private List<Liquidacion> liquidaciones = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public List<Liquidacion> getLiquidaciones() {
        return liquidaciones;
    }

    public void setLiquidaciones(List<Liquidacion> liquidaciones) {
        this.liquidaciones = liquidaciones;
    }


    public static final class Builder {
        protected Boolean activo = true;
        protected Date fechaCreacion = new Date();
        protected Date fechaActualizacion = new Date();
        private Long id;
        private Cliente cliente;
        private String nroTarjeta;
        private Date fechaValidaDesde = new Date();
        private Date fechaValidaHasta = new Date();
        private Long codigoSeguridad;
        private Long diaCierre;
        private String categoria;
        private BigDecimal montoLimite;
        private List<Liquidacion> liquidaciones = new ArrayList<>();

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

        public Builder withCliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder withNroTarjeta(String nroTarjeta) {
            this.nroTarjeta = nroTarjeta;
            return this;
        }

        public Builder withFechaValidaDesde(Date fechaValidaDesde) {
            this.fechaValidaDesde = fechaValidaDesde;
            return this;
        }

        public Builder withFechaValidaHasta(Date fechaValidaHasta) {
            this.fechaValidaHasta = fechaValidaHasta;
            return this;
        }

        public Builder withCodigoSeguridad(Long codigoSeguridad) {
            this.codigoSeguridad = codigoSeguridad;
            return this;
        }

        public Builder withDiaCierre(Long diaCierre) {
            this.diaCierre = diaCierre;
            return this;
        }

        public Builder withCategoria(String categoria) {
            this.categoria = categoria;
            return this;
        }

        public Builder withMontoLimite(BigDecimal montoLimite) {
            this.montoLimite = montoLimite;
            return this;
        }

        public Builder withLiquidaciones(List<Liquidacion> liquidaciones) {
            this.liquidaciones = liquidaciones;
            return this;
        }

        public Tarjeta build() {
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setActivo(activo);
            tarjeta.setFechaCreacion(fechaCreacion);
            tarjeta.setFechaActualizacion(fechaActualizacion);
            tarjeta.setId(id);
            tarjeta.setCliente(cliente);
            tarjeta.setNroTarjeta(nroTarjeta);
            tarjeta.setFechaValidaDesde(fechaValidaDesde);
            tarjeta.setFechaValidaHasta(fechaValidaHasta);
            tarjeta.setCodigoSeguridad(codigoSeguridad);
            tarjeta.setDiaCierre(diaCierre);
            tarjeta.setCategoria(categoria);
            tarjeta.setMontoLimite(montoLimite);
            tarjeta.setLiquidaciones(liquidaciones);
            return tarjeta;
        }
    }
}
