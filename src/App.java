import controller.ColorValueModel;
import controller.Controller;
import controller.Message;
import view.CustomView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        ColorValueModel model = new ColorValueModel(0,0,0);
        CustomView view = new CustomView(queue);
        Controller controller = new Controller(queue, model, view);
        controller.mainLoop();
    }
}

