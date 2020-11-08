package cmds

import core.Bot
import core.addcog
import core.bot
import net.dv8tion.jda.api.JDA

fun setup(jda: JDA): Bot {
    addcog(W(jda).run())
    addcog(E(jda).run())
    return bot
}