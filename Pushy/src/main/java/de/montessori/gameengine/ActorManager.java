package de.montessori.gameengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ActorManager {

	private World world;
	
    /** All the actor objects currently in play */
    private List<Actor> GAME_ACTORS = new ArrayList<>();

    /**
     * 
     * @param world
     */
	public ActorManager(World world) {
        this.world = world;
	}
	
	public World getWorld(){
		return world;
	}
	
	/**
	 * 
	 * @return list of all actors.
	 */
    public List<Actor> getAllActors() {
        return GAME_ACTORS;
    }
    
    /**
     * VarArgs of actor objects to be added to the game.
     * @param actors 
     */
    public void addActors(Actor... actors) {  
    	GAME_ACTORS.addAll(Arrays.asList(actors));
    	for (int i = 0; i < actors.length; i++) {
			actors[i].registerManager(this);
		}
    }
    
    public void addActorList(List<Actor> actors){
    	GAME_ACTORS.addAll(actors);
    	for (Actor actor : actors) {
			actor.registerManager(this);
		}
    }

}
