public class Java17FeaturesDemo {

    public static void main(String[] args) {
        // 1. Demonstrating Sealed Classes
        Animal animal = new Dog("Bulldog");
        describeAnimal(animal);

        // 2. Pattern Matching for instanceof
        printAnimalSound(animal);

        // 3. Record Classes
        Person person = new Person("Alice", 30);
        System.out.println("Person: " + person.name() + ", Age: " + person.age());

        // 4. Text Blocks
        String jsonExample = """
                {
                    "name": "Alice",
                    "age": 30,
                    "city": "Wonderland"
                }
                """;
        System.out.println("JSON Example: " + jsonExample);

        // 5. Switch Expressions
        String day = "MONDAY";
        String typeOfDay = switch (day) {
            case "MONDAY", "FRIDAY", "SUNDAY" -> "Start or End of the week";
            case "TUESDAY", "WEDNESDAY", "THURSDAY" -> "Midweek";
            case "SATURDAY" -> "Weekend";
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };
        System.out.println("Type of day: " + typeOfDay);
    }

    // Sealed Classes Example
    sealed interface Animal permits Dog, Cat {}

    final static class Dog implements Animal {
        private final String breed;

        public Dog(String breed) {
            this.breed = breed;
        }

        public String breed() {
            return breed;
        }
    }

    final static class Cat implements Animal {
        private final String color;

        public Cat(String color) {
            this.color = color;
        }

        public String color() {
            return color;
        }
    }

    // Sealed Class with instanceof pattern matching
    private static void describeAnimal(Animal animal) {
        if (animal instanceof Dog dog) {
            System.out.println("This is a dog of breed: " + dog.breed());
        } else if (animal instanceof Cat cat) {
            System.out.println("This is a cat of color: " + cat.color());
        } else {
            System.out.println("Unknown animal.");
        }
    }

    // Record Class Example
    record Person(String name, int age) {}

    // Pattern Matching for instanceof
    private static void printAnimalSound(Animal animal) {
        if (animal instanceof Dog dog) {
            System.out.println("The dog barks.");
        } else if (animal instanceof Cat cat) {
            System.out.println("The cat meows.");
        }
    }
}
