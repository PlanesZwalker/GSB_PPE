
	public class Habitation(){
		
		private String lesPoubelles;
		private String unUsager;
		private String adresse;
		
		public GetPoubelles();//Collection de Poubelle
		
		public GetUsager()://Usager

	//public GetCout(an : entier, mois ::entier) : réel
	//retourne le cout des levées de cette poubelle pour le mois reçu en paramètre

		public GetCout(int annee, int mois){	

			double cout =0;
			for( var it in lesPoubelles){
				cout += it.GetCout(an, mois);
			}
			return cout;

		}
		
	}

public class Poubelle(){
	
	private String id;
	private TypeDechet nature;
	private Collection de Levee lesLevees; // Collection de levee
	
	public GetId();// Collection de Poubelle
	
	public GetNature():// Usager

//public GetCout(an : entier, mois ::entier) : réel
//retourne le cout des levées de cette poubelle pour le mois reçu en paramètre

	public double GetCout(int an ,  int mois) {
		
		double cout=0;
		
		for(Levee it in LesLevees){
			if(it.GetDate().year = an && it.getDate().month = mois){
				cout += TypeDechet.getTarif() * it.GetPoids();
			}
		}
		return cout;
	}
	
}










