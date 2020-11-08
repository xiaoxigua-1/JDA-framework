package core

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Group{
    private var group: MutableMap<String,fugit>
    private var aliases: List<String>
    private var errorfunction: (event: MessageReceivedEvent, error: Error) -> Unit
    private  var commandslist:MutableMap<String,fugit>
    private  var commandlist : MutableList<Command>
    private val function:fugit
    private  var name:String
    constructor(function: fugit,name: String,aliases: List<String>){
        this.function=function
        this.name=name
        this.errorfunction=fun(_: MessageReceivedEvent, error: Error){
            println(error)
        }
        this.commandslist= mutableMapOf()
        this.aliases=aliases
        this.commandlist=mutableListOf()
        this.group= mutableMapOf()
    }

    fun error(function:errorfn){
        this.errorfunction=function
    }
    fun command(function:fugit,name:String,aliases: List<String>):Command{
        var command= Command(function,name,aliases)
        commandlist.add(command)
        return command
    }
    fun run(): MutableMap<String, fugit> {
        //event.message.contentDisplay.drop(1).split(" ").drop(1)[0]
        for(i in commandlist){
            i.run().forEach {
                commandslist[it.key]=it.value
            }
        }
        var w=fun(event: MessageReceivedEvent){
            try{
                function(event)
                if(event.message.contentDisplay.drop(1).split(" ").drop(1).isNotEmpty()){
                    commandslist[event.message.contentDisplay.drop(1).split(" ").drop(1)[0]]?.let{it(event)}
                }

            }catch (error:Error){
                errorfunction(event,error)
            }
        }
        group[name]=w
        for(i in aliases){
            group[i]=w
        }
        return group
    }
}