package gestionXML;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Dates
{
//	* Choix de la langue francaise
	Locale locale = Locale.getDefault();
	Date actuelle = new Date();
	
//	* Definition du format utilise pour les dates
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy' à 'HH:mm:ss");

//	* Donne la date au format "jj-mm-yyyy"
	public String date()
	{
		String dat = dateFormat.format(actuelle);
		return dat;
	}
}
