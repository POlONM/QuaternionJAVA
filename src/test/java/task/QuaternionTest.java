package task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuaternionTest {

    Quaternion quaternion;

    @Test
    public void createEmpty(){
        quaternion = new Quaternion();
        assertEquals("0.0 + 0.0 * i + 0.0 * j + 0.0 * k", quaternion.toString());
    }

    @Test
    public void getSopr() throws Exception {
        quaternion = new Quaternion(1, 2, 3, 4);
       assertEquals(new Quaternion(1, -2, -3, -4), quaternion.sopr());
    }

    @Test
    public void getAbs() throws Exception {
        quaternion = new Quaternion(1, 2, -2, -4);
        assertEquals("5.0",quaternion.abs());
    }

    @Test
    public void getNorm() throws Exception {
        quaternion = new Quaternion(1, 2, 3, 4);
        assertEquals(new Quaternion(1, 4, 9, 16), quaternion.norm());
    }

    @Test
    public void getSum() throws Exception {
        assertEquals(new Quaternion(5, 5, 5, 5),
                Quaternion.sum(new Quaternion(3, -1, 4, 2), new Quaternion(2, 6, 1, 3)));
    }

    @Test
    public void getAntiSum() throws Exception {
        assertEquals(new Quaternion(1, -11, 2, 7),
                Quaternion.antiSum(new Quaternion(2, -5, 6, 4), new Quaternion(1, 6, 4, -3)));
    }

    @Test
    public void getMultNum() throws Exception {
        assertEquals(new Quaternion(4, -10, 12, 8),
                Quaternion.multNum(new Quaternion(2, -5, 6, 4), 2));
    }

    @Test
    public void getMult() throws Exception {
        assertEquals(new Quaternion(1, 1, 2, 7),
                Quaternion.mult(new Quaternion(2, -3, 1, 3), new Quaternion(1, 4, 6, -3)));
    }

    @Test
    public void getParts() throws Exception {
        quaternion = new Quaternion(1, 2, 3, 4);
        assertEquals("u = 1.0, v = 2.0 * i + 3.0 * j + 4.0 * k", quaternion.getParts());
    }

    Vector vector;
    Double angle;

    @Test
    public void getQuaternion() throws Exception {
         vector = new Vector(1, 0, 0);
         angle = 0.0;
         assertEquals("1.0 + 0.0 * i + 0.0 * j + 0.0 * k",
                 Quaternion.getQuaternion(vector, angle));
    }

}