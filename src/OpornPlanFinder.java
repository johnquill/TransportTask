

public class OpornPlanFinder {
    public static void find(TransportTask task) {
        int i = 0;
        int j = 0;
        int[] arrPost = task.arrPost.clone();
        int[] arrPotr = task.arrPotr.clone();
        while (true) {
            if (arrPotr[i] < arrPost[j]) {
                task.matr[i][j].value = arrPotr[i];
                task.basis.add(new Cell(i, j, task.matr[i][j].value, task.matr[i][j].price, true));
                task.matr[i][j].basis = true;
                arrPost[j] -= arrPotr[i];
                arrPotr[i] -= arrPotr[i];
                i++;
            } else {
                task.matr[i][j].value = arrPost[j];
                task.basis.add(new Cell(i, j, task.matr[i][j].value, task.matr[i][j].price, true));
                task.matr[i][j].basis = true;
                arrPotr[i] -= arrPost[j];
                arrPost[j] -= arrPost[j];
                j++;
            }
            if (i == arrPotr.length || j == arrPost.length) {
                break;
            }
        }
        if (task.basis.size() < task.nPost + task.nPotr - 1) {
            System.out.println("План выражден, я такое решать не умею");
            System.exit(0);
        }
        if (includeNotNull(arrPost) || includeNotNull(arrPotr)) {
            System.out.println("Задача не разрешима");
            System.exit(0);
        }
    }

    private static boolean includeNotNull(int[] arr) {
        for (int el: arr) {
            if (el != 0) {
                return true;
            }
        }
        return false;
    }
}
