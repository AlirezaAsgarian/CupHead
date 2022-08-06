package mainmodule.util;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollisionController {
    static Logger logger = LoggerFactory.getLogger(CollisionController.class);

    public synchronized static boolean haveCollision(Image i1, Image i2, Bounds b1, Bounds b2) {
        if (i2 == null || i1 == null) return false;
        if (!b1.intersects(b2)) return false;
        PixelReader pr1 = i1.getPixelReader();
        PixelReader pr2 = i2.getPixelReader();
        logger.debug("first image bound {} width {} height {}", b1, i1.getWidth(), i2.getHeight());
        logger.debug("second image bound {} width {} height {}", b2, i2.getWidth(), i2.getHeight());

        for (int x = 1; x < i1.getWidth() - 1; x++) {
            for (int y = 1; y < i1.getHeight() - 1; y++) {
                Location coordinate1 = new Location(x, y);
                Location coordinate2 = calculateSecondPointCoordinate(coordinate1, b1, b2);
                if (!isBorderPoint(coordinate2, b2.getWidth(), b2.getHeight()) && isSecondImageBoundContainsCurrentPoint(b2, (int) (coordinate1.getX() + b1.getMinX()), (int) (coordinate1.getY() + b1.getMinY())) && pointHasCollision(pr1, pr2, coordinate1, coordinate2))
                    return true;
            }
        }
        return false;
    }

    private static boolean isBorderPoint(Location coordinate2, double width, double height) {
        if (coordinate2.getY() == height || coordinate2.getX() == width) return true;
        return false;
    }


    private static Location calculateSecondPointCoordinate(Location currentPoint, Bounds b1, Bounds b2) {
        double secondImageX = calculateCurrentCoordinateOfSecondImage((int) (currentPoint.getX() + b1.getMinX()), b2.getMinX());
        double secondImageY = calculateCurrentCoordinateOfSecondImage((int) (currentPoint.getY() + b1.getMinY()), b2.getMinY());
        return new Location(secondImageX, secondImageY);
    }

    private static double calculateCurrentCoordinateOfSecondImage(int currentPoint, double i2Bound) {
        return (currentPoint - i2Bound);
    }

    private static boolean isSecondImageBoundContainsCurrentPoint(Bounds bound, int x, int y) {
        return bound.contains(x, y);

    }

    private static boolean pointHasCollision(PixelReader pr1, PixelReader pr2, Location currentPoint, Location secondImageCoordinate) {
        return !PixelOpacityIsZero(pr1, currentPoint.getX(), currentPoint.getY()) && !PixelOpacityIsZero(pr2, secondImageCoordinate.getX(), secondImageCoordinate.getY());
    }


    private static boolean PixelOpacityIsZero(PixelReader imagePixelReader, double x, double y) {
        try {
            return imagePixelReader.getColor((int) x, (int) y).getOpacity() == 0;
        } catch (IndexOutOfBoundsException e) {
            logger.debug("x = {} y = {}", x, y);
            return true;
        }
    }

}

