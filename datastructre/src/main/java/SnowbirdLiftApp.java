import java.util.Objects;

public class SnowbirdLiftApp {
	BasicQueue<Gad2Chair> gad2Lift = new BasicQueue<Gad2Chair>();

	public static void main(String[] args) {
		SnowbirdLiftApp app = new SnowbirdLiftApp();
		app.runLift();
	}
	
	public void runLift() {
		loadLift();
		
		//print out the lift size
		System.out.println(gad2Lift.size());
		
		//does the lift contain skiers Mary and Anna
		System.out.println("Lift has Mary and Anna on it: " + gad2Lift.contains(new Gad2Chair("Mary", "Anna")));
		
		//who's at the 2nd position - remember 0 based index
		System.out.println("2nd chair has: " + gad2Lift.access(1).listChairRiders());

		unloadLift();
		
		//if the lift is unloaded again, an error should occur since no one is left on it
		try {
			unloadLift();
		} catch (IllegalStateException t) {
			System.out.println("Can't unload any more skiers because the lift is empty: " + t.getMessage());
		}
	}
	
	private void loadLift() {
		//skiers in line to get on lift
		Gad2Chair chair1 = new Gad2Chair("John", "Dave");
		Gad2Chair chair2 = new Gad2Chair("Samantha", "Kelly");
		Gad2Chair chair3 = new Gad2Chair("Mary", "Anna");
		Gad2Chair chair4 = new Gad2Chair("Robert", "Chad");
		Gad2Chair chair5 = new Gad2Chair("Sarah", "Bill");
		
		//load them onto the lift
		gad2Lift.enQueue(chair1);
		gad2Lift.enQueue(chair2);
		gad2Lift.enQueue(chair3);
		gad2Lift.enQueue(chair4);
		gad2Lift.enQueue(chair5);
	}
	
	private void unloadLift() {
		Gad2Chair chair = gad2Lift.deQueue();
		
		//should be John and Dave
		System.out.println(chair.listChairRiders());
		
		chair = gad2Lift.deQueue();

		//should be Samantha and Kelly
		System.out.println(chair.listChairRiders());

		chair = gad2Lift.deQueue();

		//should be Mary and Anna
		System.out.println(chair.listChairRiders());

		chair = gad2Lift.deQueue();

		//should be Robert and Chad
		System.out.println(chair.listChairRiders());

		chair = gad2Lift.deQueue();

		//should be Sarah and Bill
		System.out.println(chair.listChairRiders());
	}

	class Gad2Chair {
		private String seat1Name;
		private String seat2Name;
		
		public Gad2Chair(String skierInFirstSeat, String skierInSecondSeat) {
			this.seat1Name = skierInFirstSeat;
			this.seat2Name = skierInSecondSeat;
		}
		


		public String listChairRiders() {
			return this.seat1Name + ", " + this.seat2Name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Gad2Chair)) return false;
			Gad2Chair gad2Chair = (Gad2Chair) o;
			return Objects.equals(seat1Name, gad2Chair.seat1Name) &&
					Objects.equals(seat2Name, gad2Chair.seat2Name);
		}

		@Override
		public int hashCode() {
			return Objects.hash(seat1Name, seat2Name);
		}
	}
}