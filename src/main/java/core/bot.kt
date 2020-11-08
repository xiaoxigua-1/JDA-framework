package core

class Bot{
    var commandslist:MutableMap<String,fugit>

    constructor() {
        this.commandslist = mutableMapOf()
    }
    fun add(x:MutableMap<String,fugit>){
        x.forEach{commandslist[it.key]=it.value}
    }
}
val bot=Bot()

fun addcog(x:MutableMap<String,fugit>){
    bot.add(x)
}