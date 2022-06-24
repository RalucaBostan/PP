package P2

import cnames.structs.SDL_Renderer
import kotlinx.cinterop.*
import sdl.*

class GButton(val ren: CPointer<SDL_Renderer>, val _ID : Int, val _cap : String, val _x : Int, val _y : Int, val _back : Color, val _fore : Color, val _hnd :  ((GObject) -> Unit)?, val hk : Char) :
    GObject(ren, _ID, _cap, _x,_y, _cap.length * 8 + 32,24, _back, _fore, _hnd){

    private val hot_key : Char = hk

    private fun DrawPressed(){
        val arena = Arena()
        val rect : SDL_Rect
        rect = arena.alloc<SDL_Rect>()
        rect.x=x+2
        rect.y=y+2
        rect.w=w
        rect.h=h

        SDL_SetRenderDrawColor(renderer, back.r, back.g, back.b, SDL_ALPHA_OPAQUE.toUByte())
        memScoped {
            SDL_RenderFillRect(renderer, rect.ptr.reinterpret())
        }
        DrawString(x+2 + w / 2 - caption.length * 8 / 2, y+2 + 4, caption)

        SDL_SetRenderDrawColor(renderer, 0, 0, 0, SDL_ALPHA_OPAQUE.toUByte())
        SDL_RenderDrawLine(renderer, x + 2, y + 2, x + 2 + w - 1, y + 2)
        SDL_RenderDrawLine(renderer, x + 2, y + 2, x + 2, y + 2 + h - 1)

        SDL_SetRenderDrawColor(renderer, 255, 255, 255, SDL_ALPHA_OPAQUE.toUByte())
        SDL_RenderDrawLine(renderer, x + 2 + w - 1, y + 2 + 2, x + 2 + w - 1, y + 2 + h - 1)
        SDL_RenderDrawLine(renderer, x + 2, y + 2 + h - 1, x + 2 + w - 1, y + 2 + h - 1)
    }

    private fun DrawReleased(){
        val arena = Arena()
        val rect : SDL_Rect
        rect = arena.alloc<SDL_Rect>()
        rect.x=x
        rect.y=y
        rect.w=w
        rect.h=h
        SDL_SetRenderDrawColor(renderer, back.r, back.g, back.b, SDL_ALPHA_OPAQUE.toUByte())
        memScoped {
            SDL_RenderFillRect(renderer, rect.ptr.reinterpret())
        }
        DrawString(x + w / 2 - caption.length * 8 / 2, y + 4, caption)

        SDL_SetRenderDrawColor(renderer, 255, 255, 255, SDL_ALPHA_OPAQUE.toUByte())
        SDL_RenderDrawLine(renderer, x, y, x + w - 2, y)
        SDL_RenderDrawLine(renderer, x, y, x, y + h - 2)

        SDL_SetRenderDrawColor(renderer, 0, 0, 0, SDL_ALPHA_OPAQUE.toUByte())
        SDL_RenderDrawLine(renderer, x + w - 1, y + 1, x + w - 1, y + h - 1)
        SDL_RenderDrawLine(renderer, x, y + h - 1, x + w - 1, y + h - 1)
    }

    override fun Paint(){
        if (!state)
            DrawReleased()
        else
            DrawPressed()

        childs.forEach {
            it.Paint()
        }
    }

    override fun HandleClickDown(root : GObject, x : Int, y : Int){
        state = true
    }

    override fun HandleClickUp(root : GObject, x : Int, y : Int){
        state = false

        if (IsHit(x,y)){
            if (chandler != null)
                chandler?.invoke(root)
        }
    }

    override fun HandleKey(key : Any, root : GObject){
        if (hot_key == key && chandler != null)
            chandler?.invoke(root)

        childs.forEach {
            it.HandleKey(key, root)
        }
    }
}