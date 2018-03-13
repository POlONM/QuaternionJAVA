package task;

public class Vector {
    double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector normalize() {
        return new Vector(x / length(), y / length(), z / length());
    }

    @Override
    public String toString() {
        return "(" + ((x < 0) ? "- " : "") + Math.abs(x) + ", " + ((y < 0) ? "- " : "") +
                Math.abs(y) + ", " + ((z < 0) ? "- " : "") + Math.abs(z) + ")";
    }
}
