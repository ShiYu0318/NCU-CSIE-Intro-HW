class Box {
    private double width;

    public Box(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }
}

class BoxOpener {
    public void open(Box b) {
        System.out.println("打開箱子，寬度是：" + b.getWidth());
    }

    public static void main(String[] args) {
        Box box = new Box(10);
        BoxOpener opener = new BoxOpener();
        opener.open(box);
    }
}