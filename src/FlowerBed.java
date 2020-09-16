import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FlowerBed implements GardenComponent
{
	Point2D topLeft;
	int height;
	int width;
	Color rectanguleColor;
	Rectangle rect = new Rectangle();
	List<GardenComponent> myComponent = new ArrayList<GardenComponent>();	
	
	public FlowerBed(Point2D topLeft,int height, int width)
	{
		this.topLeft = topLeft;
		this.height = height;
		this.width = width;
		rect.setHeight(height);
		rect.setWidth(width);
		rect.setX(topLeft.getX());
		rect.setY(topLeft.getY());
		rect.setStroke(Color.BLACK);
		rect.setFill(Color.DARKGOLDENROD);
	}
	
	public Rectangle getRectangle()
	{
		return rect;
	}
	
	public void addChild(GardenComponent component)
	{
		myComponent.add(component);
	}
	
	public void removeChild(GardenComponent component)
	{
		if(myComponent.contains(component))
				myComponent.remove(component);
	}
	
	public GardenComponent getChild(int index)
	{
		return myComponent.get(index);
	}
	
	@Override
	public void move(double dx, double dy)
	{
		rect.setX(rect.getX()+dx);
		rect.setY(rect.getY()+dy);
		
		for(GardenComponent child:myComponent)
		{
			child.move(dx,dy);
		}
	}
	
	@Override
	public boolean ContainsPoint(Point2D point) {
		return (rect.contains(point));
		
	}
}
