package gestionXML;

@SuppressWarnings("rawtypes")
public class Message implements java.lang.Comparable{

	private int id = 0;
	private String texte = "";
	private String posteur = "";
	private String date = "20/01/1993 à 00:00";
	private int nbr_likes = 0;
	

	public Message(String texte, String posteur) {
		Dates d = new Dates();
		this.date = d.date();
		this.texte = texte;
		this.posteur = posteur;
	}

	public int getId() {
		return id;
	}

	public String getTexte() {
		return texte;
	}

	public String getPosteur() {
		return posteur;
	}

	public String getDate() {
		return date;
	}

	public int getLike() {
		return nbr_likes;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public void setDate(String date){
		this.date = date;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public void setLikes(int nbr_likes){
		this.nbr_likes=nbr_likes;
	}

	public int compareTo(Object other) {
		  int nombre1 = ((Message) other).getId(); 
	      int nombre2 = this.getId(); 
	      if (nombre1 > nombre2)  return -1; 
	      else if(nombre1 == nombre2) return 0; 
	      else return 1; 
	}
}
