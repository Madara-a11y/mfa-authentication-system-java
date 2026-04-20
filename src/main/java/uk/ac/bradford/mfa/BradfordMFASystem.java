package uk.ac.bradford.mfa;

import java.util.Scanner;
import uk.ac.bradford.mfa.EmailAuthenticator;
import uk.ac.bradford.mfa.LoginManager;
import uk.ac.bradford.mfa.TOTPAuthenticator;
import uk.ac.bradford.mfa.User;

public class BradfordMFASystem {

    // This is the EXACT line NetBeans is looking for
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
        new uk.ac.bradford.mfa.gui.LoginGui().setVisible(true);
    });
        Scanner scanner = new Scanner(System.in);
        LoginManager loginManager = new LoginManager();

        // Hard-coded user for the University prototype
        User testUser = new User("student123", "Bradford123", "student@bradford.ac.uk");

        System.out.println("--- University of Bradford MFA Portal ---");
        System.out.print("Enter Username: ");
        String inputUser = scanner.nextLine();
        System.out.print("Enter Password: ");
        String inputPass = scanner.nextLine();

        // 1. Password Validation
        if (inputUser.equals(testUser.getUsername()) && inputPass.equals(testUser.getPassword())) {
            System.out.println("\n[AUTH] Password correct.");
            System.out.println("Select your preferred MFA method:");
            System.out.println("1. Email");
            System.out.println("2. Authenticator App (TOTP)");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            // 2. Set Strategy (Demonstrates Open/Closed Principle)
            if (choice == 1) {
                loginManager.setMfaMethod(new EmailAuthenticator());
            } else {
                loginManager.setMfaMethod(new TOTPAuthenticator());
            }

            // 3. Process MFA
            loginManager.getMfaMethod().sendCode(testUser.getEmail());
            System.out.print("Enter 6-digit code: ");
            String inputCode = scanner.nextLine();

            if (loginManager.verifyMFA(inputCode)) {
                System.out.println("\nSUCCESS: Access Granted to Bradford Systems.");
            } else {
                System.out.println("\nFAILURE: Invalid Code.");
            }
        } else {
            System.out.println("FAILURE: Invalid Credentials.");
        }
        scanner.close();
    }
}