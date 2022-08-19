import java.awt.Color;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import helperClasses.Scheduler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SendEmbeds extends ListenerAdapter {
	/*
	 * public SendEmbeds() { }
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getMessage().getContentStripped().startsWith(Main.prefix + "serverInfo")) {
			event.getChannel().sendMessageEmbeds(serverInfo(event.getGuild()).build(), new MessageEmbed[0]).queue();
		}

	}

	private static EmbedBuilder serverInfo(Guild guild) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		OffsetDateTime date = guild.getTimeCreated();
		String formatted = df.format(date);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.decode("#51F6FF"));
		builder.setTitle(guild.getName());
		builder.setDescription("Server Infos:");
		builder.addField(":busts_in_silhouette: Members:", String.valueOf(guild.getMemberCount()), true);
		builder.addBlankField(true);
		builder.addField(":date: Erstellt am:", formatted, true);
		builder.addField(":crown: Owner:", guild.getOwner().getUser().getName(), true);
		builder.addBlankField(true);
		builder.setThumbnail(guild.getIconUrl());
		return builder;
	}

	public void ScheduledBirthdayMessages() throws InterruptedException {
		// SchedulerTest
		Scheduler s = new Scheduler();
		s.scheduleTask();

	}

}
