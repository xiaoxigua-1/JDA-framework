package cmds

import core.Commands
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.message.MessageReceivedEvent


class E(jda: JDA): Commands(){
    override fun commands(){
        super.command(fun(event: MessageReceivedEvent){

        },"fucc",listOf())
    }
}