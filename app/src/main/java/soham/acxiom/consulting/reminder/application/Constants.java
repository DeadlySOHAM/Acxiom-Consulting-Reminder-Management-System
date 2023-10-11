package soham.acxiom.consulting.reminder.application;

import java.util.ArrayList;

public class Constants {

    public static final String mailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            mailDomain = "@acxiomconsulting.com";

    public static final ArrayList<String> subjects = new ArrayList<String>(){{
        add("Subject 1");
        add("Subject 2");
        add("Subject 3");
        add("Subject 4");
        add("Subject 5");
    }};
}
