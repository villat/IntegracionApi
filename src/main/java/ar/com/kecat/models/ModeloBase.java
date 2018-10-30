package ar.com.kecat.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class ModeloBase implements Serializable {

    private static final long serialVersionUID = -9034263254665776829L;

    @Column(name="activo")
    protected Boolean activo = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_creacion")
    protected Date fechaCreacion = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_actualizacion")
    protected Date fechaActualizacion = new Date();

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
