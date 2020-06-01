class Shape {

    private final int x;
    private final int y;
    private final int id;

    Shape(int x, int y, int id)
    {
        this.x=x;
        this.y=y;
        this.id=id;
    }

    int getX()
    {
        return this.x;
    }

    int getY()
    {
        return this.y;
    }

    String getId()
    {
        return String.valueOf(this.id);
    }
}
