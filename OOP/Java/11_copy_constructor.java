class MyArray {
    private int[] data;
    private int size;

    public MyArray(int size) {
        this.size = size;
        this.data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    public MyArray(MyArray other) {
        this.size = other.size;
        this.data = new int[size];
        System.arraycopy(other.data, 0, this.data, 0, size);
        System.out.println("複製建構子執行");
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + (i == size - 1 ? "\n" : " "));
        }
    }

    public static void main(String[] args) {
        MyArray arr1 = new MyArray(5);
        MyArray arr2 = new MyArray(arr1);
        arr2.print();
    }
}