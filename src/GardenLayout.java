import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GardenLayout extends Application
{
	
//  Variables:  ***********************************************************************
	
	int gardenWidth=400;
	int gardenHeight=600;
		
	AnchorPane pane;
	Scene scene;
	Color color;
	Flower flower;
	FlowerBed flowerBed;
	ImageView flowerView;
	Point2D lastPosition = null;
	Point2D clickPoint;
	GardenComponent currentComponent;
	boolean inDragMode = false;
	List<GardenComponent> Components = new ArrayList<GardenComponent>();
	

	
	
	
	
//  functions:  ************************************************************************
	
	public void drawGrass()
	{	
		Image grass = new Image("Photos/Grass.jpg",gardenWidth, gardenHeight,false,false);
		ImageView grassView = new ImageView(grass);
		grassView.setX(0);
		grassView.setY(0);
		pane.getChildren().add(grassView);			
	}

	
	public void drawFlower()
	{	
		Image flower = new Image("Photos/Flower1.png",70, 70,false,false);
		flowerView = new ImageView(flower);
		flowerView.setX(20);
		flowerView.setY(20);
		pane.getChildren().add(flowerView);			
	}

	
	private GardenComponent getCurrentShape(){
		GardenComponent currentComponent = null;
		for(GardenComponent Component: Components){
			if (Component.ContainsPoint(clickPoint)){
				currentComponent = Component;
				break;
			}
		} 
		return currentComponent;
    }

	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent mouseEvent)
		{
			clickPoint = new Point2D(mouseEvent.getX(),mouseEvent.getY());
			String eventName = mouseEvent.getEventType().getName();
			
			if(!inDragMode){
				currentComponent = getCurrentShape();
        	}
			
			switch(eventName)
			{
			
			
				case("MOUSE_RELEASED"):
		    		if(currentComponent!=null && currentComponent instanceof Flower)
		    		{
		    			for(GardenComponent container: Components)
		    			{
		        			if (container instanceof FlowerBed && container.ContainsPoint(clickPoint))
		        			{
		        				((FlowerBed)container).addChild(currentComponent);
		        				break;
		        			}
		        			
		        		} 
					}
		    		else
		    		{
		    			currentComponent = null;  
		    		}
					inDragMode = false;
					break;
					
					
			
				case("MOUSE_DRAGGED"):
					inDragMode = true;
					if(currentComponent!=null && lastPosition != null)
					{
						double delataX = clickPoint.getX()-lastPosition.getX();
						double delataY = clickPoint.getY()-lastPosition.getY();
						currentComponent.move(delataX,delataY);
						//flowerView.setX(flowerView.getX()+delataX);
						//flowerView.setY(flowerView.getY()+delataY);
					}
				break;
				
				
			}
			lastPosition = clickPoint;
		}
	};
	

	
	
	
	
//  The Stage:  ************************************************************************
	
	@Override
	public void start(Stage GreenGarden) throws Exception
	{
		 
		pane = new AnchorPane();
		scene = new Scene(pane, gardenWidth, gardenHeight);
		//scene.setFill(Color.YELLOWGREEN);
		
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		
		drawGrass();
		//drawFlower();
		//Components.add(flowerView);
		
		
		flowerBed = new FlowerBed(new Point2D(50,200), 150, 100);
		Components.add(flowerBed);
		pane.getChildren().add(flowerBed.getRectangle());		
		
		flower = new Flower(new Point2D(100,100),Color.RED,true);
		Components.add(flower);
		pane.getChildren().add(flower.getCircle());	
		
	

		
		
		GreenGarden.setTitle("Green Garden");
		GreenGarden.setScene(scene);
		GreenGarden.show();		
		
	}
	


//  main:  *****************************************************************************		
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
