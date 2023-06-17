package Logica;

import java.time.LocalDateTime;

public class Reuniao {
	private LocalDateTime dataHora;
	private String ata;
	
	public Reuniao(LocalDateTime dataHora) {
		this.dataHora = dataHora;
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