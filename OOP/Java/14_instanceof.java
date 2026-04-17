Animal a = new Dog("小黑", 3, "拉布拉多");

// 檢查型別
System.out.println(a instanceof Animal);  // true
System.out.println(a instanceof Dog);     // true
System.out.println(a instanceof Cat);     // false

// 實際應用：根據型別做不同處理
Animal[] animals = {
    new Dog("小黑", 3, "拉布拉多"),
    new Cat("咪咪", 2),
    new Dog("小白", 1, "柴犬")
};

for (Animal a : animals) {
    if (a instanceof Dog dog) {       // Java 16+ 寫法
        dog.fetch();                  // 自動轉型，直接用
    } else if (a instanceof Cat cat) {
        System.out.println(cat.name + " 在撒嬌");
    }
}