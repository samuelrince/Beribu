package fr.ecp.IS1220.beribu;

public class CardFactory {

	public Card newCard(String type, User user) {
		if (type.equalsIgnoreCase("standard"))
			return new Standard(user);
		if (type.equalsIgnoreCase("vlibre"))
			return new Vlibre(user);
		if (type.equalsIgnoreCase("vmax"))
			return new Vmax(user);
		else
			throw new IllegalArgumentException("There is no such card type.");
	}
}
