package models.dao;

/**
 * Created by jordan on 20/04/2016.
 */
public class Email {


    int idEmail;
    String email;
    int DonoEmail;

    public Email(String email, int idDono){
        setEmail(email);
        setDonoEmail(idDono);
    };

    public int getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(int idEmail) {
        this.idEmail = idEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+ (\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Boolean validadeEmail = email.matches(EMAIL_REGEX);
        if(validadeEmail){this.email = email;}
        else {throw new IllegalArgumentException("Email Invalido");}

    }

    public int getDonoEmail() {
        return DonoEmail;
    }

    public void setDonoEmail(int donoEmail) {
        DonoEmail = donoEmail;
    }
}
