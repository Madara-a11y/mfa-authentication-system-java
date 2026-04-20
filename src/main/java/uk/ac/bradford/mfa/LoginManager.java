/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.bradford.mfa;

import uk.ac.bradford.mfa.MFAStrategy;

/**
 *
 * @author maxim
 */
public class LoginManager {
    private MFAStrategy mfaMethod;

    public void setMfaMethod(MFAStrategy mfaMethod) {
        this.mfaMethod = mfaMethod;
    }

    public MFAStrategy getMfaMethod() {
        return mfaMethod;
    }

    public boolean verifyMFA(String inputCode) {
        if (mfaMethod == null) return false;
        return mfaMethod.verifyCode(inputCode);
    }
    
    public boolean authenticate(String username, String password) {
    // In a real system, you'd check a database. 
    // For your prototype, we will use a hardcoded check.
    return username.equals("admin") && password.equals("password123");
}
}