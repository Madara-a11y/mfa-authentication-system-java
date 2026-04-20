/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.bradford.mfa;

/**
 *
 * @author maxim
 */

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public class TOTPAuthenticator implements MFAStrategy {
    // This is the Base32 Secret Key
    private final String sharedSecret = "JBSWY3DPEHPK3PXP"; 

    @Override
    public void sendCode(String destination) {
        System.out.println("[MFA] Check your Microsoft Authenticator App.");
    }

    @Override
    public boolean verifyCode(String inputCode) {
        try {
            long currentTime = System.currentTimeMillis() / 1000 / 30;
            
            // Look-ahead/Look-back window (fixes clock sync issues)
            for (int i = -1; i <= 1; i++) {
                if (generateTOTP(sharedSecret, currentTime + i).equals(inputCode)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error calculating TOTP: " + e.getMessage());
        }
        return false;
    }

    private String generateTOTP(String secret, long interval) throws Exception {
        // Simple Base32 to Byte conversion for the "JBSWY3DPEHPK3PXP" string
        byte[] key = {0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x21, (byte)0xde, (byte)0xad, (byte)0xbe, (byte)0xef}; 
        // Note: For a university prototype, we use a fixed byte array matching that secret
        
        byte[] data = ByteBuffer.allocate(8).putLong(interval).array();
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(key, "HmacSHA1"));
        
        byte[] hash = mac.doFinal(data);
        int offset = hash[hash.length - 1] & 0xF;
        int truncatedHash = ((hash[offset] & 0x7f) << 24 | (hash[offset + 1] & 0xff) << 16 | (hash[offset + 2] & 0xff) << 8 | (hash[offset + 3] & 0xff));
        
        return String.format("%06d", truncatedHash % 1000000);
    }

    @Override
    public String getMethodName() { return "TOTP Authenticator"; }
}