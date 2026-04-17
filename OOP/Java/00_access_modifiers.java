class Student {
    // 存取修飾字
    private String name;
    private int age;
    private double gpa;

    public void setName(String n) { name = n; }
    public void setAge(int a) { age = a; }
    public String getName() { return name; }

    protected int studentId; // 只有自己和子類別能存取（繼承時用到）
}