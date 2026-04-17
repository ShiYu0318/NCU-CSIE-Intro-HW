// 1. final 變數：常數，不能再被賦值
final double PI = 3.14159;
PI = 3.0;   // 錯誤！

// 2. final 方法：不能被子類別覆寫
class Animal {
    public final void breathe() {
        System.out.println("呼吸中");
    }
}

class Dog extends Animal {
    @Override
    public void breathe() { }   // 錯誤！final 不能覆寫
}

// 3. final 類別：不能被繼承
final class String { }    // Java 的 String 就是 final class

class MyString extends String { }   // 錯誤！不能繼承 final class