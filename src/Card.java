
public interface Card {
	public User getUser();
	public double cost(Duration duration, String bicycleType);
	
	public static void main(String[] args) {
		User user1 = new User("Robert Downey Jr.");
		Vmax vmax1 = new Vmax(user1);
		System.out.println(user1.getCard());
	}
}
