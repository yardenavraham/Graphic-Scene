import Primitives.Vector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

//this class test the functions of vector class
public class VectorTests {
    @Test
    public void testAdd(){//test of adding vector to vector
        //Example of 4 tests with vectors from different quarters
        Vector v1 = new Vector(3.5,-5,10);
        Vector v2 = new Vector(1,-5,-4);
        Vector v3=v1.add(v2);
        assertTrue(new Vector(4.5,-10,6).compareTo(v3)==0);
        
        v1 = new Vector(1,1,2);
        v2 = new Vector(-1,3,0);
        v3=v1.add(v2);
        assertTrue(new Vector(0,4,2).compareTo(v3)==0);
        
        v1 = new Vector(1,1,-2);
        v2 = new Vector(-1,3,0);
        v3=v1.add(v2);
        assertTrue(new Vector(0,4,-2).compareTo(v3)==0);
        
        v1 = new Vector(1,-1,-2);
        v2 = new Vector(-1,-3,0);
        v3=v1.add(v2);
        assertTrue(new Vector(0,-4,-2).compareTo(v3)==0);
    }
    
    @Test
    public void testSubtract(){//test of subtract vector from vector
        //Example of 4 tests with vectors from different quarters
        
        Vector v1 = new Vector(3.5,-5,10);
        Vector v2 = new Vector(1,-5,-4);
        Vector v3=v1.subtract(v2);
        assertTrue(new Vector(2.5,0,14).compareTo(v3)==0);
        
        v1 = new Vector(1,1,2);
        v2 = new Vector(-1,3,0);
        v3=v1.subtract(v2);
        assertTrue(new Vector(2,-2,2).compareTo(v3)==0);
        
        v1 = new Vector(1,1,-2);
        v2 = new Vector(-1,3,0);
        v3=v1.subtract(v2);
        assertTrue(new Vector(2,-2,-2).compareTo(v3)==0);
        
        v1 = new Vector(1,-1,-2);
        v2 = new Vector(-1,-3,0);
        v3=v1.subtract(v2);
        assertTrue(new Vector(2,2,-2).compareTo(v3)==0);
    }
    @Test
    public void testScaling(){//test of multing in scalar
        //Example of 3 tests with different scalars
        Vector v = new Vector(3.5,-5,10);
        v.scale(7);
        assertTrue(new Vector(24.5,-35,70).compareTo(v)==0);
        
        v = new Vector(3.5,-5,10);
        v.scale(-7);
        assertTrue(new Vector(-24.5,35,-70).compareTo(v)==0);
        
        v = new Vector(3.5,-5,10);
        v.scale(0);
        assertTrue(new Vector(0,0,0).compareTo(v)==0);
    }
    @Test
    public void testDotProduct(){//test of dot product
        //Example of 4 tests with vectors from different quarters
        
        Vector v1 = new Vector(3.5,-5,10);
        Vector v2 = new Vector(1,-5,-4);
        double v3=v1.dot_product(v2);
        assertEquals(-11.5,v3,1e-10);
        
        v1 = new Vector(1,1,2);
        v2 = new Vector(-1,3,0);
        v3=v1.dot_product(v2);
        assertEquals(2,v3,1e-10);
        
        v1 = new Vector(1,1,-2);
        v2 = new Vector(-1,2,0);
        v3=v1.dot_product(v2);
        assertEquals(1,v3,1e-10);
        
        v1 = new Vector(-1,-1,-2);
        v2 = new Vector(-1,-3,0);
        v3=v1.dot_product(v2);
        assertEquals(4,v3,1e-10);
    }
    @Test
    public void testLength(){//test of claculate lentgh of vector 
        Vector v = new Vector(3.5,-5,10);
        assertEquals(Math.sqrt(137.25),v.length(),1e-10);
        
        v = new Vector(0,0,0);
        assertEquals(Math.sqrt(0),v.length(),1e-10);
        
        v = new Vector(-1,1,1);
        assertEquals(Math.sqrt(3),v.length(),1e-10);
        
        v = new Vector(1,-1,-2);
        assertEquals(Math.sqrt(6),v.length(),1e-10);
    }
    @Test
    public void testNormalize() throws Exception{//test of normalize vector
        Vector v = new Vector(3.5,-5,10);
        v.normalize();
        assertEquals(1.0, v.length(),1e-10);
        v = new Vector(0,0,0);
        try {
            v.normalize();
            fail("Didn't throw divide by zero exception!");
        } 
        catch (ArithmeticException e) {
            assertTrue(true);
        }
    }
    @Test
    public void testCrossProduct(){//test of cross product
        //Example of 4 tests with vectors from different quarters
        
        Vector v1 = new Vector(3.5,-5,10);
        Vector v2 = new Vector(1,-5,-4);
        Vector v3=v1.cross_Product(v2);
        assertTrue(new Vector(70,24,-12.5).compareTo(v3)==0);
        
        v1 = new Vector(1,1,2);
        v2 = new Vector(-1,3,0);
        v3=v1.cross_Product(v2);
        assertTrue(new Vector(-6,-2,4).compareTo(v3)==0);
        
        v1 = new Vector(1,1,-2);
        v2 = new Vector(-1,3,0);
        v3=v1.cross_Product(v2);
        assertTrue(new Vector(6,2,4).compareTo(v3)==0);
        
        v1 = new Vector(1,-1,-2);
        v2 = new Vector(-1,-3,0);
        v3=v1.cross_Product(v2);
        assertTrue(new Vector(-6,2,-4).compareTo(v3)==0);
    }

}
