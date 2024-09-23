import org.example.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
public class CalcTest {
    private Main calc;
    @Before
    public void setup()
    {
        calc = new Main();
    }
    @Test
    public void test_add_pos()
    {
        double a = 1;
        double b = 2;
        double expected = 3;
        Assert.assertEquals(expected,calc.add(a,b),1e-6);
    }
    @Test
    public void test_add_neg()
    {
        double a = 1;
        double b = -2;
        double expected = -1;
        Assert.assertEquals(expected,calc.add(a,b),1e-6);
    }
    @Test
    public void test_sub_pos()
    {
        double a = 1;
        double b = 2;
        double expected = -1;
        Assert.assertEquals(expected,calc.sub(a,b),1e-6);
    }
    @Test
    public void test_sub_neg()
    {
        double a = -11;
        double b = -22;
        double expected = 11;
        Assert.assertEquals(expected,calc.sub(a,b),1e-6);
    }
    @Test
    public void test_mult_pos()
    {
        double a = 11;
        double b = 2;
        double expected = 22;
        Assert.assertEquals(expected,calc.mult(a,b),1e-6);
    }
    @Test
    public void test_mult_neg()
    {
        double a = -11;
        double b = 2;
        double expected = -22;
        Assert.assertEquals(expected,calc.mult(a,b),1e-6);
    }
    @Test
    public void test_mult_neg_neg()
    {
        double a = -11;
        double b = -2;
        double expected = 22;
        Assert.assertEquals(expected,calc.mult(a,b),1e-6);
    }
    @Test
    public void test_div_pos()
    {
        double a = 3;
        double b = 2;
        double expected = 1.5;
        Assert.assertEquals(expected,calc.div(a,b),1e-6);
    }
    @Test
    public void test_div_neg()
    {
        double a = 11;
        double b = -2;
        double expected = -5.5;
        Assert.assertEquals(expected,calc.div(a,b),1e-6);
    }
    @Test
    public void test_div_neg_neg()
    {
        double a = -11;
        double b = -0.2;
        double expected = 55;
        Assert.assertEquals(expected,calc.div(a,b),1e-6);
    }
}