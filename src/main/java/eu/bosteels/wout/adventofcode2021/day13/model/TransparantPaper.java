package eu.bosteels.wout.adventofcode2021.day13.model;

import java.util.*;


public class TransparantPaper {

    private List<RandomDot> dots;

    public TransparantPaper(Scanner dotInput) {
        dots = readDotsFromInput(dotInput);
    }


    public void foldAlongX(int foldline) {
        if (!canFoldAlongX(foldline)) throw new IllegalArgumentException();
        int width = getWidth();
        for (int x = 0; x <= foldline; x++) {
            for (int y = 0; y <= getLength(); y++) {
                if (width - foldline >= foldline - x) {
                    Optional<RandomDot> landingDot = getDotAt(x, y);
                    Optional<RandomDot> foldingDot = getDotAt(foldline + (foldline - x), y);
                    if (foldingDot.isPresent()) {
                        if (landingDot.isEmpty()) {
                            dots.add(new RandomDot(x, y, true));
                        }
                        dots.remove(foldingDot.get());
                    }
                }
            }
        }
    }


    public void foldAlongY(int foldline) {
        if (!canFoldAlongY(foldline)) throw new IllegalArgumentException();

        int length = getLength();
        int width = getWidth();
        for (int y = 0; y <= foldline; y++) {
            for (int x = 0; x <= width; x++) {
                if (length - foldline >= foldline - y) {
                    Optional<RandomDot> landingDot = getDotAt(x, y);
                    Optional<RandomDot> foldingDot = getDotAt(x, foldline + (foldline - y));
                    if (foldingDot.isPresent()) {
                        if (landingDot.isEmpty()) {
                            dots.add(new RandomDot(x, y, true));
                        }
                        dots.remove(foldingDot.get());
                    }
                }
            }
        }

    }

    private boolean canFoldAlongX(int x) {
        if (x > getWidth()) return false;
        for (int y = 0; y <= getLength(); y++) {
            if (getDotAt(x, y).isPresent()) {
                return false;
            }
        }
        return true;
    }

    private boolean canFoldAlongY(int y) {
        if (y > getLength()) return false;
        for (int x = 0; x <= getWidth(); x++) {
            if (getDotAt(x, y).isPresent()) {
                return false;
            }
        }
        return true;
    }


    private Optional<RandomDot> getDotAt(int x, int y) {
        return dots.stream().filter(dot -> dot.getyCoord() == y && dot.getxCoord() == x).findAny();
    }

    private int getLength() {
        Optional<RandomDot> highestY = dots.stream().max(Comparator.comparingInt(RandomDot::getyCoord));
        return highestY.isPresent() ? highestY.get().getyCoord() : 0;
    }

    private int getWidth() {
        Optional<RandomDot> highestX = dots.stream().max(Comparator.comparingInt(RandomDot::getxCoord));
        return highestX.isPresent() ? highestX.get().getxCoord() : 0;
    }


    private List<RandomDot> readDotsFromInput(Scanner input) {
        List<RandomDot> dots = new ArrayList<>();
        while (input.hasNext()) {
            String line = input.nextLine();
            if (line.length() == 0) {
                break;
            }
            String[] coordinates = line.split(",");
            dots.add(new RandomDot(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), true));
        }
        return dots;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("");
        for (int y = 0; y <= getLength(); y++) {
            StringBuilder line = new StringBuilder("\t");
            for (int x = 0; x <= getWidth(); x++) {
                line.append(getDotAt(x, y).isPresent() ? "# " : ". ");
            }
            line.append("\n");
            out.append(line);
        }
        return out.toString();
    }

    public long getDotCount() {
        return dots.stream().filter(RandomDot::getIsDot).count();
    }
}
