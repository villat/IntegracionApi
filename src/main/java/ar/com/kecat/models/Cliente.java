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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="clientes")
@Where(clause = "activo = 1")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Cliente extends ModeloBase implements Serializable {

    private static final long serialVersionUID = 3057549723550122608L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "nro_documento")
    private String nroDocumento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_nacimiento")
    private Date fechaNacimiento = new Date();

    @OneToMany(mappedBy="cliente",fetch = FetchType.EAGER)
    @Where(clause ="activo =1")
    private List<Tarjeta> tarjetas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public static final class Builder {
        protected Boolean activo = true;
        protected Date fechaCreacion = new Date();
        protected Date fechaActualizacion = new Date();
        private Long id;
        private String nombre;
        private String apellido;
        private String tipoDocumento;
        private String nroDocumento;
        private Date fechaNacimiento;
        private List<Tarjeta> tarjetas = new ArrayList<>();

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

        public Builder withNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder withApellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder withTipoDocumento(String tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
            return this;
        }

        public Builder withNroDocumento(String nroDocumento) {
            this.nroDocumento = nroDocumento;
            return this;
        }

        public Builder withFechaNacimiento(Date fechaNacimiento){
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder withTarjetas(List<Tarjeta> tarjetas) {
            this.tarjetas = tarjetas;
            return this;
        }

        public Cliente build() {
            Cliente cliente = new Cliente();
            cliente.setActivo(activo);
            cliente.setFechaCreacion(fechaCreacion);
            cliente.setFechaActualizacion(fechaActualizacion);
            cliente.setId(id);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTipoDocumento(tipoDocumento);
            cliente.setNroDocumento(nroDocumento);
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setTarjetas(tarjetas);
            return cliente;
        }
    }
}
