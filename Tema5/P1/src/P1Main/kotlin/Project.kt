package P1

import kotlinx.cinterop.*
import platform.posix.*
import sdl.SDL_Delay

enum class UserCommand {
    LEFT,
    RIGHT,
    DOWN,
    UP,
    ACTION,
    EXIT
}

interface GameFieldVisualizer {
    fun refresh()
}

interface UserInput {
    fun readCommands(): List<UserCommand>
}

class Project(val visualizer: GameFieldVisualizer, val userInput: UserInput) {
    fun Run() {
        visualizer.refresh()
        mainLoop()
    }

    private fun mainLoop() {
        while (true) {
            SDL_Delay(1000 / 60)
            val commands = userInput.readCommands()
            for (cmd in commands) {
                when (cmd) {
                    UserCommand.EXIT -> return
                    UserCommand.LEFT -> print("left")
                    UserCommand.RIGHT -> print("right")
                    UserCommand.DOWN -> print("down")
                    UserCommand.UP -> print("up")
                    UserCommand.ACTION -> print("action")
                }
                visualizer.refresh()
            }
            visualizer.refresh()
        }
    }
}

