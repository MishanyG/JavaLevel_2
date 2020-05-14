package HomeWork_1L2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;

public class MainWindow extends JFrame {

    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private int size = 1;
    Sprite[] spritesIn = new Sprite[size];
    Sprite[] spritesOut;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }

    private MainWindow()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        MainCanvas canvas = new MainCanvas(this);
        initApplication();
        canvas.setBackground(Color.GRAY);
        add(canvas);
        setTitle("Circles");
        setVisible(true);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent event)
            {
                super.mouseReleased(event);
                if(event.getButton() == MouseEvent.BUTTON1)
                {
                    spritesOut = new Sprite[++size];
                    resize();
                    spritesIn[size - 1] = new Ball();
                }
                if(event.getButton() == MouseEvent.BUTTON3)
                {
                    try
                    {
                        if(size > 0)
                        {
                            spritesOut = new Sprite[--size];
                            resize();
                        }
                        else
                        {
                            throw new Exception("Мячиков совсем не осталось!");
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
    }

    private void resize()
    {
        spritesOut = Arrays.copyOf(spritesIn, size);
        spritesIn = new Sprite[size];
        spritesIn = Arrays.copyOf(spritesOut, size);
    }

    private void initApplication()
    {
        for (int i = 0; i < spritesIn.length; i++)
        {
            spritesIn[i] = new Ball();
        }
    }

    void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (int i = 0; i < spritesIn.length; i++) {
            spritesIn[i].update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (int i = 0; i < spritesIn.length; i++) {
            spritesIn[i].render(canvas, g);
        }
    }
}
