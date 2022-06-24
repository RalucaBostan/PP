package P2

import cnames.structs.SDL_Renderer
import kotlinx.cinterop.*
import sdl.SDL_ALPHA_OPAQUE
import sdl.SDL_Rect
import sdl.SDL_RenderFillRect
import sdl.SDL_SetRenderDrawColor

class GPanel(val ren: CPointer<SDL_Renderer>, val _ID : Int, val _x : Int, val _y : Int, val _w : Int, val _h : Int, val _back : Color) :
    GObject(ren, _ID, "", _x,_y,_w,_h,_back, Color(0U, 0U, 0U), null){

    override fun Paint(){
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
        childs.forEach {
            it.Paint()
        }
    }

    override fun HandleClickDown(root: GObject, x: Int, y: Int) {
        childs.forEach {
            if (it.IsHit(x, y))
                it.HandleClickDown(root, x, y)
        }
    }

    override fun HandleClickUp(root: GObject, x: Int, y: Int) {
        childs.forEach {
            if (it.IsHit(x, y))
                it.HandleClickUp(root, x, y)
        }
    }

    override fun HandleKey(key: Any, root: GObject) {
        childs.forEach {
            it.HandleKey(key, root)
        }
    }
}