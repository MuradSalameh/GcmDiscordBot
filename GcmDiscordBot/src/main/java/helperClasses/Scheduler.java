package helperClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import hibernate.dao.MemberDAO;
import hibernate.model.Member;

public class Scheduler {
	private static int counter = 0;

	public static void scheduleTask() throws InterruptedException {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		Runnable executeTask = () -> {
			counter++;

			// Methods
			// -------------------------------------------------------------------

			System.out.println("test");
			getBirthdays();

			// -------------------------------------------------------------------

		};

		// executeTask, initialDelay (time to delay first execution), period denotes the
		// duration between successive execution
		ScheduledFuture<?> scheduleAtFixedRate = service.scheduleAtFixedRate(executeTask, 3, 3, TimeUnit.SECONDS);
		while (true) {
			Thread.sleep(3000);
			if (counter == 3) {
				System.out.println("Stopping the scheduled task!");
				scheduleAtFixedRate.cancel(true);
				service.shutdown();
				break;
			}
		}
	}

	public static List<Member> getBirthdays() {
		List<Member> members = MemberDAO.getTodaysMembersBirthdays();
		if (members.isEmpty()) {
			System.out.println("No Birthdays Today");
		}

		ArrayList<Member> ol = new ArrayList<Member>();

		for (Member m : members) {
			ol.add(m);
			System.out.println(m);
		}
		return members;
	}

}