package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class Question {

    private String description;
    private List<String> answers;
    private String answer;

    public Question() {
    }

    public Question( String description, List<String> answers, String answer ) {
        this.description = description;
        this.answers = answers;
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAnswers() {
        return answers;
    }
    
    @JsonIgnore
    public String getAnswer() {
        return answer;
    }

}