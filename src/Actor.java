import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {
	public abstract void act(long now);
	
	public double getHeight() {
		return getImage().getHeight();
	}
	public double getWidth() {
		return getImage().getWidth();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(Class<A> cls) {
		ArrayList<A> actors = new ArrayList<A>();
		for(Node a : getWorld().getChildren()) {
			if(a != this && (cls.isInstance(a) || cls == null) && intersects(a.getBoundsInParent())) {
				actors.add((A)a);
			}
		}
		return actors;
	}
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) { return (getIntersectingObjects(cls).size() > 0 ) ? getIntersectingObjects(cls).get(0) : null; }
	
	public World getWorld() {
		return (World)getParent();
	}
	
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
}