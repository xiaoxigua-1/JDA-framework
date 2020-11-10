package core

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

internal typealias  fugit =  (event:MessageReceivedEvent) -> Unit
internal typealias errorfn=(event:MessageReceivedEvent,error:Error)->Unit
internal typealias onready=()->Unit

open class Commands: ListenerAdapter() {
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
    fun runCommandError(): errorfn {
        return fun(event:MessageReceivedEvent,error:Error){
            onCommandError(event,error)
        }
    }
    protected fun command(function:fugit, name: String,aliases: List<String>):Command{
        val command=Command(function,name,aliases)
        commandlist.add(command)
        return command
    }
    protected fun group(function:fugit, name:String,aliases: List<String>): Group {
        val group=Group(function,name,aliases)
        grouplist.add(group)
        return group
    }
    protected open fun commands(){
    }

    protected open fun onCommandError(event:MessageReceivedEvent,error:Error){
    }
}
