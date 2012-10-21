package br.com.senac.ccs.thinkfast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThinkFastGame {
    
    private final ConcurrentHashMap<String, Participant> participants;
    private final Lock lock;
    private final List<Question> questions;
    private Question currentQuestion;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    public ThinkFastGame() {
        this.participants = new ConcurrentHashMap<String, Participant>();
        this.questions = new ArrayList<Question>();
        this.lock = new ReentrantLock();
    }
    
    public Result play( String id, String name, Screen screen ) {
        lock.lock();
        Result result = null;
        try {
            Participant participant = new Participant( id, name, screen );
            participants.put( id, participant );
            result = new Result( currentQuestion, "Welcome! :)", 
                    new ArrayList<Participant>( participants.values() ));
        } finally {
            lock.unlock();
        }
        return result;
    }
    
    public void bind( String id, Screen screen ) {
        Participant participant = participants.get( id );
        participant.setScreen( screen );
    }
    
    public Result answer( String id, Answer answer) {
        lock.lock();
        Result result = null;
        try {
            if ( currentQuestion.getAnswer().equals( answer ) ) {
                Question question = currentQuestion;
                questions.remove( question );
                Collections.shuffle( questions );
                currentQuestion = questions.get( 0 );
                questions.add( question );
                final List<Participant> all = new ArrayList<Participant>( participants.values() );
                Participant winner = participants.remove( id );
                winner.incrementScore();
                winner.notify( new Result( currentQuestion, "Congratulation!", all ) );
                for ( Participant participant : participants.values() ) {
                    participant.notify( new Result ( currentQuestion, 
                            String.format( "O participante %s respondeu mais rapido.", winner.getName() ), all ) );
                }
                participants.put( id, winner );
            } else {
                result = new Result( "Nope!!! :(" );
            }
        } finally {
            lock.unlock();
        }
        return result;
    }
    
    @PostConstruct
    public void init() {
        
        Answer correct = new Answer( "Washington DC" );
        questionRepository.save( new Question( "Qual a capital dos EUA?", 
                                 Arrays.asList( new Answer[]{ correct, 
                                                              new Answer( "California"), 
                                                              new Answer("Nevada" ) } ), 
                                 correct ) );
        correct = new Answer( "Moscou" );
        questionRepository.save( new Question( "Qual a capital da Russia?", 
                                 Arrays.asList( new Answer[]{ new Answer( "Berlin" ), 
                                                              new Answer( "Paris" ), 
                                                              correct } ), 
                                 correct ) );
        this.questions.addAll( questionRepository.findAll() );
        this.currentQuestion = questions.get( 0 );
    }
}
