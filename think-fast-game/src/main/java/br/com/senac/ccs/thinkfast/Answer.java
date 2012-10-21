package br.com.senac.ccs.thinkfast;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ecarrara
 */
@Entity
public class Answer {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    
    private String description;
 
    public Answer() {
    }

    public Answer( String description ) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription( String description ) {
        this.description = description;
    }
    
    @Override
    public boolean equals( Object o ) {
        return super.equals( o ) || 
                (this.getClass().isInstance( o ) 
                && this.hashCode() == o.hashCode() );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode( this.id );
        return hash;
    }
}
