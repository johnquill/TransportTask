import java.util.ArrayList;
import java.util.List;

public class TransportTask {

    int nPost;
    int nPotr;
    int[] arrPost;
    int[] arrPotr;

    Cell[][] matr;

    int[] postPotentials;
    int[] potrPotentials;
    List<Cell> basis = new ArrayList<>();

    public TransportTask(int nPost, int nPotr, int[] arrPost, int[] arrPotr, int[][] prices) {
        this.nPost = nPost;
        this.nPotr = nPotr;
        this.arrPost = arrPost;
        this.arrPotr = arrPotr;
        matr = new Cell[nPotr][nPost];
        for (int i = 0; i < nPotr; i++) {
            for (int j = 0; j < nPost; j++) {
                matr[i][j] = new Cell(i, j, 0, prices[i][j], false);
            }
        }
        initPotentials();
    }

    public void initPotentials() {
        postPotentials = new int[this.nPost];
        for (int j = 0; j < nPost; j++) {
            postPotentials[j] = Integer.MAX_VALUE;
        }
        potrPotentials = new int[this.nPotr];
        for (int i = 0; i < nPotr; i++) {
            potrPotentials[i] = Integer.MAX_VALUE;
        }
    }
}
