package cmds

import core.Commands
import net.dv8tion.jda.api.events.message.MessageReceivedEvent


class E: Commands(){
    override fun commands(){
        command(fun(_: MessageReceivedEvent){

        },"fucc",listOf())
    }

}