public aspect ThinkFastGameLogger {

    pointcut participantPlay() : execution( public void br.com.senac.ccs.thinkfast.ThinkFastGame.play( String,
        String, javax.servlet.AsyncContext ));

    after() : participantPlay() {
        System.out.println( String.format( "Participant just joined in the game." ) );
    } 

}
