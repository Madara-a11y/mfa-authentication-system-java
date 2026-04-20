/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uk.ac.bradford.mfa;

/**
 *
 * @author maxim
 */
public interface MFAStrategy {
    void sendCode(String destination);
    boolean verifyCode(String inputCode);
    String getMethodName();
}
