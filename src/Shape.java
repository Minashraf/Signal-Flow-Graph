public class Shape {

    private int x;
    private int y;
    private int id;

    public Shape(int x,int y,int id)
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
