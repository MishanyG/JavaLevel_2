package HomeWork_1L2;

import java.awt.*;

public class Background extends Sprite
{
    private float genTime;

    @Override
    public void update(MainCanvas canvas, float deltaTime)
    {
        genTime += deltaTime;
        float genInter = 0.8f;
        if (genTime >= genInter)
        {
            genTime = 0;
            Color color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
            canvas.setBackground(color);
        }
    }
}
