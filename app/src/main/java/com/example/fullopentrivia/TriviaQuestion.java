package com.example.fullopentrivia;

import java.util.List;

public class TriviaQuestion {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    public void setCategory(String cat){ category = cat; }
    public String getCategory() {
        return category;
    }

    public void setType(String t) { type = t; }
    public String getType () {
        return type;
    }

    public void setDifficulty (String dif) { difficulty = dif; }
    public String getDifficulty() { return difficulty; }

    public void setQuestion (String ques) { question = ques; }
    public String getQuestion() {
        return question;
    }

    public void setCorrect_answer (String corr_ans) { correct_answer = corr_ans; }
    public String getCorrectAnswer() { return correct_answer; }

    public void setIncorrect_answers(List<String> in_ans) { incorrect_answers = in_ans; }
    public List<String> getIncorrectAnswers() {
        return incorrect_answers;
    }

    public String toString() {
        String out = "Category: " + category;
        out = out + "\nType: " + type;
        out = out + "\nDifficulty: " + difficulty;
        out = out + "\nQuestion: " + question;
        out = out + "\nCorrect_Answer: " + correct_answer;
        out = out + "\nIncorrect_Answers: " + incorrect_answers.toString();
        return out;
    }
}
