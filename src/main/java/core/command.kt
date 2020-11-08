package core

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Command{
    private var errorfunction: (MessageReceivedEvent, Error) -> Unit
    private var aliases: List<String>
    private var name: String
    private var function: (event: MessageReceivedEvent) -> Unit
    private  var command:MutableMap<String,fugit>

    constructor(function: fugit, name:String, aliases: List<String>){
        this.function=function
        this.name=name
        this.aliases=aliases
        this.errorfunction=fun(_: MessageReceivedEvent, error: Error){
            println(error)
        }
        this.command=mutableMapOf()
    }
    fun error(errorfunction:errorfn){
        this.errorfunction=errorfunction
    }
    fun run():MutableMap<String,fugit>{
        var w=fun(event: MessageReceivedEvent){
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