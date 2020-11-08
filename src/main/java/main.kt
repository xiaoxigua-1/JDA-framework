import cmds.setup
import core.bot
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
const val prefix:String="!"
fun main(){
    val jda: JDA = JDABuilder.createDefault("NzM2MDcwODQzOTY0MzI1OTQ4.XxpdZA.sKMecsr-3qNrzAdI1J0XxfKxhv8")
            .addEventListeners(MessageListenerExample())
            .build()
    jda.awaitReady()
    println("open")
    setup(jda)
}
class MessageListenerExample : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent){
        val message: Message =event.message
        val author=event.author
        if(message.contentRaw.startsWith(prefix)&&!author.isBot){
            if(message.contentDisplay.drop(1).split(" ")[0] in bot.commandslist.keys){
                bot.commandslist[message.contentDisplay.drop(1).split(" ")[0]]?.let { it(event) }
            }else{
                println("e")
            }
        }
    }
}

