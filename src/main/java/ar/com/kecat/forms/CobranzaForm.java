package ar.com.kecat.forms;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class CobranzaForm {

    @NotNull
    private BigDecimal monto;
    @NotNull
    private Date fecha;

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
}
