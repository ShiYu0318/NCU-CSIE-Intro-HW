class Student {
    private String name;
    private int[] grades; // 動態配置的陣列

    public Student(String n, int numGrades) {
        this.name = n;
        this.grades = new int[numGrades]; // heap 配置
        System.out.println(name + " 建立");
    }

    protected void finalize() throws Throwable {
        System.out.println(name + " 銷毀");
        super.finalize();
    }

    public static void main(String[] args) {
        Student s = new Student("A", 5); // 建立，印出「A 建立」
        s = null; // 讓垃圾回收機制處理
        System.gc(); // 強制呼叫垃圾回收
    }
}