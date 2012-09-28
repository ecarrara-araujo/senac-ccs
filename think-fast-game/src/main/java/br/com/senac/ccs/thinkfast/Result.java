package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude( JsonInclude.Include.NON_NULL )
public class Result {
    private Question question;
    private String message;
    private List<Participant> participants;

    public Result() {
    }

    public Result( Question question ) {
        this.question = question;
    }
    
    public Result( String message ) {
        this.message = message;
    }
    
    public Result( Question question, String message ) {
        this.question = question;
        this.message = message;
    }

    public Result( Question question, String message, List<Participant> participants ) {
        this.question = question;
        this.message = message;
        this.participants = participants;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion( Question question ) {
        this.question = question;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants( List<Participant> participants ) {
        this.participants = participants;
    }
    
}
