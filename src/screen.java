package src;
import java.awt.*;

public abstract class screen {
    public abstract void render(Graphics2D g);
    public abstract void onClick(int x, int y);

    public void setArrays(int[] array, int[] pointers, int size) {
    }
}
