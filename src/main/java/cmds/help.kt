package cmds
import core.Commands
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class W:Commands(){
    override fun commands() {
        command(fun(event: MessageReceivedEvent){
            event.channel.sendMessage("fuck").queue()
        },"fuck",listOf("w"))

        group( fun(event:MessageReceivedEvent){
            event.channel.sendMessage("eee1").queue()
        },"eee",listOf())
                .command(fun(event:MessageReceivedEvent){
                    event.channel.sendMessage("eee2").queue()
                },"ee",listOf())

    }
    override fun onMessageReceived(event: MessageReceivedEvent){
        println(event.message.contentDisplay)
    }
    override fun onCommandError(event: MessageReceivedEvent, error: Error) {
        println("無此指令")
    }
    override fun onReady(event: ReadyEvent) {
        println("w")
    }
}


