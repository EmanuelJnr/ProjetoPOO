package Logica;

import java.time.LocalDateTime;

public class Reuniao {
	private LocalDateTime dataHora;
	private String ata;

	public Reuniao(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public boolean equals(Reuniao r) {
		if(r.getDataHora().equals(dataHora))
			return true;
		return false;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getAta() {
		return ata;
	}
	public void setAta(String ata) {
		this.ata = ata;
	}
}