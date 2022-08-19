import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import helperClasses.Scheduler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

public class Main {

	public static JDA shardMan;
	public static JDABuilder builder;
	public static String prefix = "!";

	public static void main(String[] args) throws LoginException {

		builder = JDABuilder.createDefault("Nzc3MjIxNDMyODk5Nzk3MDUz.X7AR3Q.VRBTFrkQKJkC2RG6Wejx5L1yiNo");
		builder.addEventListeners(new SendEmbeds());
		builder.addEventListeners(new Command());
		builder.addEventListeners(new VoiceActivityTracker());
		shardMan = builder.build();
		System.out.println("[Bot] Online!");

		Scheduler s = new Scheduler();
		try {
			s.scheduleTask();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void stop() throws IOException {
		while (true) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			if (reader.readLine().equals("stop")) {

				reader.close();
				builder.setStatus(OnlineStatus.OFFLINE);
				shardMan.shutdown();
				System.out.println("[Bot] OFFLINE!");
				System.exit(1);
			}
		}
	}
}