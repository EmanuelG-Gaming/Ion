package ion.world.blocks.production

import arc.*
import arc.math.*
import arc.util.*
import arc.util.io.*
import arc.graphics.*
import arc.graphics.g2d.*
import mindustry.gen.*
import mindustry.type.*
import mindustry.world.blocks.production.*
import mindustry.world.blocks.environment.*
import mindustry.content.*
import mindustry.entities.*
import mindustry.graphics.*

import ion.world.blocks.*

/** A Drill that can only be placed on a specific floor. Exists to prevent conflicts with other mods. */
open class SingleFloorDrill : LimitedBlock{
    
    var outputItem = Items.copper
    var drillEffect = Fx.none
    var drillTime = 120f
    var drillBitSpeed = 2.3f
    var amount = 3
    
    constructor(name: String, floor: Floor, output: Item) : super(name, floor){
        outputItem = output
        update = true
        solid = true
        hasItems = true
        itemCapacity = 15
        category = Category.production
    }
    
    inner class SingleFloorBuild : Building(){
        
        var progress = 0f
        
        override fun updateTile(){
            super.updateTile()
            if(floor() != floor) kill() //yeah screw you payload unit users
            if(outputItem != null){
                
                if(!items.empty()){
                    dump(outputItem)
                }
                
                progress += Time.delta * efficiency()
                
                if(progress >= drillTime){
                    if(items.has(outputItem, itemCapacity)){
                        items.set(outputItem, itemCapacity)
                        progress = 0f;
                    } else {
                        items.add(outputItem, amount)
                        drillEffect.at(x + Mathf.range(block.size * 6), y + Mathf.range(block.size * 6))
                        progress = 0f;
                    }
                }
            }
        }
        
        override fun drawCracks(){}
        
        fun drawDefaultCracks(){
            super.drawCracks()
        }
        
        fun drillBitRot(): Float{
            if(items.has(outputItem, itemCapacity)){
                return 0f
            }
            return Time.time * drillBitSpeed * efficiency()
        }
        
        override fun draw(){
            Draw.z(Layer.block)
            Draw.rect(region, x, y)
            Draw.z(Layer.block - 2f)
            Drawf.shadow(region, x, y)
            Draw.z(Layer.blockCracks)
            drawDefaultCracks()
            
            Draw.z(Layer.block)
            Drawf.spinSprite(Core.atlas.find("${block.name}-rotator"), x, y, drillBitRot())
            Draw.rect("${block.name}-top", x, y)
        }
        
        override fun write(write: Writes){
            super.write(write)
            
            write.f(progress)
        }
        
        override fun read(read: Reads, revision: Byte){
            super.read(read, revision)
            
            progress = read.f()
        }
    }
}
