package objets;

public class CasierTablePojo {
	
	private int numeroCasier;
	private String contenuCasier;
	private String nomListe;
	
	public CasierTablePojo(int numeroCasier, String contenuCasier, String nomListe) {
		super();
		this.numeroCasier = numeroCasier;
		this.contenuCasier = contenuCasier;
		this.nomListe = nomListe;
	}
	
	public CasierTablePojo() {
		super();
	}

	public int getNumeroCasier() {
		return numeroCasier;
	}
	public void setNumeroCasier(int numeroCasier) {
		this.numeroCasier = numeroCasier;
	}
	public String getContenuCasier() {
		return contenuCasier;
	}
	public void setContenuCasier(String contenuCasier) {
		this.contenuCasier = contenuCasier;
	}
	public String getNomListe() {
		return nomListe;
	}
	public void setNomListe(String nomListe) {
		this.nomListe = nomListe;
	}
	

}
