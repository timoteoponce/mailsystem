/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsystem.server;

import mailsystem.User;

/**
 *
 * @author timoteo
 */
public interface AuthenticationServer {

    void authenticate(User user) throws NotAuthorizedException;
}
