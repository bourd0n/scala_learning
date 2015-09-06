package alex.scala.scalable.api;

public class Employee {
    private final String name;
    private final Long id;

    public Employee(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
