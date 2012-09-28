package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.spi.activation.Repository;
import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;

public class Participant {

    private String id;
    private String name;
    private int score;
    private AsyncContext asyncContext;

    public Participant() {
        this.score = 0;
    }

    public Participant( String id, String name ) {
        this();
        this.id = id;
        this.name = name;
    }

    public Participant( String id, String name, AsyncContext asyncContext ) {
        this( id, name );
        this.asyncContext = asyncContext;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    public void incrementScore() {
        this.score++;
    }

    public void setAsyncContext( AsyncContext asyncContext ) {
        this.asyncContext = asyncContext;
    }

    private static final ObjectMapper mapper = new ObjectMapper();
    public void notify( Result result ) throws IOException {
        if( asyncContext != null ) {
            HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
            response.setContentType( "application/json" );
            response.getWriter().write( mapper.writeValueAsString( result ) );
            response.flushBuffer();
            asyncContext.complete();
            asyncContext = null;
        }
    }
}
