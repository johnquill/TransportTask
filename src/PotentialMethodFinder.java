import java.util.*;

public class PotentialMethodFinder {
    public static boolean find(TransportTask task) {
        countPotentials(task);
        countValues(task);
        Worker.printMatr(task);
        while (isNotOptimum(task)) {
            recountBasis(task);
            task.initPotentials();
            countPotentials(task);
            countValues(task);
            Worker.printMatr(task);
        }
        return true;
    }

    private static void recountBasis(TransportTask task) {
        Cell start = findMin(task);
        List<Cell> cycle = new ArrayList<>();
        HashMap<Cell, List<Cell>> blockedCells = new HashMap<>();
        cycle.add(start);
        task.basis.add(start);
        start.basis = true;
        buildCycle(cycle, task.basis, blockedCells, Direction.ANY);
        Cell min = findMinInCycle(cycle);
        changeBasis(task.basis, cycle, min);
    }

    private static void changeBasis(List<Cell> basis, List<Cell> cycle, Cell min) {
        basis.remove(min);
        int minV = min.value;
        for (Cell cell : cycle) {
            if (cell.value >= 0) {
                cell.value += Math.pow(-1, cycle.indexOf(cell) % 2) * minV;
            }
        }
        cycle.get(0).value = minV;
        // TODO: 26.10.2022 нужно ли тут изменять значение в базисе
    }

    private static Cell findMinInCycle(List<Cell> cycle) {
        int minVal = Integer.MAX_VALUE;
        Cell minCell = null;
        for (Cell cell : cycle) {
            if (cell.value < minVal && cell.value >= 0 && cycle.indexOf(cell) % 2 == 1) {
                minCell = cell;
                minVal = minCell.value;
            }
        }
        return minCell;
    }

    private static List<Cell> buildCycle(List<Cell> cycle, List<Cell> basis, HashMap<Cell, List<Cell>> blockedCells,
                                         Direction direction) {
        Cell cur = cycle.get(cycle.size()-1);
        for (Cell cell : basis) {
            if (cell != cur && (!blockedCells.containsKey(cur) || !blockedCells.get(cur).contains(cell))) {
                if (cell.i == cur.i && direction != Direction.J) {
                    if (cell == cycle.get(0)) {
                        return cycle; // TODO: 26.10.2022 еще какая-то проблема с индексами?
                    } else {
                        cycle.add(cell);
                        return buildCycle(cycle, basis, blockedCells, Direction.J);
                    }
                } else if (cell.j == cur.j && direction != Direction.I) {
                    if (cell == cycle.get(0)) {
                        return cycle;
                    } else {
                        cycle.add(cell);
                        return buildCycle(cycle, basis, blockedCells, Direction.I);
                    }
                }
            }
        }
        List<Cell> blockedCellsForPrevCur = new ArrayList<>(addCurToBlocked(blockedCells, cycle, cur));
        blockedCells.put(cycle.get(cycle.size()-2), blockedCellsForPrevCur);
        cycle.remove(cycle.size()-1);
        Direction resDir = direction == Direction.I ? Direction.J : Direction.I;
        return buildCycle(cycle, basis, blockedCells, resDir);
    }

    private static List<Cell> addCurToBlocked(HashMap<Cell, List<Cell>> blockedCells, List<Cell> cycle, Cell cur) {
        if (cycle.size() < 2) {
            System.out.println("Не смог построить цикл");
            System.exit(-1);
        }
        List<Cell> blockedCellsForPrev = blockedCells.get(cycle.get(cycle.size() - 2));
        if (blockedCellsForPrev == null) {
            blockedCellsForPrev = Collections.singletonList(cur);
        } else {
            blockedCellsForPrev.add(cur);
        }
        return blockedCellsForPrev;
    }

    private static Cell findMin(TransportTask task) {
        int minVal = 0;
        Cell minCell = null;
        for (int i = 0; i < task.nPotr; i++) {
            for (int j = 0; j < task.nPost; j++) {
                if (task.matr[i][j].value < minVal) {
                    minCell = task.matr[i][j];
                    minVal = minCell.value;
                }
            }
        }
        return minCell;
    }

    private static void countValues(TransportTask task) {
        for (int i = 0; i < task.nPotr; i++) {
            for (int j = 0; j < task.nPost; j++) {
                if (!task.matr[i][j].basis) {
                    task.matr[i][j].value = task.matr[i][j].price - (task.potrPotentials[i] + task.postPotentials[j]);
                }
            }
        }
    }

    private static void countPotentials(TransportTask task) {
        int k = 0;
        while (BasisHandler.isEmptyStr(task.basis, k)) {
            k++;
        }
        task.potrPotentials[0] = 0;
        boolean f = true;
        while (f) {
            f = fillPotentials(task);
        }
    }

    private static boolean fillPotentials(TransportTask task) {
        boolean f = false;
        for (Cell cell : task.basis) {
            if (task.postPotentials[cell.j] == Integer.MAX_VALUE) {
                if (task.potrPotentials[cell.i] == Integer.MAX_VALUE) {
                    f = true;
                } else {
                    task.postPotentials[cell.j] = task.matr[cell.i][cell.j].price - task.potrPotentials[cell.i];
                }
            } else {
                if (task.potrPotentials[cell.i] == Integer.MAX_VALUE) {
                    task.potrPotentials[cell.i] = task.matr[cell.i][cell.j].price - task.postPotentials[cell.j];
                }
            }
        }
        return f;
    }

    private static boolean isNotOptimum(TransportTask task) {
        for (Cell[] str : task.matr) {
            for (Cell cell : str) {
                if (cell.value < 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
