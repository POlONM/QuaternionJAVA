package task;

public class Quaternion {
    private double a;
    private double b;
    private double c;
    private double d;

    public Quaternion() {
        a = b = c = d = 0;
    }

    public Quaternion(double a) {
        this.a = a;
        b = c = d = 0;
    }

    public Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public boolean isZero() {
        return (a == 0 && b == 0 && c == 0 && d == 0);
    }

    public Quaternion sopr() {
        return new Quaternion(a, -b, -c, -d);
    }

    public double abs() {
        return Math.sqrt(a * a + b * b + c * c + d * d);
    }

    public Quaternion norm() {
        return new Quaternion(a * a, b * b, c * c, d * d);
    }

    public static Quaternion sum(Quaternion a, Quaternion b) {
        return new Quaternion(a.a + b.a, a.b + b.b, a.c + b.c, a.d + b.d);
    }

    public static Quaternion antiSum(Quaternion a, Quaternion b) {
        return new Quaternion(a.a - b.a, a.b - b.b, a.c - b.c, a.d - b.d);
    }

    public static Quaternion multNum(Quaternion a, double num) {
        return new Quaternion(a.a * num, a.b * num, a.c * num, a.d * num);
    }

    public static Quaternion mult(Quaternion a, Quaternion b) {
        return new Quaternion(a.a * b.a - a.b * b.b - a.c * b.c - a.d * b.d,
                a.a * b.b + a.b * b.a + a.c * b.d - a.d * b.c,
                a.a * b.c - a.b * b.d + a.c * b.a + a.d * b.b,
                a.a * b.d + a.b * b.c - a.c * b.b + a.d * b.a);
    }

    public static Quaternion divideNum(Quaternion a, double num) {
        if (num == 0) throw new ArithmeticException("Division by zero");
        return new Quaternion(a.a / num, a.b / num, a.c / num, a.d / num);
    }

    public String getParts() {
        return "u = " + a + ", " + "v = " + b + " * i + " + c + " * j + " + d + " * k";
    }


    public static Quaternion getQuaternion(Vector vector, double angle) {
        Vector unitVector = vector.normalize();
        Quaternion qu = new Quaternion();
        qu.a = Math.cos(angle / 2);
        qu.b = unitVector.getX() * Math.sin(angle / 2);
        qu.c = unitVector.getY() * Math.sin(angle / 2);
        qu.d = unitVector.getZ() * Math.sin(angle / 2);
        return qu;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quaternion that = (Quaternion) o;

        if (Double.compare(that.a, a) != 0) return false;
        if (Double.compare(that.b, b) != 0) return false;
        if (Double.compare(that.c, c) != 0) return false;
        return Double.compare(that.d, d) == 0;
    }

    @Override
    public int hashCode() {
        int result;

        long temp;
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(d);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        if (b < 0)
            return (a + " - " + b * (-1) + " * i + " + c + " * j + " + d + " * k");
        else if (c < 0)
            return (a + " + " + b + " * i - " + c * (-1) + " * j + " + d + " * k");
        else if (d < 0)
            return (a + " + " + b + " * i + " + c + " * j - " + d * (-1) + " * k");
        else if (b < 0 && c < 0)
            return (a + " - " + b * (-1) + " * i - " + c * (-1) + " * j + " + d + " * k");
        else if (b < 0 && d < 0)
            return (a + " - " + b * (-1) + " * i + " + c + " * j - " + d * (-1) + " * k");
        else if (c < 0 && d < 0)
            return (a + " + " + b + " * i - " + c * (-1) + " * j - " + d * (-1) + " * k");
        else if (b < 0 && c < 0 && d < 0)
            return (a + " - " + b * (-1) + " * i - " + c * (-1) + " * j - " + d * (-1) + " * k");
        else
            return (a + " + " + b + " * i + " + c + " * j + " + d + " * k");
    }
}
