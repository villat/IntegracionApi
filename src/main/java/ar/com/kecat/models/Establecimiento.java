package ar.com.kecat.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="establecimientos")
@Where(clause = "activo = 1")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Establecimiento extends ModeloBase implements Serializable {

    private static final long serialVersionUID = -7769167960616182064L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="cuit")
    private String cuit;

    @Column(name="cbu")
    private String cbu;

    @Column(name="razon_social")
    private String razonSocial;

    @Column(name="domicilio")
    private String domicilio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }


    public static final class Builder {
        protected Boolean activo = true;
        protected Date fechaCreacion = new Date();
        protected Date fechaActualizacion = new Date();
        private Long id;
        private String cuit;
        private String cbu;
        private String razonSocial;
        private String domicilio;

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

        public Builder withCuit(String cuit) {
            this.cuit = cuit;
            return this;
        }

        public Builder withCbu(String cbu){
            this.cbu = cbu;
            return this;
        }

        public Builder withRazonSocial(String razonSocial) {
            this.razonSocial = razonSocial;
            return this;
        }

        public Builder withDomicilio(String domicilio) {
            this.domicilio = domicilio;
            return this;
        }

        public Establecimiento build() {
            Establecimiento establecimiento = new Establecimiento();
            establecimiento.setActivo(activo);
            establecimiento.setFechaCreacion(fechaCreacion);
            establecimiento.setFechaActualizacion(fechaActualizacion);
            establecimiento.setId(id);
            establecimiento.setCuit(cuit);
            establecimiento.setCbu(cbu);
            establecimiento.setRazonSocial(razonSocial);
            establecimiento.setDomicilio(domicilio);
            return establecimiento;
        }
    }
}
