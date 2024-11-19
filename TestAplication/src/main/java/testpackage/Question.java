package testpackage;

public class Question {
    String statement;
    String option1;
    String option2;
    String option3;
    int correctOption;

    public Question(String statement, String option1, String option2, String option3, int correctOption) {
        this.statement = statement;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctOption = correctOption;
    }

    public String getStatement() {
        return statement;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}
