class Student {
    public String name;
    public int age;

    public Student() {
        this.name = "未知";
        this.age = 0;
    }

    public Student(String n) {
        this.name = n;
        this.age = 0;
    }

    public Student(String n, int a) {
        this.name = n;
        this.age = a;
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student("A");
        Student s3 = new Student("B", 21);
    }
}