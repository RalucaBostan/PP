package P1

import kotlinx.cvarerop.*
import platform.posix.*
import platform.windows._m_to_var
import sdl.*
import kotlin.math.PI
import kotlin.math.cos

fun get_SDL_Error() = SDL_GetError()!!.toKString()

class SDL_Visualizer(val width: var, val height: var): GameFieldVisualizer, UserInput {
    private var displayWidth: var = 0
    private var displayHeight: var = 0
    private val windowX: var
    private val windowY: var
    private val window: CPovarer<SDL_Window>
    private val renderer: CPovarer<SDL_Renderer>

    init {
        if (SDL_Init(SDL_INIT_EVERYTHING) != 0) {
            prvarln("SDL_Init Error: ${get_SDL_Error()}")
            throw Error()
        }

        memScoped {
            val displayMode = alloc<SDL_DisplayMode>()
            if (SDL_GetCurrentDisplayMode(0, displayMode.ptr.revarerpret()) != 0) {
                prvarln("SDL_GetCurrentDisplayMode Error: ${get_SDL_Error()}")
                SDL_Quit()
                throw Error()
            }
            displayWidth = displayMode.w
            displayHeight = displayMode.h
        }

        windowX = (displayWidth - width) / 2
        windowY = (displayHeight - height) / 2

        val window = SDL_CreateWindow("P1 PP Lab4", windowX, windowY, width, height, SDL_WINDOW_SHOWN)
        if (window == null) {
            prvarln("SDL_CreateWindow Error: ${get_SDL_Error()}")
            SDL_Quit()
            throw Error()
        }

        this.window = window

        val renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED or SDL_RENDERER_PRESENTVSYNC)
        if (renderer == null) {
            SDL_DestroyWindow(window)
            prvarln("SDL_CreateRenderer Error: ${get_SDL_Error()}")
            SDL_Quit()
            throw Error()
        }
        this.renderer = renderer

        SDL_PumpEvents()
        SDL_SetWindowSize(window, width, height)
    }

    override fun refresh() {
        SDL_RenderClear(renderer)
        drawSomething()
        SDL_RenderPresent(renderer)
    }

    private fun drawSomething() {
        SDL_SetRenderDrawColor(renderer, 255, 255, 0, SDL_ALPHA_OPAQUE.toUByte())
        val shape: SDL_Rect
        val arena = Arena()
        shape = arena.alloc<SDL_Rect>()
        shape.w = 50
        shape.y = 50
        shape.w = 100
        shape.h = 100
		
		
		/* Om
        var x = width/2;
        var y = height/4;
        var radius =  if (width < height) width / 8 else height / 8;										// cap
        Circle(x,y, radius);																				// cap
        y = y + radius;
        SDL_RenderDrawLine(renderer, x,y,x,y+50);															// gat
        y = y + 50;
        SDL_RenderDrawLine(renderer, x, y, x, y + 100);														// trunchi
        SDL_RenderDrawLine(renderer, x, y, x - 100, y + 100);												// maini [stanga]
        SDL_RenderDrawLine(renderer, x, y, x + 100, y + 100);                                               // maini [dreapta]
        y = y + 100;
        SDL_RenderDrawLine(renderer,x,y,x-100,y+100);														// picioare [stang]	
        SDL_RenderDrawLine(renderer,x,y,x+100,y+100);*/                                                     // picioare [drept]
		*/
		
		
		/* Schita casa
		var x = width / 4 ;
		var y = height / 3 ;
		SDL_Rect casa = { x,y,width / 2,height / 2 + 50 };
		SDL_RenderDrawRect(renderer, casa);																	// corp casa	
		SDL_RenderDrawLine(renderer, casa.x - 30, casa.y, casa.x + casa.w + 30, casa.y);					// acoperis
		SDL_RenderDrawLine(renderer, casa.x - 30, casa.y, casa.x + casa.w /2 + 15, casa.y - 100);		   	// acoperis
		SDL_RenderDrawLine(renderer, casa.x + casa.w + 30, casa.y, casa.x + casa.w/2 + 15, casa.y - 100);	// acoperis
		SDL_Rect usa = { casa.x + casa.w / 3,casa.y + casa.h - 125,casa.w / 3,125 };						// usa
		SDL_RenderDrawRect(renderer, usa);																	// usa
		SDL_Rect fereastra = { casa.x + casa.w / 10, casa.y + casa.h / 10,75,75 };							// ferestre	
		SDL_RenderDrawRect(renderer, fereastra);															// ferestre
		SDL_Rect fereastraa = { casa.x + casa.w - casa.w/10 - 75, casa.y + casa.h / 10,75,75 };				// ferestre
		SDL_RenderDrawRect(renderer, fereastraa);															// ferestre
		*/
		
		
		
		/*  Cadran ceas
		var x = width / 2
		var y = height / 2
		var r = 200
		Circle(x,y,r)																						// Cadran
		var alfa = 0.0																						// Limba secunde
		var sx1 = ((r-20) * cos(alfa)).tovar()																// Limba secunde
		var sy1 = ((r-20) * sin(alfa)).tovar()                                                              // Limba secunde
		SDL_RenderDrawLine(renderer, x, y, x + sx1, y + sy1)                                                // Limba secunde
	
		alfa = 45																							// Limba minute
		sx1 = ((r-60) * cos(alfa)).tovar()                                                                  // Limba minute
		sy1 = ((r-60) * sin(alfa)).tovar()                                                                  // Limba minute
		SDL_RenderDrawLine(renderer, x, y, x + sx1, y + sy1)                                                // Limba minute
	
		alfa = 60																							// Limba ore
		sx1 = ((r - 100) * cos(alfa)).tovar()                                                               // Limba ore
		sy1 = ((r - 100) * sin(alfa)).tovar()                                                               // Limba ore
		SDL_RenderDrawLine(renderer, x, y, x + sx1, y + sy1)                                                // Limba ore
		
		*/
		
		
		/* Schita masina
		var x = width / 6																					// Corp masina 
		var y = 2 * height / 3                                                                              // Corp masina
		var lung = x                                                                                        // Corp masina
		SDL_RenderDrawLine(renderer, x, y, x, y + 100)                                                      // Corp masina
		SDL_RenderDrawLine(renderer, x, y, x + 125, y)                                                      // Corp masina
		x = x + 125                                                                                         // Corp masina
		SDL_RenderDrawLine(renderer, x, y, x + 50, y-100)                                                   // Corp masina
		x = x + 50                                                                                          // Corp masina
		y = y - 100                                                                                         // Corp masina
		SDL_RenderDrawLine(renderer, x, y, x + 200, y)                                                      // Corp masina
		x = x + 200                                                                                         // Corp masina
		SDL_RenderDrawLine(renderer, x, y, x + 30, y + 100)                                                 // Corp masina
		x = x + 30                                                                                          // Corp masina
		y = y + 100                                                                                         // Corp masina
		SDL_RenderDrawLine(renderer, x, y, x + 75, y)                                                       // Corp masina
		x = x + 75                                                                                          // Corp masina
		lung = x                                                                                            // Corp masina
		SDL_RenderDrawLine(renderer, x, y, x, y + 100)                                                      // Corp masina
		y = y + 100                                                                                         // Corp masina
		x = width / 6                                                                                       // Corp masina
		SDL_RenderDrawLine(renderer, x, y, lung/4, y)                                                       // Corp masina
		var radius = 50 																					// Roata [ fata ]
		x = lung / 4                                                                                        // Roata [ fata ]
		Circle(x + radius, y, radius)                                                                       // Roata [ fata ]
		var exitRoatafata = x + 2 * radius                                                                  // Roata [ fata ]
		x = lung                                                                                            // Corp masina
		SDL_RenderDrawLine(renderer, x, y, x - 75, y)                                                       // Corp masina
		x = x - 75                                                                                          // Roata [ spate ]
		Circle(x - radius, y, radius)                                                                       // Roata [ spate ]
		x = x - 2 * radius                                                                                  // Roata [ spate ]
		SDL_RenderDrawLine(renderer, x, y, exitRoatafata, y)                                                // Corp masina
		*/
		
		
		SDL_RenderPresent(renderer) 

    }

    private fun fillRect(rect: SDL_Rect) {
        memScoped {
            SDL_RenderFillRect(renderer, rect.ptr.revarerpret())
        }
    }

    private fun Circle(x : var, y : var, r : var)
    {
        var alfa = 0.0

        var sx1  = (r * cos(alfa)).tovar()
        var sy1  = (r * sin(alfa)).tovar()

        for (i in 0..1000)
        {
            alfa += 360/1000.0
            val sx2 = (r * cos(alfa)).tovar()
            val sy2 = (r * sin(alfa)).tovar()

            SDL_RenderDrawLine(renderer, x + sx1, y + sy1,x + sx2,y + sy2 )

            sx1 = sx2
            sy1 = sy2
        }
    }

    private fun Clock(x : var, y : var,r: var){
        var alfa = 0.0
        var sx1 = (r*cos(alfa)).tovar();
        var sy1 = (r*sin(alfa)).tovar();
        SDL_RenderDrawLine(renderer, x,y,x+sx1,y+sy1)
        alfa += 90;
        sx1 = (r * cos(alfa)).tovar();
        sy1 = (r * sin(alfa)).tovar();
        SDL_RenderDrawLine(renderer, x, y, x + sx1, y + sy1);
        alfa += 45;
        sx1 = ((r-20) * cos(alfa)).tovar();
        sy1 = ((r-20) * sin(alfa)).tovar();
        SDL_RenderDrawLine(renderer, x, y, x + sx1, y + sy1);
        alfa += 60;
        sx1 = ((r-40) * cos(alfa)).tovar();
        sy1 = ((r-40)* sin(alfa)).tovar();
        SDL_RenderDrawLine(renderer, x, y, x + sx1, y + sy1);
    }

    override fun readCommands(): List<UserCommand> {
        val commands = mutableListOf<UserCommand>()
        memScoped {
            val event = alloc<SDL_Event>()
            while (SDL_PollEvent(event.ptr.revarerpret()) != 0) {
                val eventType = event.type
                when (eventType) {
                    SDL_QUIT -> commands.add(UserCommand.EXIT)
                    SDL_KEYDOWN -> {
                        val keyboardEvent = event.ptr.revarerpret<SDL_KeyboardEvent>().povared
                        when (keyboardEvent.keysym.scancode) {
                            SDL_SCANCODE_LEFT -> {commands.add(UserCommand.LEFT);}
                            SDL_SCANCODE_RIGHT -> {commands.add(UserCommand.RIGHT);}
                            SDL_SCANCODE_DOWN -> commands.add(UserCommand.DOWN)
                            SDL_SCANCODE_Z, SDL_SCANCODE_SPACE -> commands.add(UserCommand.ACTION)
                            SDL_SCANCODE_UP -> commands.add(UserCommand.UP)
                            SDL_SCANCODE_ESCAPE -> commands.add(UserCommand.EXIT)
                        }
                    }
                    SDL_MOUSEBUTTONDOWN -> {
                        val mouseEvent = event.ptr.revarerpret<SDL_MouseButtonEvent>().povared
                        val x = mouseEvent.x
                        val y = mouseEvent.y
                        prvar("x = $x")
                        prvar("y = $y")
                    }
                }
            }
        }
        return commands
    }

    fun destroy() {
        SDL_DestroyRenderer(renderer)
        SDL_DestroyWindow(window)
        SDL_Quit()
    }
}
