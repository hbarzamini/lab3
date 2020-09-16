import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Flower implements GardenComponent
{
	Circle circle;
	
	public Flower(Point2D flowerLocation,Color color,Boolean movable)
	{
		circle = new Circle();
		circle.setCenterX( flowerLocation.getX());
		circle.setCenterY( flowerLocation.getY());
		circle.setRadius(10);
		circle.setFill(color);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(1);
	}
	
	public Circle getCircle()
	{
		return circle;
	}
	
	@Override
	public void move(double x, double y)
	{
		circle.setCenterX(circle.getCenterX()+x);
		circle.setCenterY(circle.getCenterY()+y);
	}
	
	@Override
	public boolean ContainsPoint(Point2D point) {
		return (circle.contains(point));				
	}
}
