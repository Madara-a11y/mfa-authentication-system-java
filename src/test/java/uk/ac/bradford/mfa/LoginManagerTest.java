/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package uk.ac.bradford.mfa;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author maxim
 */
public class LoginManagerTest {

    // TODO add test methods here.
    @Test
    public void testStrategyAssignment() {
        LoginManager lm = new LoginManager();
        
        // Test setting TOTP
        lm.setMfaMethod(new TOTPAuthenticator());
        assertEquals("TOTP Authenticator", lm.getMfaMethod().getMethodName());
        
        // Test switching to Email
        lm.setMfaMethod(new EmailAuthenticator());
        assertEquals("SMTP Email Service", lm.getMfaMethod().getMethodName());
    }
}
