package com.example.gtw_101.utilities;

public class Validation {

    /**
     * Create method checkNullData() to check whether the data are null
     *
     * @param strings storing all the strings need to be checked
     * @return the null status
     */
    public static boolean checkNullData(String[] strings){
        for (String s: strings){
            if (s.isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * Create method checkNameFormat() to check the name format
     *
     * Vietnamese, no others languages, no emojis
     *
     * @param name storing name
     * @return the status of checking format
     */
    public static boolean checkNameFormat(String name){
        String specialCharacters = "~`!@#$%^&*()-_=+[{]}\\|;:'\"<>,./?*";
        for (int i = 0; i < name.length(); i++){
            if (specialCharacters.indexOf(name.charAt(i)) != -1 || (name.charAt(i) >= 48 && name.charAt(i) <= 57)){
                return false;
            }
        }
        return true;
    }

    /**
     * Create method checkUsernameFormat() to check the username format
     *
     * @param username storing the username
     * @return the status of checking format
     */
    public static boolean checkUsernameFormat(String username){
        if (username.length() < 6){
            return false;
        }

        for (int i = 0; i < username.length(); i++){
            if (username.charAt(i) == 32){
                return false;
            }
        }
        return true;
    }

    /**
     * Create method checkPasswordFormat() to check the password format
     *
     * @param password storing the password
     * @return the status of checking format
     */
    public static boolean checkPasswordFormat(String password){
        if (password.length() < 6){
            return false;
        }
        boolean checkLetter = false;
        boolean checkNumber = false;

        for (int i = 0; i < password.length(); i++){
            if ((password.charAt(i) >= 65 && password.charAt(i) <= 90) || (password.charAt(i) >= 97 && password.charAt(i) <= 122)){
                checkLetter = true;
            }
            if (password.charAt(i) >= 48 && password.charAt(i) <= 57){
                checkNumber = true;
            }
        }
        return checkLetter && checkNumber;
    }

    /**
     * Create method checkConfirmPassword() to check the similarity of password and confirm password
     *
     * @param password storing the password
     * @param confirmPassword storing the confirm password
     * @return the status of checking password and confirm password
     */
    public static boolean checkConfirmPassword(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

    /**
     * Create method checkRegisterFormat() to check all fields when registering new account
     *
     * @param name storing the name
     * @param username storing the username
     * @param password storing the password
     * @param confirmPassword storing the confirm password
     *
     * @return the status of checking format
     */
    public static boolean checkRegisterFormat(String name, String username, String password, String confirmPassword){
        return checkNameFormat(name) && checkUsernameFormat(username) && checkPasswordFormat(password) && checkConfirmPassword(password, confirmPassword);
    }



}
