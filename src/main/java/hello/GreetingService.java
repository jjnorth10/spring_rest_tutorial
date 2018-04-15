package hello;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Jordan on 15/4/2018.
 */
@Service
public class GreetingService {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private String name = "World";

    private List<Greeting> greetings = new ArrayList<>(Arrays.asList(
            new Greeting(counter.incrementAndGet(), String.format(template, this.name)),
            new Greeting(counter.incrementAndGet(), String.format(template, this.name)),
            new Greeting(counter.incrementAndGet(), String.format(template, this.name))
    ));


    public List<Greeting> greetings() {

        return this.greetings;
    }

    public Greeting getGreeting(int id){
        return greetings.stream().filter(t -> t.getId() == id).findFirst().get();

    }

    public void addGreeting(Greeting greeting){
        this.greetings.add(greeting);
    }
}
