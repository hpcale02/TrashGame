import com.hpc_ale.ui.GameFrame;

public class JTrash {
    public static void main(String[] args) {

        int numPlayer = 2;

        if (numPlayer == 2) {
            new GameFrame(2);
        } else if (numPlayer == 3) {
            new GameFrame(3);
        } else if (numPlayer == 4) {
            new GameFrame(4);
        }
    }
}