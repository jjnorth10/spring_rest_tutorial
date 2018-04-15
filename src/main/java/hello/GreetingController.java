package hello;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingService greetingService;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/greetings")
    public List<Greeting> greetings(@RequestParam(value="name", defaultValue="World") String name) {
        List<Greeting> greetings = this.greetingService.greetings();
        return greetings;
    }

    @RequestMapping("/greeting/{id}")
    public Greeting greeting(@PathVariable int id){
        return this.greetingService.getGreeting(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/greeting")
    public void addGreeting(@RequestBody Greeting greeting) {
        this.greetingService.addGreeting(greeting);
    }
}
