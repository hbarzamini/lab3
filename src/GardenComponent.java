import javafx.geometry.Point2D;

public interface GardenComponent
{
	void move(double dx, double dy);
	public boolean ContainsPoint(Point2D point);

}
