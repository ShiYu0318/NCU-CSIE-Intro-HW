class Student {
    private String name;
    private static int totalCount = 0;

    public Student(String name) {
        this.name = name;
        totalCount++;
        System.out.println(name + " 建立，目前共 " + totalCount + " 個");
    }

    public static int getCount() {
        return totalCount;
    }

    public static void main(String[] args) {
        Student s1 = new Student("A");
        Student s2 = new Student("B");
        Student s3 = new Student("C");

        System.out.println(Student.getCount());
    }
}