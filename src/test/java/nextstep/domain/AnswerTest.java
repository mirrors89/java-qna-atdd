package nextstep.domain;

import nextstep.CannotDeleteException;
import org.junit.Test;
import support.test.BaseTest;

import static nextstep.domain.QuestionTest.CONTENTS;
import static nextstep.domain.QuestionTest.TITLE;
import static nextstep.domain.QuestionTest.newQuestion;
import static nextstep.domain.UserTest.JAVAJIGI;
import static nextstep.domain.UserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest extends BaseTest {
    public static final String ANSWER_CONTENTS = "테스트 답변 컨텐츠";

    public static Answer newAnswer(String contents) {
        return new Answer(JAVAJIGI, contents);
    }

    @Test
    public void create() {
        Question question = newQuestion(TITLE, CONTENTS, JAVAJIGI);
        Answer answer = newAnswer(ANSWER_CONTENTS);
        question.addAnswer(answer);

        assertThat(question.getAnswers().size()).isEqualTo(1);
        assertThat(question.getAnswers().get(0)).isEqualTo(answer);
    }

    @Test
    public void delete_writer() throws CannotDeleteException {
        Answer answer = newAnswer(ANSWER_CONTENTS);

        answer.delete(JAVAJIGI);

        assertThat(answer.isDeleted()).isEqualTo(true);
    }


    @Test(expected = CannotDeleteException.class)
    public void delete_not_writer() throws CannotDeleteException {
        Answer answer = newAnswer(ANSWER_CONTENTS);

        answer.delete(SANJIGI);
    }
}
