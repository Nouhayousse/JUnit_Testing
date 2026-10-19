package TestingLab;

/*
Un mot de passe est valide s'il respecte toutes ces règles :

Au moins 8 caractères
Au moins une majuscule
Au moins un chiffre
Au moins un caractère spécial parmi !@#$%^&*()-_=+[]{};:,.<>?/
Aucun espace
null → invalide

*/


import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
    private static final String SPECIAL_cHARS="!@#$%^&*()_-+=[]{};,.<>?/|";

    public boolean isValid(String password) {
        if (password == null)
            return false;

        return password.length() >= 8 && password.chars().anyMatch(Character::isUpperCase)
                && password.chars().anyMatch(Character::isDigit)
                && password.chars().anyMatch(c -> SPECIAL_cHARS.indexOf(c) >= 0)
                && password.chars().noneMatch(Character::isWhitespace);
    }


    public List<String> validate(String password){
        List<String> violations=new ArrayList<>();
        if(password==null){
            violations.add("password can't be null");
            return violations;
        }
        if(password.length()<8){
            violations.add("password size can't be less than 8");
        }
        if (password.chars().noneMatch(Character::isUpperCase))
            violations.add("password must contain an UpperCase");

        if(password.chars().noneMatch(Character::isDigit))
            violations.add("password must contain a number");
        if(password.chars().noneMatch(c->SPECIAL_cHARS.indexOf(c)>=0))
            violations.add("password must contain a special char");
        if (password.chars().anyMatch(Character::isWhitespace))
            violations.add("password can't be empty");

        return violations;
    }

}
