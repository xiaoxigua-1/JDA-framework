package core

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Command(private var function: fugit, private var name: String, private var aliases: List<String>) {
    private var errorfunction: (MessageReceivedEvent, Error) -> Unit
    private  var command:MutableMap<String,fugit>

    init {
        this.errorfunction=fun(_: MessageReceivedEvent, error: Error){
            println(error)
        }
        this.command=mutableMapOf()
    }
    fun error(errorfunction:errorfn){
        this.errorfunction=errorfunction
    }
    fun run():MutableMap<String,fugit>{
        val w=fun(event: MessageReceivedEvent){
            try{
                function(event)
            }catch (error:Error){
                errorfunction(event,error)
            }
        }
        command[name]= w
        aliases.forEach { i ->
            command[i]=w
        }
        return command
    }
}