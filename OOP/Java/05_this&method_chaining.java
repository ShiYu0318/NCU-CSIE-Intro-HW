class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }

    public void print() {
        System.out.println(name + " " + age);
    }

    public static void main(String[] args) {
        Student s = new Student("", 0);
        s.setName("A").setAge(19).print();
    }
}