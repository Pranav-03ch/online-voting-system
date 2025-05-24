package com.example.votingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.warrenstrange.googleauth.GoogleAuthenticator;

@Controller
public class TwoFactorController {

    // For demonstration purposes we use a fixed secret.
    // In a real application, generate and store a unique secret for each user.
    private static final String SECRET = "JBSWY3DPEHPK3PXP";

    // Display the 2FA page
    @GetMapping("/2fa")
    public String showTwoFactorPage(Model model) {
        return "2fa"; // This refers to 2fa.html in your templates folder
    }

    // Verify the 2FA code submitted by the user
    @PostMapping("/2fa")
    public String verifyTwoFactor(@RequestParam("code") int code, Model model) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isCodeValid = gAuth.authorize(SECRET, code);
        if (isCodeValid) {
            // Code is correct; redirect to the home page or dashboard.
            return "redirect:/";
        } else {
            // Code is invalid; return back to the 2fa page with an error message.
            model.addAttribute("error", "Invalid code. Please try again.");
            return "2fa";
        }
    }
}
