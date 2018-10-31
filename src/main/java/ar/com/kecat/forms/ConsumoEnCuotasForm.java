package ar.com.kecat.forms;

import javax.validation.constraints.NotNull;

public class ConsumoEnCuotasForm extends ConsumoEnteroForm {

    @NotNull
    private Integer cantCuotas;
    @NotNull
    private Long interes;

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
}
