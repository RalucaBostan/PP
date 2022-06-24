package P2

import cnames.structs.SDL_Renderer
import font8x16
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import platform.windows.UINT8
import platform.windows.boolean
import sdl.*

class Color(_r : Uint8, _g:Uint8, _b:Uint8) {
    val r : Uint8 = _r
    val g : Uint8 = _g
    val b : Uint8 = _b
}

abstract class GObject(val renderer: CPointer<SDL_Renderer>, val ID : Int, val cap : String, val x : Int, val y : Int, val w : Int, val h : Int, val back : Color, val fore : Color, val hnd :  ((GObject) -> Unit)?) {
    protected var state = false
    protected val childs = mutableListOf<GObject>()
    protected var caption : String = cap
    protected var chandler : ((GObject) -> Unit)? = hnd

    fun Add(Obj : GObject){
        childs += Obj
    }

    fun DrawChar(x : Int, y:Int, c:Char){
        SDL_SetRenderDrawColor(renderer, fore.r, fore.g, fore.b, SDL_ALPHA_OPAQUE.toUByte())
        for (i in 0..15){
            for (j in 0..7){
                val pixel = font8x16[c.toInt()][i] and (1 shl (7 - j))
                if (pixel != 0)
                    SDL_RenderDrawPoint(renderer, x + j, y+i)
            }
        }
    }

    fun DrawString(x : Int, y : Int, name : String){
        for (i in 0..name.length - 1){
            DrawChar(x + i*8, y, name[i])
        }
    }

    fun IsHit(xx : Int, yy : Int) : Boolean {
        return xx >= x && xx < x+w && yy >= y && yy <y+h
    }

    fun FindObject(id : Int) : GObject? {
        if (id == this.ID)
            return this
        childs.forEach {
            val item = it.FindObject(id)
            if (item != null)
                return item
        }
        return null
    }

    abstract fun Paint()
    abstract fun HandleClickUp(root : GObject, x : Int, y:Int)
    abstract fun HandleClickDown(root : GObject, x : Int, y:Int)
    abstract fun HandleKey(key : Any, root : GObject)
}

