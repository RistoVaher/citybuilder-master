package engine;


import org.lwjgl.glfw.GLFW;

import rendering.Loader;
import rendering.RawModel;
import rendering.Renderer;
import rendering.Window;
import shaders.StaticShader;

public class Main { // TODO: all the window stuff should be moved into the Input class(not created yet)
    private static final int WIDTH = 800, HEIGHT = 600, FPS = 60;
    private static Window window = new Window(WIDTH, HEIGHT, FPS, "ight fella");

    public static void main(String[] args) {
        window.create();
        window.setBackgroundColor(0.3f, 0.04f, 0.03f);


        Renderer renderer = new Renderer();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        float[] vertices = {
                -0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0.5f, 0
        };

        int[] indices = {
                0, 1, 3, 3, 1, 2
        };

        RawModel model = loader.loadToVao(vertices, indices);

        while (!window.closed()) {
            if (window.isUpdating()) {

                window.update();
                renderer.prepare();
                shader.start();
                renderer.render(model);
                shader.stop();
                if (window.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
                    System.out.println("Space is down");
                }
                window.swapBuffers();
            }
        }
        shader.cleanUp();
        loader.cleanUp();
        window.stop();
    }
}