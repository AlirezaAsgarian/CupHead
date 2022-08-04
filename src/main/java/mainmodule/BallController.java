package mainmodule;

public class BallController  {

//    private static Ball ball;
//    private static Board board;
//    private static double theta = 200;
//    private static int speed = 2;
//    public BallController(Ball ball1,Board board) {
//        BallController.ball = ball1;
//        BallController.board = board;
//        this.setCycleDuration(Duration.millis(1000));
//        this.setCycleCount(-1);
//    }
//
//    @Override
//    protected void interpolate(double v) {
//        moveBall(BallController.ball,speed,board);
//    }
//
//    private void moveBall(Ball ball, int speed, Board board) {
//        if (isHitBoard(ball, board)) theta = 360 - theta;
//        if (isHitTop(ball)) theta = -theta;
//        if (isHitRight(ball)) theta = 180 - theta;
//        if (isHitLeft(ball)) theta = 180 - theta;
//        double dx = speed * Math.cos(Math.toRadians(theta));
//        double dy = speed * Math.sin(Math.toRadians(theta)) * -1;
//        ball.setCenterX(ball.getCenterX() + dx);
//        ball.setCenterY(ball.getCenterY() + dy);
//    }
//
//    private boolean isHitLeft(Ball ball) {
//        return ball.getCenterX() - ball.getRadius() <= 0 ;
//    }
//
//    private boolean isHitRight(Ball ball) {
//        return ball.getCenterX() + ball.getRadius() >= Main.borderPane.getWidth() ;
//    }
//
//    private boolean isHitTop(Ball ball) {
//        return ball.getCenterY() - ball.getRadius() <= 0 ;
//    }
//
//    private boolean isHitBoard(Ball ball, Board board) {
//            return ball.getBoundsInParent().intersects(board.getBoundsInParent());
//        }
    }

