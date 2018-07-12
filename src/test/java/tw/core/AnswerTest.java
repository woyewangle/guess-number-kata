package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;
import tw.core.model.Record;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void should_throw_Excepetion_when_num_greater_than_9() throws Exception{
        Answer answer=Answer.createAnswer("1 2 3 10");
        try{
            answer.validate();
            fail("Answer format is incorrect");

        }catch (AnswerFormatIncorrectException  e){


        }
    }


    @Test
    public void should_throw_Excepetion_when_num_repear(){
        Answer answer=Answer.createAnswer("1 2 3 3");
        try{
            answer.validate();
            fail("Answer format is incorrect");

        }catch (AnswerFormatIncorrectException  e){


        }

    }

    @Test
    public void should_throw_Excepetion_when_contains_special_chart() {
        Answer answer=Answer.createAnswer("1 2、 3 4");
        try{
            answer.validate();
            fail("Answer format is incorrect");

        }catch (Exception e){


        }
    }


    @Test
    public void should_return_true_value_when_given_correct_input(){
        //given
        Answer answerString = mock(Answer.class);
        //涉及到answer类的其他参数
        //when
        when(answerString.getIndexOfNum("1")).thenReturn(0);
        when(answerString.getIndexOfNum("2")).thenReturn(1);
        when(answerString.getIndexOfNum("3")).thenReturn(2);
        when(answerString.getIndexOfNum("4")).thenReturn(3);
        //then
        Record record = actualAnswer.check(answerString);
        assertThat(record.getValue(), is("4A0B"));

    }








}