package ctci.moderate;

public class BisectSquares {

    public Line bisectSquare(Square sq1, Square sq2){
        Point c1 = new Point( (sq1.left + sq1.right) / 2, (sq1.top + sq1.bottom)/2 );
        Point c2 = new Point( (sq2.left + sq2.right) / 2, (sq2.top + sq2.bottom)/2 );

        return Line.createLineFromTwoPoint(c1, c2);
    }


    public static void main(String[] args) {
        Square s1 = new Square(5, 0, 5, 0);
        Square s2 = new Square(10, 5, 6, 9);

        BisectSquares test = new BisectSquares();
        Line line = test.bisectSquare(s1, s2);

        System.out.printf("%.7fx + %.7fy + %.7f = 0", line.a, line.b, line.c);
    }
}
