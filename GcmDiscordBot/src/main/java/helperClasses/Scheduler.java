package helperClasses;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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

    // Get todays birthdays from database
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

    // define target text channel for messages
    public static TextChannel targetTextChannel() {
	TextChannel textChannel = api.getTextChannelsByName("log-channel", true).get(0);
	//   	TextChannel textChannel = api.getTextChannelById("703612269644218452");
	return textChannel;
    }

    // send birthday message to text channel
    public static void sendBirthdayMessage() {
	ArrayList<Long> memberIdsBirthday = getBirthdays();

	for (long eineId : memberIdsBirthday) {
	    // api.retrieveUserById(eineId).queue(user -> {
	    targetTextChannel().sendMessage(":birthday: :gift: Alles Beste zum Geburtstag!!! " + "<@" + eineId + ">"
		    + " :champagne_glass: :champagne:\n").queue();
	    targetTextChannel().sendMessage("https://tenor.com/view/happy-birthday-gif-25394753" + " \n").queue();	    
	}
    }       

    //schedule task for daily execution at certain time
    public static void scheduleTask() throws InterruptedException {
	ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
	Runnable executeTask = () -> {
	    counter++;

	    // Methods
	    // -------------------------------------------------------------------
	    sendBirthdayMessage();
	    // -------------------------------------------------------------------

	};

	ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Vienna"));
	//Run Task at 00:05 ---> now.withHour(0).withMinute(5)
	ZonedDateTime nextRun = now.withHour(10).withMinute(12).withSecond(0);
	if(now.compareTo(nextRun) > 0)
	    nextRun = nextRun.plusDays(1);

	Duration duration = Duration.between(now, nextRun);
	long initialDelay = duration.getSeconds();

	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);            
	scheduler.scheduleAtFixedRate(executeTask,
		initialDelay,
		TimeUnit.DAYS.toSeconds(1),
		TimeUnit.SECONDS);

    }
}