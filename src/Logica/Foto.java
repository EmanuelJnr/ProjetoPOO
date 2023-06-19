package Logica;

public class Foto {
	private String caminhoDaFoto;
	
	public Foto(String caminhoDaFoto) {
		setCaminhoDaFoto(caminhoDaFoto);
	}
	public Foto() {
		
	}
	public boolean equals(Foto f) {
		if(f.getCaminhoDaFoto().equals(caminhoDaFoto))
			return true;
		return false;
	}
	public String getCaminhoDaFoto() {
		return caminhoDaFoto;
	}
	public void setCaminhoDaFoto(String caminhoDaFoto) {
		this.caminhoDaFoto = caminhoDaFoto;
	}
}