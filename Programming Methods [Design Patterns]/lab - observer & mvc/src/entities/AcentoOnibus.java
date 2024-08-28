package entities;

import enums.AcentoStatus;

import java.util.UUID;

public class AcentoOnibus {

    private int numero;
    private AcentoStatus status;

    public AcentoOnibus(int number) {
        this.numero = number;
        this.status = AcentoStatus.DISPONIVEL;
    }

    public int getNumero() {
        return numero;
    }

    public AcentoStatus getStatus() {
		return status;
	}
    public void setStatus(AcentoStatus status) {
        this.status = status;
    }

}