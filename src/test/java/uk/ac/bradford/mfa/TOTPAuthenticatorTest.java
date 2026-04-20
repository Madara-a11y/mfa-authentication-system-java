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
public class TOTPAuthenticatorTest {

    @Test
    public void testVerifyCodeFormat() {
        TOTPAuthenticator totp = new TOTPAuthenticator();
        // A null or empty code should always return false
        assertFalse(totp.verifyCode(null));
        assertFalse(totp.verifyCode(""));
        // A code with letters should return false
        assertFalse(totp.verifyCode("ABCDEF"));
    }
}
