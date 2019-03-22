package fr.ecp.IS1220.myVelib.app;

/**
 * A card factory used when creating a new subscription (or changing of credit "Standard" card).
 * @author Valentin
 *
 */
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
