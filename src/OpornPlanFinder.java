

public class OpornPlanFinder {
    public static void find(TransportTask task) {
        int i = 0;
        int j = 0;
        int[] arrPost = task.arrPost.clone();
        int[] arrPotr = task.arrPotr.clone();
        do {
            if (arrPotr[i] < arrPost[j]) {
                task.values[i][j] = arrPotr[i];
                arrPotr[i] -= arrPotr[i];
                arrPost[j] -= arrPotr[i];
                task.basis.add(new Cell(i, j, task.values[i][j]));
                i++;
            } else {
                task.values[i][j] = arrPost[j];
                arrPost[j] -= arrPost[j];
                arrPotr[i] -= arrPost[j];
                j++;
            }
        } while (i != arrPotr.length && j != arrPost.length);
        if (task.basis.size() < task.nPost + task.nPotr) {
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
