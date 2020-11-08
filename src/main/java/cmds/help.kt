package cmds
import core.Commands
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class W(jda:JDA):Commands(){
    override fun commands() {
        super.command(fun(event: MessageReceivedEvent){
            event.channel.sendMessage("fuck").queue()
        },"fuck",listOf("w"))
        super.group(fun(event:MessageReceivedEvent){
            event.channel.sendMessage("eee1").queue()
        },"eee",listOf())
                .command(fun(event:MessageReceivedEvent){
                    event.channel.sendMessage("eee2").queue()
                },"ee",listOf())

    }
}

