import java.util.List;

public class BasisHandler {

    public static boolean isEmptyStr(List<Cell> basis, int i) {
        for (Cell cell : basis) {
            if (cell.i == i) {
                return false;
            }
        }
        return true;
    }
}
