package ion.content;

import arc.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.entities.*;
import mindustry.entities.effect.*;

import ion.defs.*;

public class IonFx{
    public static Effect
    
    geometryCharge = new Effect(120f, e -> {
        Draw.color(IColor.energy);
        Lines.stroke(e.fin() * 5f);
        Lines.square(e.x, e.y, e.fout(Interp.pow3In) * 30f, e.fin(Interp.smooth) * 180f);
        Lines.square(e.x, e.y, e.fout(Interp.pow3Out) * 30f, e.fin(Interp.smooth) * -180f);
        Lines.square(e.x, e.y, e.fout(Interp.pow10In) * 50f, e.fin(Interp.smooth) * -180f);
        Lines.square(e.x, e.y, e.fout(Interp.pow10Out) * 50f, e.fin(Interp.smooth) * 180f);
    }),
    
    chargeEffect = new Effect(140f, e -> {
        Draw.color(IColor.energy);
        Lines.stroke(e.fin(Interp.smoother) * 9f);
        Lines.line(e.x, e.y, e.x + e.fout(Interp.pow3Out) * 50f, e.y);
        Lines.line(e.x, e.y, e.x + e.fout(Interp.pow3Out) * -50f, e.y);
        Lines.line(e.x, e.y, e.x + e.fout(Interp.pow5In) * 40f, e.y + e.fout(Interp.pow5Out) * 50f);
        Lines.line(e.x, e.y, e.x + e.fout(Interp.pow5In) * -40f, e.y + e.fout(Interp.pow5Out) * 50f);
        Lines.line(e.x, e.y, e.x + e.fout(Interp.pow5In) * 40f, e.y + e.fout(Interp.pow5Out) * -50f);
        Lines.line(e.x, e.y, e.x + e.fout(Interp.pow5In) * -40f, e.y + e.fout(Interp.pow5Out) * -50f);
    }),
    
    xeusCharge = new MultiEffect(geometryCharge, chargeEffect);
}