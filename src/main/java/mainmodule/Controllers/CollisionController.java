package mainmodule.Controllers;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.ImagePattern;
import mainmodule.Controllers.Location;

public class CollisionController {
    public static boolean haveCollision(Image i1, Image i2,Bounds b1,Bounds b2) {
        if (i2 == null || i1 == null) return false;
        if(!b1.intersects(b2)) return false;
        PixelReader pr1 = i1.getPixelReader();
        PixelReader pr2 = i2.getPixelReader();
        for (int x = 0; x < i1.getWidth(); x++) {
            for (int y = 0; y < i1.getHeight(); y++) {
                Location coordinate1 = new Location(x, y);
                Location coordinate2 = calculateSecondPointCoordinate(coordinate1,b1,b2);
                if (isSecondImageBoundContainsCurrentPoint(b2, (int) (coordinate1.getX() + b1.getMinX()), (int) (coordinate1.getY() + b1.getMinY())) && pointHasCollision(pr1,pr2,coordinate1,coordinate2)) return true;
            }
        }
        return false;
    }


    private static Location calculateSecondPointCoordinate(Location currentPoint, Bounds b1, Bounds b2){
        int secondImageX = calculateCurrentCoordinateOfSecondImage((int) (currentPoint.getX() + b1.getMinX()),b1 .getMinX());
        int secondImageY = calculateCurrentCoordinateOfSecondImage((int) (currentPoint.getY() + b1.getMinY()),b2 .getMinY());
        return new Location(secondImageX,secondImageY);
    }
    private static int calculateCurrentCoordinateOfSecondImage(int currentPoint, double i2Bound) {
        return (int) (currentPoint - i2Bound);
    }
    private static boolean isSecondImageBoundContainsCurrentPoint(Bounds bound, int x,int y) {
        Boolean b = bound.contains(x, y);
        return b;
    }

    private static boolean pointHasCollision(PixelReader pr1, PixelReader pr2,Location currentPoint,Location secondImageCoordinate) {
        return !PixelOpacityIsZero(pr1, currentPoint.getX(), currentPoint.getY()) && !PixelOpacityIsZero(pr2, secondImageCoordinate.getX(), secondImageCoordinate.getY());
    }


    private static boolean PixelOpacityIsZero(PixelReader imagePixelReader, double x, double y) {
        try {
            boolean b = imagePixelReader.getColor((int) x, (int) y).getOpacity() == 0;
            return b;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

}

