package model;
import java.util.ArrayList;

/**
 * gizmofactory class is used to generate object of concrete class named Gizmo based on given information
 */
public class GizmoFactory {

	ArrayList takozList = new ArrayList<Takoz>();
	ArrayList firildakList = new ArrayList<Firildak>();
	ArrayList tokatList = new ArrayList<Tokat>();

	/**
	 * use getGizmos method to get object of type Gizmo
	 * @param gizmoList, after creating objects of Gizmo class, we store the objects into these arraylists
	 */
	public void getGizmos(ArrayList<Gizmo> gizmoList){

		for(int i = 0; i < gizmoList.size(); i++){
			
			if(gizmoList.get(i).getClassType().equals("Tokat")){
				tokatList.add(gizmoList.get(i));
			}
			else if(gizmoList.get(i).getClassType().equals("Takoz")){
				takozList.add(gizmoList.get(i));
			}
			else if(gizmoList.get(i).getClassType().equals("Firildak")){
				firildakList.add(gizmoList.get(i));
			}
		}

	}


}
