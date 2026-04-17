class Student {
    private String name;
    private int age;
    private double gpa;

    // 無參數預設建構子
    public Student() {
        name = "未知";
        age = 0;
        gpa = 0.0;
        System.out.println("建立 Student");
    }

    // 有參數的建構子
    public Student(String n, int a, double g) {
        name = n;
        age = a;
        gpa = g;
    }

    public void printInfo() {
        System.out.println(name + "，" + age + " 歲，GPA: " + gpa);
    }

    public static void main(String[] args) {
        Student s1 = new Student(); // 呼叫預設建構子
        Student s2 = new Student("A", 20, 3.9); // 呼叫有參數的建構子

        s1.printInfo(); // 未知，0 歲，GPA: 0.0
        s2.printInfo(); // A，20 歲，GPA: 3.9
    }
}