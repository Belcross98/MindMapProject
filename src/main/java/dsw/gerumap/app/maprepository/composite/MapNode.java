package dsw.gerumap.app.maprepository.composite;


import lombok.*;


@Getter



public abstract class MapNode  {
    private String name;

    private MapNode parent;


    public void setName(String name) {
        this.name = name;
    }

    public void setParent(MapNode parent) {
        this.parent = parent;
    }

    public MapNode(){

    }

    public MapNode(String name, MapNode parent){
        this.name = name;
        this.parent = parent;

    }

    @Override
    public boolean equals(Object obj){
       /* if(obj != null && obj instanceof MapNode){
            MapNode otherobj = (MapNode) obj;
            return this.getName().equals(otherobj.getName());
        }
        */
        return super.equals(obj);
      //  return false;
    }
}
