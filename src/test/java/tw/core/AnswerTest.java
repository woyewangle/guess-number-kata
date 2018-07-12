package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by jxzhong on 2017/9/23.
 */
public class AnswerTest {
    private Answer actualAnswer;

    @BeforeEach
    public void setUp() {
        actualAnswer = Answer.createAnswer("1 2 3 4");
    }

    @Test
    public void should_return_1234_when_call_creatAnswer_given() {
        Answer answer=Answer.createAnswer("1 2 3 4");
        assertThat(answer.toString(),is("1 2 3 4"));
    }

    @Test
    public void should_not_throw_Excepetion_when_given_a_right_format() {
        Answer answer=Answer.createAnswer("1 2 3 4");
        try{
            answer.validate();
        }catch (AnswerFormatIncorrectException e){
            fail("输入正确的格式，不该抛异常");
        }
    }


    @Test
    public void should_throw_Excepetion_when_num_greater_than_9() {
        Answer answer=Answer.createAnswer("1 2 3 10");
        try{
            answer.validate();

        }catch (AnswerFormatIncorrectException  e){
            return;

        }
        fail("输入的数字大于9,应该抛异常");
    }

    @Test
    public void should_throw_Excepetion_when_contains_special_chart() {
        Answer answer=Answer.createAnswer("1 2、 3 4");
        try{
            answer.validate();

        }catch (Exception e){
            return;

        }
        fail("输入错误格式,应该抛异常");
    }






}