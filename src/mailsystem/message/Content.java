/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.message;

/**
 *
 * @author Carla
 */
public class Content {
    private String contenido;
    
    public Content(String contenido){
        this.contenido = contenido;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Content other = (Content) obj;
        return contenido.equals(other.contenido);
    }
      

    
}
