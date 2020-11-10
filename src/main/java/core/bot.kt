package core

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Bot{
    var commandslist:MutableMap<String,fugit> = mutableMapOf()
    var listeners:MutableList<Commands> = mutableListOf()
    private var commanderror:MutableList<errorfn> =mutableListOf()
    fun add(x:Commands){
        listeners.add(x)
        x.run().forEach{commandslist[it.key]=it.value}
        commanderror.add(x.runCommandError())
    }
    fun onCommandError(event: MessageReceivedEvent,error:Error){
        commanderror.forEach{it(event,error)}
    }
}
val bot=Bot()

fun addcog(x:Commands){
    bot.add(x)
}