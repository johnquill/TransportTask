import java.util.List;

public class TransportTask {

    int nPost;
    int nPotr;
    int[] arrPost;
    int[] arrPotr;
    int[][] prices;
    int[][] values;

    List<Cell> basis;

    public TransportTask(int nPost, int nPotr, int[] arrPost, int[] arrPotr, int[][] prices) {
        this.nPost = nPost;
        this.nPotr = nPotr;
        this.arrPost = arrPost;
        this.arrPotr = arrPotr;
        this.prices = prices;
        this.values = new int[nPotr][nPost];
    }
}
