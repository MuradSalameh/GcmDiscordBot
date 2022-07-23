//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceActivityTracker extends ListenerAdapter {
    private String MeldungMemberJoin;
    private String MeldungMemberLeave;
    private String userJoined;
    private String userLeft;
    private Instant start;
    private Instant end;
    private Duration elapsedTime;
    Map<String, Instant> currentActiveUsers = new ConcurrentHashMap<String, Instant>();

    
	/*
	 * public VoiceActivityTracker() { }
	 */

    @Override
    public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {
        this.MeldungMemberJoin = event.getMember().getUser().getName();
        String joinMeldungKanal = event.getChannelJoined().getName();
        TextChannel textChannel = event.getGuild().getTextChannelsByName("log-channel", true).get(0);
        textChannel.sendMessage(this.MeldungMemberJoin + " betritt  " + joinMeldungKanal).queue();
        this.userJoined = String.valueOf(event.getMember().getUser());
        this.start = Instant.now();
        this.currentActiveUsers.put(this.userJoined, this.start);
    }

    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
        this.MeldungMemberLeave = event.getMember().getUser().getName();
        String verlassenMeldungKanal = event.getChannelLeft().getName();
        TextChannel textChannel = event.getGuild().getTextChannelsByName("log-channel", true).get(0);
        this.userLeft = String.valueOf(event.getMember().getUser());
        Iterator<String> keys = this.currentActiveUsers.keySet().iterator();

        while(keys.hasNext()) {
            String key = keys.next();
           // Instant val = (Instant)this.currentActiveUsers.get(key);
            if (key.equals(this.userLeft)) {
                this.end = Instant.now();
                this.elapsedTime = Duration.between(this.start, this.end);
                this.currentActiveUsers.remove(this.userLeft);
            }
        }

        textChannel.sendMessage(this.MeldungMemberLeave + " verlässt  " + verlassenMeldungKanal + " Dauer: " + formatDuration(this.elapsedTime) + " Sekunden ").queue();
    }

    public static Long formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        return seconds;
    }
}
