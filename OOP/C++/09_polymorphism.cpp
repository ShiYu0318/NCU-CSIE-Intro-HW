class Shape 
{
    public:
        virtual double area() = 0;    // 純虛函式（pure virtual）
        virtual void draw() = 0;    // 讓 Shape 成為抽象類別
        virtual ~Shape() { }
};

class Circle : public Shape 
{
    private:
        double radius;
    public:
        Circle(double r) : radius(r) { }
        double area() override { return 3.14159 * radius * radius; }
        void draw() override { cout << "畫一個圓形" << endl; }
    };

class Rectangle : public Shape 
{
    private:
        double width, height;
    public:
        Rectangle(double w, double h) : width(w), height(h) { }
        double area() override { return width * height; }
        void draw() override { cout << "畫一個長方形" << endl; }
};

class Triangle : public Shape 
{
    private:
        double base, height;
    public:
        Triangle(double b, double h) : base(b), height(h) { }
        double area() override { return 0.5 * base * height; }
        void draw() override { cout << "畫一個三角形" << endl; }
};

int main() {
    // 用父類別指標裝不同子類別物件
    vector<Shape*> shapes = 
    {
        new Circle(5),
        new Rectangle(4, 6),
        new Triangle(3, 8)
    };

    // 同樣的呼叫方式，執行不同的行為
    for (Shape* s : shapes) 
    {
        s->draw();
        cout << "面積：" << s->area() << endl;
    }

    // 記得釋放記憶體
    for (Shape* s : shapes) delete s;
}

/*
畫一個圓形 面積：78.5398
畫一個長方形 面積：24
畫一個三角形 面積：12
*/