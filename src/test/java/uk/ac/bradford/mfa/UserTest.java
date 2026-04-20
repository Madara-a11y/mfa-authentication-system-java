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
public class UserTest {
    // TODO add test methods here.
    @Test
    public void testUserCreation() {
        User u = new User("max", "pass123", "max@bradford.ac.uk");
        assertEquals("max", u.getUsername());
        assertEquals("max@bradford.ac.uk", u.getEmail());
    }

    @Test
    public void testPasswordVerification() {
        User u = new User("max", "pass123", "max@bradford.ac.uk");
        assertTrue(u.checkPassword("pass123"), "Should return true for correct password");
        assertFalse(u.checkPassword("wrong"), "Should return false for incorrect password");
    }
}
