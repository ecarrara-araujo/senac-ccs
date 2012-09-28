package br.com.senac.ccs.thinkfast;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet( urlPatterns = { "/thinkfast" },
             asyncSupported = true, loadOnStartup=1 )
public class ThinkFastController extends HttpServlet {
    
    private ThinkFastGame game;

    @Override
    public void init( ServletConfig config ) throws ServletException {
        super.init( config );
        this.game = new ThinkFastGame();
        game.init();
    }
    
    @Override
    protected void doGet( HttpServletRequest req,
                          HttpServletResponse resp )
            throws ServletException, IOException {      
        
        String action = req.getParameter( "action" );
        String id = req.getSession(true).getId();
        
        if( "play".equals( action ) ) {
            String name = req.getParameter( "name" );
            AsyncContext ctx = req.startAsync();
            game.play( id, name, ctx);
        } else if( "answer".equals( action ) ) {
            String answer = req.getParameter( "answer" );
            game.answer( id, answer );
        } else if( "bind".equals( action ) ) {
            AsyncContext ctx = req.startAsync();
            game.bind( id, ctx ); 
        } 
        
        
    }
}