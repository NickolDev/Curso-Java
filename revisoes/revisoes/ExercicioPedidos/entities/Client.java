package ExercicioPedidos.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Client {
    private static final SimpleDateFormat BIRTH_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private String name;
    private String email;
    private java.util.Date birthDate;

    public Client() {
    }

    public Client(String name, String email, java.util.Date birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.util.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) throws ParseException {
        this.birthDate = BIRTH_DATE_FORMAT.parse(birthDate);
    }

    @Override
    public String toString() {
        return name + " (" + BIRTH_DATE_FORMAT.format(birthDate) + ") - " + email;
    }
}
