package zh.learn.spring5.reactiveexamples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;

    public String sayMyName() {
        return "My name is " + firstName + " " + lastName + ".";
    }
}
