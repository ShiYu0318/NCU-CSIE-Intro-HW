class Box
{
    private:
        double width;

    public:
        Box(double w) : width(w) { }

        // 宣告 printWidth 是 Box 的朋友
        friend void printWidth(Box b);

        // 宣告另一個 class 是朋友
        friend class BoxOpener;
};

void printWidth(Box b)
{
    // 因為是 friend，可以存取 private 成員
    cout << "寬度：" << b.width << endl;
}

class BoxOpener
{
    public:
        void open(Box b)
        {
            cout << "打開箱子，寬度是：" << b.width << endl;
        }
};