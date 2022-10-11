import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane{
	private WorldTimer timer;
	private HashSet<KeyCode> keysDown;
	private boolean hasStarted;
	private boolean isPaused;

	public World(){
		timer = new WorldTimer();
		keysDown = new HashSet<>();
		hasStarted = false;
		isPaused = false;
	}

	public abstract void act(long now);

	public <A extends Actor> java.util.List<A> getObjects(Class<A> cls) {
		ArrayList<A> actors = new ArrayList<A>();
		for(Node a : getChildren()) {
			if(cls.isInstance(a) || cls == null) {
				actors.add((A)a);
			}
		}
		return actors;
	}

	public void add(Actor actor) { getChildren().add(actor); }
	public void addNode(Node n){ getChildren().add(n); }
	public void remove(Actor actor) { getChildren().remove(actor); }
	public void removeNode(Node n) { getChildren().remove(n); }

	public void start() { timer.start(); }
	public void stop() { timer.stop(); }

	public boolean isPaused() { return isPaused; }
	public boolean hasStarted() { return hasStarted; }
	public void pause(){ isPaused = true; }
	public void unpause(){ isPaused = false; }
	public void setHasStarted(boolean s){ hasStarted = s; }
	
	public void addKeyDown(KeyCode k) { keysDown.add(k); }
	public void removeKeyDown(KeyCode k) { keysDown.remove(k); }
	
	public boolean isKeyDown(KeyCode k) {
		for(KeyCode t : keysDown) {
			if(k.equals(t)) return true;
		}
		return false;
    }
	
	private class WorldTimer extends AnimationTimer{
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			act(now);
			if(isPaused) return;
			
			for (Actor a : getObjects(Actor.class)) {
				a.act(now);
			}
			
		}
		
	}
}



