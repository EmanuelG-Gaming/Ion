package ion.content

import arc.graphics.*
import arc.graphics.g2d.*
import mindustry.type.*

import ion.defs.*

object IonStatusEffects{
    
    lateinit var petrified: StatusEffect
    
    fun load(){
        petrified = object : StatusEffect("petrified"){
            init{
                speedMultiplier = 0f
                healthMultiplier = 0.8f
                reloadMultiplier = 0f
                color = Color.valueOf("979aa6")
            }
            
            override fun draw(unit: mindustry.gen.Unit){
                Draw.z(180f)
                Draw.color(Color.gray)
                IDraw.unit(unit)
            }
        }
    }
}
