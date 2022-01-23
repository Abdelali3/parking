package models;

public class Statistics {
	@Override
	public String toString() {
		return "Statistics [lundi=" + lundi + ", mardi=" + mardi + ", mercredi=" + mercredi + ", jeudi=" + jeudi
				+ ", vendredi=" + vendredi + ", samedi=" + samedi + ", dimanche=" + dimanche + ", cars=" + cars
				+ ", motos=" + motos + ", electrics=" + electrics + ", trucks=" + trucks + ", handicappeds="
				+ handicappeds + ", payed=" + payed + ", unpayed=" + unpayed + "]";
	}
	public float lundi = 0;
	public float mardi = 0;
	public float mercredi = 0;
	public float jeudi = 0;
	public float vendredi = 0;
	public float samedi = 0;
	public float dimanche = 0;
	public int cars = 0;
	public int motos = 0;
	public int electrics = 0;
	public int trucks = 0;
	public int handicappeds = 0;
	public int payed = 0;
	public int unpayed = 0;
}
