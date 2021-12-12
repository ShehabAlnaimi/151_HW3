package view;

import controller.ChangeColorValueMessage;
import controller.Message;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class CustomView extends JFrame {

    BlockingQueue<Message> queue;

    JTextField redTextField;
    JTextField greenTextField;
    JTextField blueTextField;

    JButton updateButton;
    JButton resetButton;

    JLabel redLabel;
    JLabel greenLabel;
    JLabel blueLabel;

    JLabel redRectangle = new JLabel("0");
    JLabel greenRectangle = new JLabel("0");
    JLabel blueRectangle = new JLabel("0");

    public void layoutMarginsLabel(JLabel label){
        Dimension size = label.getPreferredSize();
        label.setBounds(50, 50, size.width, size.height);
    }

    public void layoutMarginsTextField(JTextField label){
        Dimension size = label.getPreferredSize();
        label.setBounds(50, 50, size.width, size.height);
    }

    public CustomView(BlockingQueue<Message> queue){
        this.queue = queue;
        this.redLabel = new JLabel("Red");
        this.greenLabel = new JLabel("Green");
        this.blueLabel = new JLabel("Blue");

        this.redTextField = new JTextField(10);
        this.greenTextField = new JTextField(10);
        this.blueTextField = new JTextField(10);

        this.updateButton = new JButton("Update");
        this.resetButton = new JButton("Reset");

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        GridLayout leftGridLayout = new GridLayout(0, 1);
        GridLayout rightGridLaout = new GridLayout(0, 3);

        leftPanel.setLayout(leftGridLayout);
        leftPanel.add(redLabel);
        leftPanel.add(redTextField);
        leftPanel.add(greenLabel);
        leftPanel.add(greenTextField);
        leftPanel.add(greenLabel);
        leftPanel.add(greenTextField);
        leftPanel.add(blueLabel);
        leftPanel.add(blueTextField);

        leftPanel.add(updateButton);
        leftPanel.add(resetButton);

        rightPanel.setLayout(rightGridLaout);
        rightPanel.add(redRectangle);
        rightPanel.add(greenRectangle);
        rightPanel.add(blueRectangle);

        updateButton.addActionListener(e -> {
            int red = Integer.valueOf(redTextField.getText());
            int green = Integer.valueOf(greenTextField.getText());
            int blue = Integer.valueOf(blueTextField.getText());
            try {
                Message msg = new ChangeColorValueMessage(red, green, blue);
                queue.put(msg);
            } catch (InterruptedException exception) {
                // do nothing
            }
        });

        this.add(leftPanel);
        this.add(rightPanel);

        this.setSize(300, 300);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void updateBarChart(int redValue, int greenValue, int blueValue){
        redRectangle.setText(String.valueOf(redValue));
        greenRectangle.setText(String.valueOf(greenValue));
        blueRectangle.setText(String.valueOf(blueValue));
    }

}
