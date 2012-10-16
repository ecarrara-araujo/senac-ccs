package br.com.senac.ccs.thinkfast;

import org.springframework.web.context.request.async.DeferredResult;

/**
 *
 * @author ecarrara
 */
public class WebScreen implements Screen {

    private DeferredResult<Result> deferredResult;
    
    public WebScreen( DeferredResult<Result> deferredResult ) {
        this.deferredResult = deferredResult;
    }
                
    @Override
    public void show( Result result ) {
        deferredResult.setResult( result );
    }
    
    
}
