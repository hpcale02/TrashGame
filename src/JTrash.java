import com.hpc_ale.Control.Controller;
import com.hpc_ale.Model.JTrashGame;
import com.hpc_ale.View.StartFrame;

public class JTrash {
    public static void main(String[] args) {
        StartFrame startFrame = new StartFrame();
        JTrashGame jTrashGame = new JTrashGame();
        Controller controller = new Controller(startFrame, jTrashGame);
    }
}