package controller;
import view.CustomView;

import java.util.concurrent.BlockingQueue;

public class Controller {
    BlockingQueue<Message> queue;
    ColorValueModel colorValueModel;
    CustomView view;

    public Controller(BlockingQueue<Message> queue, ColorValueModel colorValueModel, CustomView view) {
        this.queue = queue;
        this.colorValueModel = colorValueModel;
        this.view = view;
    }

    public void mainLoop() {
        while (view.isDisplayable()) {
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }

            if(message.getClass() == ChangeColorValueMessage.class) {
                // button updateStudentName was clicked
                ChangeColorValueMessage msg = (ChangeColorValueMessage) message;
                colorValueModel.setRedValue(msg.getRedValue()); // update model
                colorValueModel.setGreenValue(msg.getGreenValue()); // update model
                colorValueModel.setBlueValue(msg.getBlueValue()); // update model
                view.updateBarChart(colorValueModel.getRedValue(),
                        colorValueModel.getGreenValue(), colorValueModel.getBlueValue()); // update view
            }
        }
    }
}

