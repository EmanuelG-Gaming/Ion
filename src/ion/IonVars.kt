package ion

import arc.*
import mindustry.*
import mindustry.ui.dialogs.*
import mindustry.game.EventType.Trigger

import ion.game.*
import ion.ui.dialogs.*

object IonVars{

    val ai = AISwitcherDialog()
    
    val healthbars = Healthbars
    
    fun load(){
        Events.run(Trigger.update){
            if(ai.active){
                if(Vars.mobile){
                    Core.camera.position.set(Vars.player.unit().x, Vars.player.unit().y)
                }
            }
        }
    }
}
