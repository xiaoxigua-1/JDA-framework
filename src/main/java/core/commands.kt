package core

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

typealias  fugit =  (event:MessageReceivedEvent) -> Unit
typealias errorfn=(event:MessageReceivedEvent,error:Error)->Unit


open class Commands {
    private var commandslist: MutableMap<String,fugit> = mutableMapOf()
    var commandlist:MutableList<Command> = mutableListOf()
    var grouplist:MutableList<Group> = mutableListOf()
    fun run():MutableMap<String,fugit>{
        commands()
        for(i in grouplist){
            i.run().forEach {
                commandslist[it.key]=it.value
            }
        }
        for(i in commandlist){
            i.run().forEach{
                commandslist[it.key]=it.value
            }
        }
        return commandslist
    }
    fun command(function:fugit, name: String,aliases: List<String>):Command{
        val command=Command(function,name,aliases)
        commandlist.add(command)
        return command
    }
    fun group(function:fugit, name:String,aliases: List<String>): Group {
        val group=Group(function,name,aliases)
        grouplist.add(group)
        return group
    }
    open fun commands(){
    }
}