import org.junit.Assert;
import org.junit.Test;

public class LengthTest {

    @Test
    public void shouldReturnTrue_whenJudgeAreSame_givenTwoLengthWithSameValueAndUnit() {
        // given
        Length lengthOne = new Length(10, LengthUnit.CM);
        Length lengthTwo = new Length(10, LengthUnit.CM);

        // when
        boolean result = lengthOne.equal(lengthTwo);

        // then
        Assert.assertTrue(result);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeException_whenCompare_givenTwoLengthWithSameValueAndDifferentUnit() {
        //given
        Length lengthOne = new Length(10, LengthUnit.CM);
        Length lengthTwo = new Length(10, LengthUnit.M);

        //when
        lengthOne.equal(lengthTwo);
    }
}
