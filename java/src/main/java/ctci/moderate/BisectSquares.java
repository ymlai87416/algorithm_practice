package ctci.moderate;

public class BisectSquares {

    public Line bisectSquare(double[][] squares){
        double[][] center = new double[2][2];

        for(int i=0; i<2; ++i){
            center[i][0] = (squares[i][0] + squares[i][2]) /2;
            center[i][1] = (squares[i][1] + squares[i][3]) /2;
        }

        //how to define a line by passing 2 points?
        double a = center[0][1] - center[1][1];
        double b = center[1][0] - center[0][0];
        double c = center[0][0] * (center[0][1]-center[1][1])
                - center[0][1]* (center[0][0]-center[1][0]);

        return new Line(a, b, -c);
    }


    public static void main(String[] args) {

    }
}

class Line{
    double a;
    double b;
    double c;
    public Line(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
