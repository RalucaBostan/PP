package P2

import cnames.structs.SDL_Renderer
import kotlinx.cinterop.*
import sdl.*

class GEdit(val ren: CPointer<SDL_Renderer>, val _ID : Int, val _x : Int, val _y : Int, val _w : Int, val _back : Color, val _fore : Color, val _hnd :  ((GObject) -> Unit)?) :
    GObject(ren, _ID, "", _x,_y,_w,24,_back, _fore, _hnd) {

    private val max_chars = (w-12)/8

    fun AddChar(ch : Char){
        if (caption.length <= max_chars)
            caption += ch
    }

    fun AddString(S : String){
        caption += S
    }

    fun SetText(S : String){
        if (S.length <= max_chars)
            caption = S
        else
            caption = S.subSequence(0, max_chars) as String
    }

    fun Clear(){
        caption = ""
    }

    fun GetText() : String{
        return caption
    }

    override fun Paint(){
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
        DrawString(x+2+w/2-caption.length * 8 / 2, y + 2 + 4, caption)

        SDL_SetRenderDrawColor(renderer, 0, 0, 0, SDL_ALPHA_OPAQUE.toUByte())
        SDL_RenderDrawLine(renderer, x + 2, y + 2, x + 2 + w - 1, y + 2);
        SDL_RenderDrawLine(renderer, x + 2, y + 2, x + 2, y + 2 + h - 1);

        SDL_SetRenderDrawColor(renderer, 255, 255, 255, 255);
        SDL_RenderDrawLine(renderer, x + 2 + w - 1, y + 2 + 1, x + 2 + w - 1, y + 2 + h - 1);
        SDL_RenderDrawLine(renderer, x + 2, y + 2 + h - 1, x + 2 + w - 1, y + 2 + h - 1);
    }

    override fun HandleClickDown(root : GObject, x : Int, y : Int ){

    }

    override fun HandleClickUp(root : GObject, x : Int, y : Int){

    }

    override fun HandleKey(key: Any, root: GObject) {
        if (chandler != null){
            AddChar(key as Char)
            chandler?.invoke(root)
        }
    }
}

