package fr.ecp.IS1220.myVelib.core.card;

import fr.ecp.IS1220.myVelib.core.exception.BadCardTypeException;
import fr.ecp.IS1220.myVelib.core.user.User;

/**
 * A card factory used when creating a new subscription (or changing of credit "Standard" card).
 * @author Valentin
 *
 */
public class CardFactory {
	public static final String[] types = {"Standard", "VLibre", "VMax"};

	public Card newCard(String type, User user) {
		if (type.equalsIgnoreCase("standard"))
			return new Standard(user);
		if (type.equalsIgnoreCase("vlibre"))
			return new Vlibre(user);
		if (type.equalsIgnoreCase("vmax"))
			return new Vmax(user);
		else
			throw new BadCardTypeException("No such card type. The accepted card types are"
					+ " 'standard', 'vlibre' and 'vmax'.");
	}
}
