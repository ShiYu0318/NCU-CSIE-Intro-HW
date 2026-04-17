class Vector2D 
{
    public:
        double x, y;

        Vector2D(double x, double y) : x(x), y(y) { }

        // 重載 + 運算子
        Vector2D operator+(const Vector2D& other) const 
        {
            return Vector2D(x + other.x, y + other.y);
        }

        // 重載 == 運算子
        bool operator==(const Vector2D& other) const 
        {
            return x == other.x && y == other.y;
        }

        // 重載 * 運算子（純量乘法）
        Vector2D operator*(double scalar) const 
        {
            return Vector2D(x * scalar, y * scalar);
        }

        // 重載 << 運算子（輸出）
        friend ostream& operator<<(ostream& os, const Vector2D& v) 
        {
            os << "(" << v.x << ", " << v.y << ")";
            return os;
        }
};

int main() 
{
    Vector2D v1(1, 2);
    Vector2D v2(3, 4);

    Vector2D v3 = v1 + v2;    // (4, 6)
    Vector2D v4 = v1 * 3;    // (3, 6)
    cout << v3 << endl;    // (4, 6)
    cout << (v1 == v2) << endl;    // 0（false）
}