package ar.com.kecat.forms;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class ConsumoEnCuotasForm extends ConsumoEnteroForm {

    @NotNull
    private Integer cantCuotas;
    @NotNull
    private BigDecimal montoCuota;
    @NotNull
    private Long interes;

    public Integer getCantCuotas() {
        return cantCuotas;
    }

    public void setCantCuotas(Integer cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public BigDecimal getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(BigDecimal montoCuota) {
        this.montoCuota = montoCuota;
    }

    public Long getInteres() {
        return interes;
    }

    public void setInteres(Long interes) {
        this.interes = interes;
    }
}
