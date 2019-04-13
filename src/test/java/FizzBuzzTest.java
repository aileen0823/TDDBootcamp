import lesson1.FizzBuzz;
import org.junit.Assert;
import org.junit.Test;

public class FizzBuzzTest {

    @Test
    public void should_return_fizz_when_calculate_given_number_3(){
        int number = 3;

        String result = FizzBuzz.printFizzBuzz(number);

        Assert.assertEquals(result, "fizz");
    }


}
