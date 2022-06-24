fun main(args: Array<String>) {
    val width = 640
    val height = 480

    val visualizer = SDL_Visualizer(width, height)
    val project = Project(visualizer, visualizer)
    project.Run()

    return
}
