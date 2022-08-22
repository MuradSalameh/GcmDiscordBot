package helperClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import hibernate.dao.MemberDAO;
import hibernate.model.Member;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Scheduler extends ListenerAdapter {

    public static JDABuilder builder;
    private static int counter = 0;
    private static JDA api = null;

    public Scheduler(JDA api) {
	this.api = api;
    }

    public void start() throws InterruptedException {
	scheduleTask();
    }

    public static TextChannel targetTextChannel() {
	TextChannel textChannel = api.getTextChannelsByName("log-channel", true).get(0);
	//		TextChannel textChannel = api.getTextChannelById("703612269644218452");

	return textChannel;
    }

    public static ArrayList<Long> getBirthdays() {

	List<Member> members = MemberDAO.getTodaysMembersBirthdays();
	if (members.isEmpty()) {
	    System.out.println("No Birthdays Today");
	}

	ArrayList<Long> userIdList = new ArrayList<Long>();

	for (Member m : members) {
	    userIdList.add(Long.parseLong(m.getClanId()));
	}

	return userIdList;
    }

    public static void sendBirthdayMessage() {
	ArrayList<Long> memberIdsBirthday = getBirthdays();

	for (long eineId : memberIdsBirthday) {
	    // api.retrieveUserById(eineId).queue(user -> {
	    targetTextChannel().sendMessage(":birthday: :gift: Alles Beste zum Geburtstag!!! " + "<@" + eineId + ">"
		    + " :champagne_glass: :champagne:\n").queue();
	    targetTextChannel().sendMessage("https://tenor.com/view/happy-birthday-gif-25394753").queue();
	    // });
	}

    }

    public static void scheduleTask() throws InterruptedException {
	ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
	Runnable executeTask = () -> {
	    counter++;

	    // Methods
	    // -------------------------------------------------------------------
	    sendBirthdayMessage();
	    // -------------------------------------------------------------------

	};

	/*
	ZoneOffset zoneOffSet = ZoneOffset.of("+02:00");
	OffsetDateTime now = OffsetDateTime.now(zoneOffSet);
	LocalDate today = now.toLocalDate();
	LocalDate tomorrow = today.plusDays(1);
	OffsetDateTime tomorrowStart = OffsetDateTime.of(tomorrow, LocalTime.MIN, zoneOffSet);
	Duration d = Duration.between(now, tomorrowStart);
	long millisUntilTomorrowStart = d.toMillis();

	ScheduledFuture<?> scheduleAtFixedRate = service.scheduleAtFixedRate(executeTask, 
		millisUntilTomorrowStart, // Initial delay, before first execution to get close to first moment of tomorrow.
		TimeUnit.DAYS.toMillis(1), // Amount of time in each interval, between subsequent executions
		TimeUnit.MILLISECONDS); // Unit of time intended by the numbers in previous two arguments.
	 */

	
	// executeTask, initialDelay (time to delay first execution), period denotes the
	// duration between successive execution
	ScheduledFuture<?> scheduleAtFixedRate = service.scheduleAtFixedRate(executeTask, 1, 5, TimeUnit.SECONDS);
	while (true) {
	    Thread.sleep(5000);
	    if (counter == 1) {
		System.out.println("Stopping the scheduled task!");
		scheduleAtFixedRate.cancel(true);
		service.shutdown();
		break;
	    }
	}
    }
}