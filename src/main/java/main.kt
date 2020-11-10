
import cmds.setup
import core.bot
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
const val prefix:String="!"
const val token=""
fun main(){
    setup()
    val jda2: JDABuilder = JDABuilder.createDefault(token)
    jda2.addEventListeners(MessageListenerExample())
    for(i in bot.listeners){
        jda2.addEventListeners(i)
    }
    jda2.build()
}
class MessageListenerExample : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent){
        val message: Message =event.message
        val author=event.author
        if(message.contentRaw.startsWith(prefix)&&!author.isBot){
            try {
                if (message.contentDisplay.drop(1).split(" ")[0] in bot.commandslist.keys) {
                    bot.commandslist[message.contentDisplay.drop(1).split(" ")[0]]?.let { it(event) }
                } else {
                    throw Error("Not Command is ${message.contentDisplay.drop(1).split(" ")[0]}")
                }
            }catch (error:Error){
                bot.onCommandError(event,error)
            }
        }
    }
}